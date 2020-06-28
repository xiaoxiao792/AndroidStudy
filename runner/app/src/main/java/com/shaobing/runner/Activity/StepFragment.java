package com.shaobing.runner.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.shaobing.runner.Bean.StepBean;
import com.shaobing.runner.Bean.UserBean;
import com.shaobing.runner.DB.MySqlDBAccess;
import com.shaobing.runner.R;
import com.shaobing.runner.Service.BindService;
import com.shaobing.runner.Service.MusicService;
import com.shaobing.runner.Tools.Tool;
import com.shaobing.runner.Tools.UpdateUiCallBack;
import com.shaobing.runner.View.CircularProgressView;

public class StepFragment extends Fragment {

    private BindService bindService;
    private MyConn conn;
    private Intent myMusic;
    private MusicService.MyBinder binder;
    private TextView textView,tv_step_standard;
    private boolean isBind;
    private MyHandler handler = new MyHandler();
    private String newDate,oldDate;
    private int stepNum = 0;
    private int standardNum = 500;
    private ImageView step_frag_history,step_frag_music;
    private LinearLayout paihangbang_btn;
    private CircularProgressView cir_step;
    private boolean musicKey=false;
    private UserBean user;

    @SuppressLint("HandlerLeak")
    private class MyHandler extends Handler {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                textView.setText(msg.arg1 + "");
                stepNum = msg.arg1;
                int standardKey = stepNum*100/standardNum;
                if(standardKey<=100&&standardKey>1){
                    cir_step.setProgress(standardKey,1000);
                }else if(standardKey <=1){
                    cir_step.setProgress(1,1000);
                }else {
                    cir_step.setProgress(100,1000);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.step_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        Bundle bundle = getArguments();
        user = (UserBean)bundle.getSerializable("user");

        initStepNum();
        initView(view);

        Toast.makeText(getActivity(), user.getUserId()+",欢迎您", Toast.LENGTH_SHORT).show();

        myMusic = new Intent(getActivity(),MusicService.class);
        Intent intent = new Intent(getActivity(), BindService.class);
        isBind =  getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        getActivity().startService(intent);

        step_frag_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicKey==false){
                    step_frag_music.setSelected(true);
                    if (conn == null)
                        conn = new MyConn();
                        getActivity().bindService(myMusic,conn,Context.BIND_AUTO_CREATE);
                        getActivity().startService(myMusic);
                        musicKey = true;
                }else {
                    step_frag_music.setSelected(false);
                    binder.callMethodInService();
                    musicKey = false;
                }
            }
        });
        tv_step_standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upStepNum();
            }
        });
        paihangbang_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StepBean stepBean = new StepBean(Tool.getAutoId(), user.getUserId(), stepNum, Tool.getDate());
                        MySqlDBAccess.updateStep(stepBean);
                    }
                });
                t1.start();
                try {
                    t1.join(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                putStepNum(stepNum);
                Intent intent = new Intent(getActivity(), StepRankActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user",user);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
    }

    private void initView(View view){
        textView = view.findViewById(R.id.busu);
        tv_step_standard = view.findViewById(R.id.tv_step_standard);
        step_frag_history = view.findViewById(R.id.step_frag_history);
        step_frag_music = view.findViewById(R.id.step_frag_music);
        paihangbang_btn = view.findViewById(R.id.paihangbang_btn);
        cir_step = view.findViewById(R.id.cir_step);
        int standardKey = stepNum*100/standardNum;
        if(standardKey<=100&&standardKey>1){
            cir_step.setProgress(standardKey,1000);
        }else if(standardKey <=1){
            cir_step.setProgress(1,1000);
        }else {
            cir_step.setProgress(100,1000);
        }
        textView.setText(stepNum+"");
        tv_step_standard.setText("目标步数："+standardNum+"步");
    }

    //和绷定服务数据交换的桥梁，可以通过IBinder service获取服务的实例来调用服务的方法或者数据
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BindService.LcBinder lcBinder = (BindService.LcBinder) service;
            bindService = lcBinder.getService();
            bindService.registerCallback(new UpdateUiCallBack() {
                @Override
                public void updateUi(int stepCount) {
                    //当前接收到stepCount数据，就是最新的步数
                    Message message = Message.obtain();
                    message.what = 1;
                    message.arg1 = stepCount;
                    handler.sendMessage(message);
                    Log.i("MainActivity—updateUi","当前步数"+stepCount);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MusicService.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }


    private void putOldDate(String oldDate){
        SharedPreferences sp = getActivity().getSharedPreferences("runnerConfig",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("oldDate",oldDate);
        editor.apply();
    }

    private String getOldDate(){
        SharedPreferences sp = getActivity().getSharedPreferences("runnerConfig",Context.MODE_PRIVATE);
        return sp.getString("oldDate","");
    }

    private void putStepNum(int stepNum){
        SharedPreferences sp = getActivity().getSharedPreferences("runnerConfig",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("stepNum",stepNum);
        editor.apply();
    }

    private int getStepNum(){
        SharedPreferences sp = getActivity().getSharedPreferences("runnerConfig",Context.MODE_PRIVATE);
        return sp.getInt("stepNum",0);
    }

    private void putStandardNum(int standardNum){
        SharedPreferences sp = getActivity().getSharedPreferences("runnerConfig",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("standardNum",standardNum);
        editor.apply();
    }

    private int getStandardNum(){
        SharedPreferences sp = getActivity().getSharedPreferences("runnerConfig",Context.MODE_PRIVATE);
        return sp.getInt("standardNum",500);
    }

    private void putStandard(int standard){
        SharedPreferences sp = getActivity().getSharedPreferences("runnerConfig", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("standard",standard);
        editor.apply();
    }

    private int getStandard(){
        SharedPreferences sp = getActivity().getSharedPreferences("runnerConfig",Context.MODE_PRIVATE);
        return sp.getInt("standard",0);
    }

    private void initStepNum(){
        newDate = Tool.getDate();
        oldDate = getOldDate();
        if (newDate.equals(oldDate)){
            stepNum = getStepNum();
        }else {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    StepBean stepBean = new StepBean(Tool.getAutoId(), user.getUserId(), 0, Tool.getDate());
                    MySqlDBAccess.insert(stepBean);
                }
            });
            t1.start();
        }
        standardNum = getStandardNum();
        putOldDate(Tool.getDate());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //app被关闭之前，service先解除绑定
        if (isBind) {
            getActivity().unbindService(serviceConnection);
        }
        putStepNum(stepNum);
    }

    public void upStepNum(){
        final Dialog dialog = new Dialog(getActivity());
        //去掉title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.up_step_num);
        window.setBackgroundDrawableResource(android.R.color.transparent);

        // 设置宽高
        window.setLayout(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimScale);
        window.setGravity(Gravity.CENTER);

        final EditText et_add_plan_name = (EditText)window.findViewById(R.id.et_add_plan_name);
        Button btn_add_plan_add = (Button)window.findViewById(R.id.btn_add_plan_add);

        btn_add_plan_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                standardNum = Integer.parseInt(et_add_plan_name.getText().toString().trim());
                tv_step_standard.setText("目标步数："+standardNum+"步");
                putStandardNum(standardNum);
                int standardKey = stepNum*100/standardNum;
                if(standardKey<=100&&standardKey>1){
                    cir_step.setProgress(standardKey,1000);
                }else if(standardKey <=1){
                    cir_step.setProgress(1,1000);
                }else {
                    cir_step.setProgress(100,1000);
                }
            }
        });
    }

}
