package com.shaobing.runner.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.shaobing.runner.Bean.UserBean;
import com.shaobing.runner.R;
import com.shaobing.runner.Tools.Tool;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private LinearLayout mOneLin, mTwoLin, mThreeLin;
    private ImageView mOneImg,mTwoImg,mThreeImg;

    private FrameLayout mFrameLayout;
    private StepFragment stepFragment;
    private CourseFragment courseFragment;
    private PersonFragment personFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private UserBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tool.steep(this,true,true);
        Bundle bundle = getIntent().getExtras();
        user = (UserBean)bundle.getSerializable("user");

        mFrameLayout = findViewById(R.id.frame);
        mOneLin = findViewById(R.id.one_lin);
        mTwoLin = findViewById(R.id.two_lin);
        mThreeLin = findViewById(R.id.three_lin);
        mOneImg=findViewById(R.id.one_img);
        mTwoImg=findViewById(R.id.two_img);
        mThreeImg=findViewById(R.id.three_img);
        mOneLin.setOnClickListener(this);
        mTwoLin.setOnClickListener(this);
        mThreeLin.setOnClickListener(this);
        //获取FragmentManager对象
        manager = getSupportFragmentManager();
        //获取FragmentTransaction对象
        transaction = manager.beginTransaction();
        setSwPage(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one_lin:
                setSwPage(0);
                break;
            case R.id.two_lin:
                setSwPage(1);
                break;
            case R.id.three_lin:
                setSwPage(2);
                break;
        }
    }

    public void setSwPage(int i) {
        //获取FragmentManager对象
        manager = getSupportFragmentManager();
        //获取FragmentTransaction对象
        transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideFragments(transaction);
        switch (i) {
            case 0:
                reImgSelect();
                mOneImg.setSelected(true);
                if (stepFragment == null) {
                    stepFragment = new StepFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user",user);
                    stepFragment.setArguments(bundle);
                    transaction.add(R.id.frame, stepFragment);
                } else {
                    //如果微信对应的Fragment已经实例化，则直接显示出来
                    transaction.show(stepFragment);
                }
                break;
            case 1:
                reImgSelect();
                mTwoImg.setSelected(true);
                if (courseFragment == null) {
                    courseFragment = new CourseFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user",user);
                    courseFragment.setArguments(bundle);
                    transaction.add(R.id.frame, courseFragment);
                } else {
                    //如果微信对应的Fragment已经实例化，则直接显示出来
                    transaction.show(courseFragment);
                }
                break;
            case 2:
                reImgSelect();
                mThreeImg.setSelected(true);
                if (personFragment == null) {
                    personFragment = new PersonFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user",user);
                    personFragment.setArguments(bundle);
                    transaction.add(R.id.frame, personFragment);
                } else {
                    //如果微信对应的Fragment已经实例化，则直接显示出来
                    transaction.show(personFragment);
                }
                break;
        }
        transaction.commit();
    }

    //将四个的Fragment隐藏
    private void hideFragments(FragmentTransaction transaction) {
        if (stepFragment != null) {
            transaction.hide(stepFragment);
        }
        if (courseFragment != null) {
            transaction.hide(courseFragment);
        }
        if (personFragment != null) {
            transaction.hide(personFragment);
        }
    }

    //初始化底部菜单选择状态
    private void reImgSelect(){
        mOneImg.setSelected(false);
        mTwoImg.setSelected(false);
        mThreeImg.setSelected(false);
    }
}