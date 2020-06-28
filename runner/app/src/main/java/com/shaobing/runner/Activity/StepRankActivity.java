package com.shaobing.runner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.shaobing.runner.Adapter.StepRankAdapter;
import com.shaobing.runner.Bean.StepBean;
import com.shaobing.runner.Bean.UserBean;
import com.shaobing.runner.DB.MySqlDBAccess;
import com.shaobing.runner.R;
import com.shaobing.runner.Tools.Tool;
import java.util.List;

public class StepRankActivity extends AppCompatActivity {

    private ListView step_rank_list;
    private TextView ac_step_rank_ranknum,ac_step_rank_username,ac_step_rank_stepnum;
    private List<StepBean> stepBeans;
    private UserBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_rank);

        Bundle bundle = getIntent().getExtras();
        user = (UserBean)bundle.getSerializable("user");

        initView();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                stepBeans = MySqlDBAccess.queryStep(Tool.getDate());
            }
        });
        t1.start();
        try {
            t1.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(stepBeans!=null) {
            StepRankAdapter stepRankAdapter = new StepRankAdapter(this, stepBeans);
            step_rank_list.setAdapter(stepRankAdapter);
            int i=1;
            for (StepBean e:stepBeans){
                if (e.getUserId().equals(user.getUserId())){
                    ac_step_rank_stepnum.setText(e.getStepNum()+"");
                    break;
                }
                i++;
            }
            ac_step_rank_ranknum.setText(i+"");
            ac_step_rank_username.setText(user.getUserId());
        }
    }

    private void initView(){
        step_rank_list = findViewById(R.id.step_rank_list);
        ac_step_rank_ranknum = findViewById(R.id.ac_step_rank_ranknum);
        ac_step_rank_username = findViewById(R.id.ac_step_rank_username);
        ac_step_rank_stepnum = findViewById(R.id.ac_step_rank_stepnum);
    }
}
