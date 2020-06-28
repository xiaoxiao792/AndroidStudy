package com.shaobing.runner.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.shaobing.runner.Bean.StepBean;
import com.shaobing.runner.R;
import java.util.List;

public class StepRankAdapter extends BaseAdapter {

    private final Context context;
    private final List<StepBean> stepBeans;

    public StepRankAdapter(Context context, List<StepBean> stepBeans) {
        this.context = context;
        this.stepBeans = stepBeans;
    }

    @Override
    public int getCount() {
        return stepBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return stepBeans.get(position);
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
            view = LayoutInflater.from(context).inflate(R.layout.step_rank_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.step_rank_ranknum = (TextView) view.findViewById(R.id.step_rank_ranknum);
            viewHolder.step_rank_username = (TextView) view.findViewById(R.id.step_rank_username);
            viewHolder.step_rank_stepnum = (TextView) view.findViewById(R.id.step_rank_stepnum);
            view.setTag(viewHolder);
        }
        //获得值
        viewHolder = (ViewHolder) view.getTag();
        //获得item对象
        StepBean bean = (StepBean) getItem(position);

        int positionNum = position+1;
        viewHolder.step_rank_ranknum.setText(positionNum+"");
        viewHolder.step_rank_username.setText(bean.getUserId());
        viewHolder.step_rank_stepnum.setText(bean.getStepNum()+"");

        return view;
    }

    //第3方的类  ViewHolder
    private class ViewHolder{
        private TextView step_rank_ranknum;
        private TextView step_rank_username;
        private TextView step_rank_stepnum;
    }
}
