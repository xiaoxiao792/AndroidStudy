package com.shaobing.runner.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.shaobing.runner.Activity.CoursePlayActivity;
import com.shaobing.runner.Bean.CourseBean;
import com.shaobing.runner.Bean.UserBean;
import com.shaobing.runner.R;
import com.shaobing.runner.Tools.Tool;
import com.shehuan.niv.NiceImageView;
import java.util.List;

public class CourseAdapter extends BaseAdapter {

    private final Context context;
    private final List<CourseBean> courseBeans;
    private UserBean userBean;
    Bitmap[] bmp;
    String[] imgUrl;

    public CourseAdapter(Context context, final List<CourseBean> courseBeans, UserBean userBean) {
        this.context = context;
        this.courseBeans = courseBeans;
        this.userBean = userBean;
        this.bmp = new Bitmap[courseBeans.size()];
        this.imgUrl = new String[courseBeans.size()];

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<courseBeans.size();i++){
                    imgUrl[i]=courseBeans.get(i).getCourseImg();
                    bmp[i] = Tool.getURLimage(imgUrl[i]);
                }
            }
        });
        t1.start();
        try {
            t1.join(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getCount() {
        return courseBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return courseBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        //判断
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.course_list_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.course_img = view.findViewById(R.id.course_img);
            viewHolder.course_name = view.findViewById(R.id.course_name);
            viewHolder.course_time = view.findViewById(R.id.course_time);
            viewHolder.course_sub_num = view.findViewById(R.id.course_sub_num);
            viewHolder.course_study_btn = view.findViewById(R.id.course_study_btn);
            view.setTag(viewHolder);
        }
        //获得值
        viewHolder = (ViewHolder) view.getTag();

        //获得item对象
        final CourseBean bean = (CourseBean) getItem(position);

        viewHolder.course_img.setImageBitmap(bmp[position]);
        viewHolder.course_name.setText(bean.getCourseName());
        viewHolder.course_time.setText(bean.getCourseTime()+"分钟");
        viewHolder.course_sub_num.setText(bean.getCourseSubNum()+"人在学");
        viewHolder.course_study_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoursePlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user",userBean);
                bundle.putSerializable("course",bean);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        return view;
    }

    //第3方的类  ViewHolder
    private class ViewHolder{
        private NiceImageView course_img;
        private TextView course_name;
        private TextView course_time;
        private TextView course_sub_num;
        private TextView course_study_btn;
    }
}
