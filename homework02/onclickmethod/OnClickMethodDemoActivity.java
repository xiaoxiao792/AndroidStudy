package com.example.homwork02;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class OnClickMethodDemoActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click_method_demo);
        Button ok = (Button)findViewById(R.id.button_OK);
        ok.setOnClickListener (new OnClickListener() {//侦听此按钮的按键动作
            public void onClick (View v) {//如果单击此按钮
                EditText et = (EditText) findViewById (R.id.et_money);
                RadioButton rb1 = (RadioButton) findViewById (R.id.radiovip);
                String setText;
                int i = Integer.parseInt(et.getText().toString());
                String a = null;//定义字符串变量
                if (rb1.isChecked()) {//如果VIP的RadioButton被按下
                    setText = "VIP会员";
                    i=i*4/5;
                    a=i+"";

                }else {
                    setText = "普通顾客";
                    a=i+"";
                }
                TextView tv1 = (TextView) findViewById(R.id.show_vip_com);//找到id号为showText的布局控件并将其设定为名为tv1的TextView实例
                tv1.setText(setText);
                TextView tv2 = (TextView) findViewById(R.id.tv_money);//找到id号为showText的布局控件并将其设定为名为tv1的TextView实例
                tv2.setText(a);
            }
        });
    }
}