package com.shaobing.runner.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.shaobing.runner.Adapter.CourseAdapter;
import com.shaobing.runner.Bean.CourseBean;
import com.shaobing.runner.Bean.UserBean;
import com.shaobing.runner.DB.MySqlDBAccess;
import com.shaobing.runner.R;
import java.util.List;

public class CourseFragment extends Fragment {
    private UserBean user;
    private ListView course_list_view;
    private List<CourseBean> courseBeans;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.course_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        user = (UserBean)bundle.getSerializable("user");

        //加载课程列表
        course_list_view = view.findViewById(R.id.course_list_view);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                courseBeans = MySqlDBAccess.queryCourse();
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
            course_list_view.setAdapter(courseAdapter);
        }

    }

}
