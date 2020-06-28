package com.shaobing.runner.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.shaobing.runner.Bean.CourseBean;
import com.shaobing.runner.Bean.UserBean;
import com.shaobing.runner.Bean.UserSubBean;
import com.shaobing.runner.DB.MySqlDBAccess;
import com.shaobing.runner.R;
import com.shaobing.runner.Tools.DisplayUtils;
import com.shaobing.runner.Tools.MockUtils;
import com.shaobing.runner.Tools.SimpleOnVideoControlListener;
import com.shaobing.runner.Tools.Tool;
import com.shaobing.runner.Tools.VideoDetailInfo;
import com.shaobing.runner.View.BDVideoView;

public class CoursePlayActivity extends AppCompatActivity {

    private BDVideoView videoView;
    private VideoDetailInfo info;
    private UserBean user;
    private CourseBean courseBean;
    private TextView course_play_course_name,course_play_time,course_play_sub_num,course_play_course_describe,btn_sub_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_play);
        Bundle bundle = getIntent().getExtras();
        user = (UserBean)bundle.getSerializable("user");
        courseBean = (CourseBean)bundle.getSerializable("course");
        initView();

        initVideo(courseBean.getCourseName(),courseBean.getCourseVideo());

        videoView.setOnVideoControlListener(new SimpleOnVideoControlListener() {

            @Override
            public void onRetry(int errorStatus) {

            }

            @Override
            public void onBack() {
                onBackPressed();
            }

            @Override
            public void onFullScreen() {
                //DisplayUtils.toggleScreenOrientation(CoursePlayActivity.this);
                Toast.makeText(CoursePlayActivity.this, "开发者已禁用此功能！", Toast.LENGTH_SHORT).show();
            }
        });
        videoView.startPlayVideo(info);

        btn_sub_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UserSubBean userSubBean = new UserSubBean(Tool.getAutoId(),user.getUserId(),courseBean.getCourseId());
                        MySqlDBAccess.insert(userSubBean);
                    }
                });
                t1.start();
                Toast.makeText(CoursePlayActivity.this, "已加入你的课程！", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void initView(){
        videoView = findViewById(R.id.vv);
        course_play_course_name = findViewById(R.id.course_play_course_name);
        course_play_time = findViewById(R.id.course_play_time);
        course_play_sub_num =  findViewById(R.id.course_play_sub_num);
        course_play_course_describe =  findViewById(R.id.course_play_course_describe);
        btn_sub_course =  findViewById(R.id.btn_sub_course);

        course_play_course_name.setText(courseBean.getCourseName());
        course_play_time.setText(courseBean.getCourseTime()+"分钟");
        course_play_sub_num.setText(courseBean.getCourseSubNum()+"人学习");
        course_play_course_describe.setText(courseBean.getCourseDescribe());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        videoView.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();

        videoView.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        videoView.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!DisplayUtils.isPortrait(this)) {
            if(!videoView.isLock()) {
                DisplayUtils.toggleScreenOrientation(this);
            }
        } else {
            super.onBackPressed();
        }
    }

    private void initVideo(String name,String url){
        info = null;
        try {
            info = MockUtils.mockData(VideoDetailInfo.class,url);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        info.title=name;
    }
}