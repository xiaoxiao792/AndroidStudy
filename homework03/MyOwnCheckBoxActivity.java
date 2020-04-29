package com.example.homework03;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MyOwnCheckBoxActivity extends AppCompatActivity {
    CheckBox dlj;
    CheckBox zhj;
    CheckBox fyq;
    CheckBox wf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CheckBoxActivity");
        setContentView(R.layout.activity_my_own_check_box);
        find_and_modify_text_view();
    }

    private void find_and_modify_text_view() {
        dlj = (CheckBox) findViewById(R.id.dlj);
        zhj = (CheckBox) findViewById(R.id.zhj);
        fyq = (CheckBox) findViewById(R.id.fyq);
        wf = (CheckBox) findViewById(R.id.wf);
        Button get_view_button = (Button) findViewById(R.id.button_in_checkboxactivity);
        get_view_button.setOnClickListener(get_view_button_listener);
    }

    private Button.OnClickListener get_view_button_listener = new Button.OnClickListener() {
        public void onClick(View v) {
            String r = "我喜爱的歌手是：";
            if (dlj.isChecked()) {
                r = r + " " + dlj.getText();//如果复选框被选中，将其文字得到
            }
            if (zhj.isChecked()) {
                r = r + " " + zhj.getText();
            }
            if (fyq.isChecked()) {
                r = r + " " + fyq.getText();
            }
            if (wf.isChecked()) {
                r = r + " " + wf.getText();
            }
            setTitle(r);
        }
    };

}
