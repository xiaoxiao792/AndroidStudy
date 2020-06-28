package com.shaobing.runner.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.shaobing.runner.Adapter.CourseAdapter;
import com.shaobing.runner.Bean.CourseBean;
import com.shaobing.runner.Bean.UserBean;
import com.shaobing.runner.DB.MySqlDBAccess;
import com.shaobing.runner.R;
import com.shaobing.runner.Tools.Tool;
import com.shehuan.niv.NiceImageView;
import java.util.List;

public class PersonFragment extends Fragment {
    private TextView person_frag_name,person_frag_standard_num;
    private ImageView person_frag_user_setting;
    private UserBean user;
    private ListView my_course_list_view;
    private List<CourseBean> courseBeans;
    private NiceImageView niceImageView;
    String url = "http://imgstore04.cdn.sogou.com/app/a/100520024/877e990117d6a7ebc68f46c5e76fc47a";
    Bitmap bmp;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.person_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        user = (UserBean)bundle.getSerializable("user");

        initView(view);

        //设置个人信息
        person_frag_name.setText(user.getUserName());

        //设置达标天数
        String standardKey = "达标"+getStandard()+"天";
        person_frag_standard_num.setText(standardKey);

        //设置课程列表
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                courseBeans = MySqlDBAccess.queryUserSub(user.getUserId());
            }
        });
        t1.start();
        try {
            t1.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (courseBeans!=null){
            CourseAdapter courseAdapter = new CourseAdapter(getActivity(),courseBeans,user);
            my_course_list_view.setAdapter(courseAdapter);
        }

        //设置头像
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                bmp = Tool.getURLimage(url);
                System.out.println("000");
            }
        });
        t2.start();
        try {
            t2.join(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        niceImageView.setImageBitmap(bmp);

        //前往设置个人信息按钮事件
        person_frag_user_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSetting();
            }
        });

    }

    private void initView(View view){
        my_course_list_view = view.findViewById(R.id.my_course_list_view);
        niceImageView = view.findViewById(R.id.person_frag_user_img);
        person_frag_name = view.findViewById(R.id.person_frag_name);
        person_frag_standard_num = view.findViewById(R.id.person_frag_standard_num);
        person_frag_user_setting = view.findViewById(R.id.person_frag_user_setting);
    }

    public void userSetting(){
        final Dialog dialog = new Dialog(getActivity());
        //去掉title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.user_setting);
        window.setBackgroundDrawableResource(android.R.color.transparent);

        // 设置宽高
        window.setLayout(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimScale);
        window.setGravity(Gravity.CENTER);

        EditText et_user_weight = (EditText)window.findViewById(R.id.et_user_weight);
        EditText et_user_height = (EditText)window.findViewById(R.id.et_user_height);
        Button btn_user_setting_confirm = (Button)window.findViewById(R.id.btn_user_setting_confirm);

        btn_user_setting_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    private int getStandard(){
        SharedPreferences sp = getActivity().getSharedPreferences("runnerConfig",Context.MODE_PRIVATE);
        return sp.getInt("standard",0);
    }
}
