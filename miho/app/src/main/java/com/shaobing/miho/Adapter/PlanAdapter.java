package com.shaobing.miho.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.shaobing.miho.Bean.PlanBean;
import com.shaobing.miho.DB.MySQLiteDBAccessUtil;
import com.shaobing.miho.R;
import com.shaobing.miho.Views.SwipeMenuView;
import java.util.List;

/**
 * @className : PlanAdapter
 * @description : plan适配器
 * @date : 2020/6/3 15:17
 * @author : 邵文炳
 */
public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanHolder> {

    private Context mContext;
    private List<PlanBean> mList;
    private boolean recordState[];

    public PlanAdapter(Context mContext, List<PlanBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        recordState = new boolean[mList.size()];
    }

    @Override
    public PlanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new PlanHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final PlanHolder holder, final int position) {
        final PlanBean bean = mList.get(position);
        holder.tv_item_plan_name.setText(bean.getPlanName());
        holder.tv_item_chock_in_num.setText(bean.getClockInNum()+"次");
        setMenuImg(holder,bean);
        final MySQLiteDBAccessUtil mySQLiteDBAccessUtil = new MySQLiteDBAccessUtil(mContext);
        mySQLiteDBAccessUtil.open();
        recordState[position] = mySQLiteDBAccessUtil.queryRecordState(bean.getPlanId());
        if(recordState[position]){
            GradientDrawable myGrad = (GradientDrawable) holder.ll_layout.getBackground();
            myGrad.setColor(Color.parseColor(bean.getColor()));
        }else {
            GradientDrawable myGrad = (GradientDrawable) holder.ll_layout.getBackground();
            myGrad.setColor(Color.WHITE);
        }
        mySQLiteDBAccessUtil.close();

        holder.tv_item_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.swipe_menu.isMenuOpen()) {
                    holder.swipe_menu.smoothToCloseMenu();
                }
                mList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mList.size());
                mySQLiteDBAccessUtil.open();
                mySQLiteDBAccessUtil.planFinishPlan(bean.getPlanId());
                mySQLiteDBAccessUtil.close();
            }
        });
        holder.tv_item_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.swipe_menu.isMenuOpen()) {
                    holder.swipe_menu.smoothToCloseMenu();
                }
            }
        });
        holder.tv_item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.swipe_menu.isMenuOpen()) {
                    holder.swipe_menu.smoothToCloseMenu();
                }
                mList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mList.size());
                mySQLiteDBAccessUtil.open();
                mySQLiteDBAccessUtil.deletePlan(bean.getPlanId());
                mySQLiteDBAccessUtil.close();
            }
        });
        holder.swipe_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.swipe_menu.isMenuOpen()) {
                    holder.swipe_menu.smoothToCloseMenu();
                }
                if (recordState[position]){

                }else {
                    showClockIn(holder, mySQLiteDBAccessUtil, bean,position);
                }
            }
        });

    }

    public void showClockIn(final PlanHolder holder, final MySQLiteDBAccessUtil mySQLiteDBAccessUtil, final PlanBean bean,final int position){
        final Dialog dialog = new Dialog(mContext);
        //去掉title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.not_time_clock);
        window.setBackgroundDrawableResource(android.R.color.transparent);

        // 设置宽高
        window.setLayout(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimScale);
        window.setGravity(Gravity.CENTER);

        Button btn_clock_in = (Button)window.findViewById(R.id.btn_clock_in);
        btn_clock_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                showClockInConfirm(holder,mySQLiteDBAccessUtil,bean,position);
            }
        });

    }

    public void showClockInConfirm(final PlanHolder holder, final MySQLiteDBAccessUtil mySQLiteDBAccessUtil, final PlanBean bean,final int position){
        final Dialog dialog = new Dialog(mContext);
        //去掉title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
        Window window = dialog.getWindow();
        // 设置布局
        window.setContentView(R.layout.clock_confirm);
        window.setBackgroundDrawableResource(android.R.color.transparent);

        // 设置宽高
        window.setLayout(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出的动画效果
        window.setWindowAnimations(R.style.AnimScale);
        window.setGravity(Gravity.CENTER);

        Button btn_clock_confirm = (Button)window.findViewById(R.id.btn_clock_confirm);
        btn_clock_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tv_item_chock_in_num.setText(bean.getClockInNum()+1+"次");
                dialog.cancel();
                mySQLiteDBAccessUtil.open();
                mySQLiteDBAccessUtil.updateRecord(bean.getPlanId());
                mySQLiteDBAccessUtil.planPlusClockNum(bean.getPlanId());
                mySQLiteDBAccessUtil.close();
                GradientDrawable myGrad = (GradientDrawable) holder.ll_layout.getBackground();
                myGrad.setColor(Color.parseColor(bean.getColor()));
                recordState[position]=true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private void setMenuImg(PlanHolder holder, PlanBean bean){
        switch (bean.getIcon()){
            case "1":
                holder.img_item_icon.setBackground(mContext.getResources().getDrawable(R.mipmap.icon01_cat));
                break;
            case "2":
                holder.img_item_icon.setBackground(mContext.getResources().getDrawable(R.mipmap.icon02_xuexi));
                break;
            case "3":
                holder.img_item_icon.setBackground(mContext.getResources().getDrawable(R.mipmap.icon03_gongzuo));
                break;
            case "4":
                holder.img_item_icon.setBackground(mContext.getResources().getDrawable(R.mipmap.icon04_shoushen));
                break;
            case "5":
                holder.img_item_icon.setBackground(mContext.getResources().getDrawable(R.mipmap.icon05_shoubing));
                break;
            default:
                break;
        }
    }


    public class PlanHolder extends RecyclerView.ViewHolder {

        private LinearLayout ll_layout;
        private SwipeMenuView swipe_menu;
        private TextView tv_item_finish,tv_item_edit,tv_item_delete,tv_item_plan_name,tv_item_chock_in_num;
        private ImageView img_item_icon;

        public PlanHolder(View itemView) {
            super(itemView);
            swipe_menu = (SwipeMenuView) itemView.findViewById(R.id.swipe_menu);
            tv_item_finish = (TextView) itemView.findViewById(R.id.tv_item_finish);
            tv_item_edit = (TextView) itemView.findViewById(R.id.tv_item_edit);
            tv_item_delete = (TextView) itemView.findViewById(R.id.tv_item_delete);
            tv_item_plan_name = (TextView) itemView.findViewById(R.id.tv_item_plan_name);
            tv_item_chock_in_num = (TextView) itemView.findViewById(R.id.tv_item_chock_in_num);
            img_item_icon = (ImageView) itemView.findViewById(R.id.img_item_icon);
            ll_layout = (LinearLayout) itemView.findViewById(R.id.ll_layout);
        }
    }
}
