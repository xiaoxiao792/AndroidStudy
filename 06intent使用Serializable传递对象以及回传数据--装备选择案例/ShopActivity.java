package cn.itcast.select;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.itcast.domain.ItemInfo;

public class ShopActivity extends Activity implements OnClickListener {

	private ItemInfo itemInfo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		itemInfo = new ItemInfo("金剑", 100, 20, 20);
		findViewById(R.id.rl).setOnClickListener(this);
		TextView mLifeTV = (TextView) findViewById(R.id.tv_life);
		TextView mNameTV = (TextView) findViewById(R.id.tv_name);
		TextView mSpeedTV = (TextView) findViewById(R.id.tv_speed);
		TextView mAttackTV = (TextView) findViewById(R.id.tv_attack);
		// TextView显示字符串,这里传入int值编译不会报错,运行会出错
		mLifeTV.setText("生命值+" + itemInfo.getLife());
		mNameTV.setText(itemInfo.getName() + "");
		mSpeedTV.setText("敏捷度+" + itemInfo.getSpeed());
		mAttackTV.setText("攻击力+" + itemInfo.getAcctack());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl:
			Intent intent = new Intent();
			intent.putExtra("equipment", itemInfo);
			setResult(1, intent);
			finish();
			break;
		}
	}
}
