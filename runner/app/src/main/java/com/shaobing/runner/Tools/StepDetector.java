package com.shaobing.runner.Tools;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * @className : StepDetector
 * @description : 计步器算法源码，检测是否有步点
 * @date : 2020/6/18 12:41
 * @author : 邵文炳
 */
public class StepDetector implements SensorEventListener {

    //传感器数据类型
    //存放三轴数据
    private float[] oriValues = new float[3];
    private final int ValueNum = 4;
    //用于存放计算阈值的波峰波谷差值
    private float[] tempValue = new float[ValueNum];
    private int tempCount = 0;
    //是否上升的标志位
    private boolean isDirectionUp = false;
    //持续上升次数
    private int continueUpCount = 0;
    //上一点的持续上升的次数，为了记录波峰的上升次数
    private int continueUpFormerCount = 0;
    //上一点的状态，上升还是下降
    private boolean lastStatus = false;
    //波峰值
    private float peakOfWave = 0;
    //波谷值
    private float valleyOfWave = 0;
    //此次波峰的时间
    private long timeOfThisPeak = 0;
    //上次波峰的时间
    private long timeOfLastPeak = 0;
    //当前的时间
    private long timeOfNow = 0;
    //当前传感器的值
    private float gravityNew = 0;
    //上次传感器的值
    private float gravityOld = 0;
    //动态阈值需要动态的数据，这个值用于这些动态数据的阈值
    private final float InitialValue = (float) 1.3;
    //初始阈值
    private float ThreadValue = (float) 2.0;
    //波峰波谷时间差
    private int TimeInterval = 250;
    private StepCountListener mStepListeners;

    /**
     * @methodName : onSensorChanged
     * @description : 当传感器值改变回调此方法
     * @date : 2020/6/18 12:43
     * @author : 邵文炳
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        for (int i = 0; i < 3; i++) {
            oriValues[i] = event.values[i];
        }
        gravityNew = (float) Math.sqrt(oriValues[0] * oriValues[0]
                + oriValues[1] * oriValues[1] + oriValues[2] * oriValues[2]);
        detectorNewStep(gravityNew);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void initListener(StepCountListener listener) {
        this.mStepListeners = listener;
    }

    /**
     * @methodName : detectorNewStep
     * @description : 检测步子，并开始计步
     *              1.传入sersor中的数据
     *              2.如果检测到了波峰，并且符合时间差以及阈值的条件，则判定为1步
     *              3.符合时间差条件，波峰波谷差值大于initialValue，则将该差值纳入阈值的计算中
     * @date : 2020/6/18 12:49
     * @author : 邵文炳
     */
    private void detectorNewStep(float values) {
        if (gravityOld == 0) {
            gravityOld = values;
        } else {
            if (detectorPeak(values, gravityOld)) {
                timeOfLastPeak = timeOfThisPeak;
                timeOfNow = System.currentTimeMillis();
                if (timeOfNow - timeOfLastPeak >= TimeInterval
                        && (peakOfWave - valleyOfWave >= ThreadValue)) {
                    timeOfThisPeak = timeOfNow;
                    mStepListeners.countStep();
                }
                if (timeOfNow - timeOfLastPeak >= TimeInterval
                        && (peakOfWave - valleyOfWave >= InitialValue)) {
                    timeOfThisPeak = timeOfNow;
                    ThreadValue = peakValleyThread(peakOfWave - valleyOfWave);
                }
            }
        }
        gravityOld = values;
    }

    /**
     * @methodName : detectorPeak
     * @description : 检测波峰&记录波谷值
     *                以下四个条件判断为波峰：
     *                1.目前点为下降的趋势：isDirectionUp为false
     *                2.之前的点为上升的趋势：lastStatus为true
     *                3.到波峰为止，持续上升大于等于2次
     *                4.波峰值大于20
     *                以下两个条件判断为波谷值
     *                1.观察波形图，可以发现在出现步子的地方，波谷的下一个就是波峰，有比较明显的特征以及差值
     *                2.所以要记录每次的波谷值，为了和下次的波峰做对比
     * @date : 2020/6/18 12:53
     * @author : 邵文炳
     */
    private boolean detectorPeak(float newValue, float oldValue) {
        lastStatus = isDirectionUp;
        if (newValue >= oldValue) {
            isDirectionUp = true;
            continueUpCount++;
        } else {
            continueUpFormerCount = continueUpCount;
            continueUpCount = 0;
            isDirectionUp = false;
        }

        if (!isDirectionUp && lastStatus
                && (continueUpFormerCount >= 2 || oldValue >= 20)) {
            peakOfWave = oldValue;
            return true;
        } else if (!lastStatus && isDirectionUp) {
            valleyOfWave = oldValue;
            return false;
        } else {
            return false;
        }
    }

    /**
     * @methodName : peakValleyThread
     * @description : 阈值的计算
     *                1.通过波峰波谷的差值计算阈值
     *                2.记录4个值，存入tempValue[]数组中
     *                3.在将数组传入函数averageValue中计算阈值
     * @date : 2020/6/18 12:56
     * @author : 邵文炳
     */
    private float peakValleyThread(float value) {
        float tempThread = ThreadValue;
        if (tempCount < ValueNum) {
            tempValue[tempCount] = value;
            tempCount++;
        } else {
            tempThread = averageValue(tempValue, ValueNum);
            for (int i = 1; i < ValueNum; i++) {
                tempValue[i - 1] = tempValue[i];
            }
            tempValue[ValueNum - 1] = value;
        }
        return tempThread;

    }

    /**
     * @methodName : averageValue
     * @description : 梯度化阈值
     *                1.计算数组的均值
     *                2.通过均值将阈值梯度化在一个范围里
     * @date : 2020/6/18 12:57
     * @author : 邵文炳
     */
    private float averageValue(float[] value, int n) {
        float ave = 0;
        for (int i = 0; i < n; i++) {
            ave += value[i];
        }
        ave = ave / ValueNum;
        if (ave >= 8)
            ave = (float) 4.3;
        else if (ave >= 7 && ave < 8)
            ave = (float) 3.3;
        else if (ave >= 4 && ave < 7)
            ave = (float) 2.3;
        else if (ave >= 3 && ave < 4)
            ave = (float) 2.0;
        else {
            ave = (float) 1.3;
        }
        return ave;
    }

}