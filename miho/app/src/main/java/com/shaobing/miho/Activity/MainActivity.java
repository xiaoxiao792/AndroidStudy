package com.shaobing.miho.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.shaobing.miho.Adapter.PlanAdapter;
import com.shaobing.miho.Bean.PlanBean;
import com.shaobing.miho.Bean.ShortCutBean;
import com.shaobing.miho.Bean.UserBean;
import com.shaobing.miho.DB.MySQLiteDBAccessUtil;
import com.shaobing.miho.R;
import com.shaobing.miho.Service.MusicService;
import com.shaobing.miho.Tools.GetTime;
import com.shaobing.miho.Tools.MyDividerDecoration;
import com.shaobing.miho.Tools.Tool;
import com.shaobing.miho.Views.SwipeRecycleView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView img_main_music;
    private FloatingActionButton fb_main_fb1,fb_main_fb2,fb_main_fb3,fb_main_fb4,fb_main_fb5;
    private SwipeRecycleView swipe_recycle_view;
    private FloatingActionsMenu fbm_nain_add;
    private List<ShortCutBean> shortCutBeans;
    private List<PlanBean> planBeans;
    private PlanAdapter planAdapter;
    private boolean imgChanged = false;
    private MusicService.MyBinder binder;
    private MyConn conn;
    private MySQLiteDBAccessUtil mySQLiteDBAccessUtil;
    private Intent myMusic;
    private String newDate,oldDate;
    private int newMonth,oldMonth;
    private int weekDay;
    private UserBean user;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tool.steep(this,true,false);

        Bundle bundle = getIntent().getExtras();
        user = (UserBean)bundle.getSerializable("user");
        userId = user.getUserId();

        if (planBeans==null){
            getData();
        }

        initView();

        myMusic = new Intent(MainActivity.this,MusicService.class);
        img_main_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgChanged){
                    img_main_music.setBackground(getResources().getDrawable(R.mipmap.music_pause));
                    binder.callMethodInService();
                }else {
                    img_main_music.setBackground(getResources().getDrawable(R.mipmap.music_play));
                    if (conn == null)
                        conn = new MyConn();
                    bindService(myMusic,conn,BIND_AUTO_CREATE);
                    startService(myMusic);
                }
                imgChanged = !imgChanged;
            }
        });
        fb_main_fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbm_nain_add.collapse();
                showAddPlan("#00BCD4","1",userId);
            }
        });
        fb_main_fb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbm_nain_add.collapse();
                showAddPlan("#FFC107","2",userId);
            }
        });
        fb_main_fb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbm_nain_add.collapse();
                showAddPlan("#00C853","3",userId);
            }
        });
        fb_main_fb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbm_nain_add.collapse();
                showAddPlan("#F06292","4",userId);
            }
        });
        fb_main_fb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbm_nain_add.collapse();
                showAddPlan("#FF6D00","5",userId);
            }
        });
    }

    private void getData(){
        newDate = GetTime.getDate();
        oldDate = getOldDate();
//        newMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
//        oldMonth = getOldMonth();
        weekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);//1表示星期日

        mySQLiteDBAccessUtil = new MySQLiteDBAccessUtil(this);
        mySQLiteDBAccessUtil.open();
        shortCutBeans = mySQLiteDBAccessUtil.queryAllShortCut();
        planBeans = new ArrayList<>();
        List<PlanBean> planUnLimit = mySQLiteDBAccessUtil.queryRunPlan(userId);
        for (PlanBean a : planUnLimit){
            if (a.getTimeClass()==0){
                planBeans.add(a);
            }else if (a.getTimeClass()==3){
                switch (weekDay){
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        planBeans.add(a);
                        break;
                    default:
                        break;
                }
            }else if (a.getTimeClass()==124){
                switch (weekDay) {
                    case 1:
                    case 7:
                        planBeans.add(a);
                        break;
                    default:
                        break;
                }
            }
        }
        if (!newDate.equals(oldDate)){
            mySQLiteDBAccessUtil.autoAddRecord(userId,planBeans);
            mySQLiteDBAccessUtil.autoPlanPlusPlanNum(planBeans);
        }
//        if (newMonth!=oldMonth){
//
//        }
        mySQLiteDBAccessUtil.close();
    }

    private void initView() {
        img_main_music = (ImageView)findViewById(R.id.img_main_music);
        fb_main_fb1 = (FloatingActionButton)findViewById(R.id.fb_main_fb1);
        fb_main_fb2 = (FloatingActionButton)findViewById(R.id.fb_main_fb2);
        fb_main_fb3 = (FloatingActionButton)findViewById(R.id.fb_main_fb3);
        fb_main_fb4 = (FloatingActionButton)findViewById(R.id.fb_main_fb4);
        fb_main_fb5 = (FloatingActionButton)findViewById(R.id.fb_main_fb5);
        swipe_recycle_view = (SwipeRecycleView)findViewById(R.id.swipe_recycle_view);
        fbm_nain_add = (FloatingActionsMenu)findViewById(R.id.fbm_nain_add);

        swipe_recycle_view.setLayoutManager(new LinearLayoutManager(this));
        swipe_recycle_view.addItemDecoration(new MyDividerDecoration());
        if (planBeans != null){
            planAdapter = new PlanAdapter(this,planBeans);
            swipe_recycle_view.setAdapter(planAdapter);
        }
        fbInitAll();
    }

    private void fbInitAll(){
        for (ShortCutBean a :shortCutBeans){
            if(a.getPositionId().equals("1")) {
                fbInit(fb_main_fb1, a);
            }else if (a.getPositionId().equals("2")){
                fbInit(fb_main_fb2, a);
            }else if (a.getPositionId().equals("3")){
                fbInit(fb_main_fb3, a);
            }else if (a.getPositionId().equals("4")){
                fbInit(fb_main_fb4, a);
            }else if (a.getPositionId().equals("5")){
                fbInit(fb_main_fb5, a);
            }
        }
    }

    private void fbInit(FloatingActionButton floatingActionButton,ShortCutBean shortCutBean){
        int myColor = Color.parseColor(shortCutBean.getColor());
        floatingActionButton.setColorNormal(myColor);
        fbInitImage(floatingActionButton,shortCutBean.getIcon());
    }

    private void fbInitImage(FloatingActionButton floatingActionButton,String str){
        switch (str){
            case "1":
                floatingActionButton.setIcon(R.mipmap.icon01_cat);
                break;
            case "2":
                floatingActionButton.setIcon(R.mipmap.icon02_xuexi);
                break;
            case "3":
                floatingActionButton.setIcon(R.mipmap.icon03_gongzuo);
                break;
            case "4":
                floatingActionButton.setIcon(R.mipmap.icon04_shoushen);
                break;
            case "5":
                floatingActionButton.setIcon(R.mipmap.icon05_shoubing);
                break;
            default:
                break;
        }
    }

    private void putOldDate(){
        SharedPreferences sp = getSharedPreferences("mihoConfig",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("oldDate",GetTime.getDate());
        editor.apply();
    }

    private String getOldDate(){
        SharedPreferences sp = getSharedPreferences("mihoConfig",MODE_PRIVATE);
        return sp.getString("oldDate","");
    }

    private void putOldMonth(){
        SharedPreferences sp = getSharedPreferences("mihoConfig",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("oldMonth",Calendar.getInstance().get(Calendar.MONTH)+1);
        editor.apply();
    }

    private int getOldMonth(){
        SharedPreferences sp = getSharedPreferences("mihoConfig",MODE_PRIVATE);
        return sp.getInt("oldMonth",0);
    }

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MusicService.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public void showAddPlan(final String color, final String icon, final String userId){
        final Dialog dialog = new Dialog(this);
        //去掉title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.add_plan);
        window.setBackgroundDrawableResource(android.R.color.transparent);

        // 设置宽高
        window.setLayout(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimScale);
        window.setGravity(Gravity.CENTER);

        FloatingActionButton fb_add_plan_img = (FloatingActionButton)window.findViewById(R.id.fb_add_plan_img);
        final EditText et_add_plan_name = (EditText)window.findViewById(R.id.et_add_plan_name);
        final RadioButton rb_add_plan_rb1 = (RadioButton)window.findViewById(R.id.rb_add_plan_rb1);
        final RadioButton rb_add_plan_rb2 = (RadioButton)window.findViewById(R.id.rb_add_plan_rb2);
        final RadioButton rb_add_plan_rb3 = (RadioButton)window.findViewById(R.id.rb_add_plan_rb3);
        Button btn_add_plan_add = (Button)window.findViewById(R.id.btn_add_plan_add);

        initFBImg(fb_add_plan_img,color,icon);

        btn_add_plan_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                PlanBean planBean = new PlanBean();
                String planName = et_add_plan_name.getText().toString().trim();
                int timeClass = 0;
                if (rb_add_plan_rb1.isChecked()){
                    timeClass = 0;
                }else if (rb_add_plan_rb2.isChecked()){
                    timeClass = 3;
                }
                else if (rb_add_plan_rb3.isChecked()){
                    timeClass = 124;
                }
                planBean.setPlanId(GetTime.getAutoId());
                planBean.setPlanName(planName);
                planBean.setIcon(icon);
                planBean.setColor(color);
                planBean.setTimeClass(timeClass);
                planBean.setBeginDate(GetTime.getDate());
                planBean.setEndDate(null);
                planBean.setState(1);
                planBean.setForceClass(1);
                planBean.setDuration(0);
                planBean.setClockInNum(0);
                planBean.setPlanNum(0);

                mySQLiteDBAccessUtil = new MySQLiteDBAccessUtil(MainActivity.this);
                mySQLiteDBAccessUtil.open();
                mySQLiteDBAccessUtil.addPlan(planBean,userId);
                mySQLiteDBAccessUtil.addRecord(userId,planBean.getPlanId());
                mySQLiteDBAccessUtil.close();


                if (timeClass==0){
                    planBeans.add(planBean);
                }else if (timeClass==3){
                    switch (weekDay){
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            planBeans.add(planBean);
                            break;
                        default:
                            break;
                    }
                }else if (timeClass==124){
                    switch (weekDay) {
                        case 1:
                        case 7:
                            planBeans.add(planBean);
                            break;
                        default:
                            break;
                    }
                }

                redoPlanAdater();
            }
        });
    }

    private void initFBImg(FloatingActionButton floatingActionButton,String color,String icon){
        int myColor = Color.parseColor(color);
        floatingActionButton.setColorNormal(myColor);
        switch (icon){
            case "1":
                floatingActionButton.setIcon(R.mipmap.icon01_cat);
                break;
            case "2":
                floatingActionButton.setIcon(R.mipmap.icon02_xuexi);
                break;
            case "3":
                floatingActionButton.setIcon(R.mipmap.icon03_gongzuo);
                break;
            case "4":
                floatingActionButton.setIcon(R.mipmap.icon04_shoushen);
                break;
            case "5":
                floatingActionButton.setIcon(R.mipmap.icon05_shoubing);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(myMusic);
        if (conn != null) {
            unbindService(conn);
            conn = null;
        }
        putOldDate();
    }

    public void redoPlanAdater(){
        planAdapter = new PlanAdapter(MainActivity.this,planBeans);
        swipe_recycle_view.setAdapter(planAdapter);
    }
}
