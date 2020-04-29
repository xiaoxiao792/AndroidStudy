package cn.itcast.product;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.itcast.product.bean.Account;
import cn.itcast.product.dao.AccountDao;

public class MainActivity extends Activity {
	// ��Ҫ��������ݼ���
	private List<Account> list;
	// ���ݿ���ɾ�Ĳ������
	private AccountDao dao;
	// ����������EditText
	private EditText nameET;
	// �������EditText
	private EditText balanceET;
	// ������
	private MyAdapter adapter;
	// ListView
	private ListView accountLV;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
         //��ʼ���ؼ�
		initView();
		dao = new AccountDao(this);
		// �����ݿ��ѯ����������
		list = dao.queryAll();
		adapter = new MyAdapter();
		accountLV.setAdapter(adapter);// ��ListView���������(�Զ�������������Ŀ)
	}

	// ��ʼ���ؼ�
	private void initView() {
		accountLV = (ListView) findViewById(R.id.accountLV);
		nameET = (EditText) findViewById(R.id.nameET);
		balanceET = (EditText) findViewById(R.id.balanceET);
		// ��Ӽ�����, ������Ŀ����¼�
		accountLV.setOnItemClickListener(new MyOnItemClickListener());
	}
     //activity_mian.xml ��ӦImageView�ĵ���¼������ķ���
	public void add(View v) {
		String name = nameET.getText().toString().trim();
		String balance = balanceET.getText().toString().trim();
          //��Ŀ���� balance.equals(����) �����0 
          //���balance ���ǿ��ַ��� ���������ת��
		Account a = new Account(name, balance.equals("") ? 0
				: Integer.parseInt(balance));
		dao.insert(a);                      // �������ݿ�
		list.add(a);                        // ���뼯��
		adapter.notifyDataSetChanged(); // ˢ�½���
         // ѡ�����һ��
		accountLV.setSelection(accountLV.getCount() - 1); 
		nameET.setText("");
		balanceET.setText("");
	}	
	// �Զ���һ��������(������װ��ListView�Ĺ���)
	private class MyAdapter extends BaseAdapter {
		public int getCount() {                   // ��ȡ��Ŀ����
			return list.size();
		}

		public Object getItem(int position) { // ����λ�û�ȡ����
			return list.get(position);
		}

		public long getItemId(int position) { // ����λ�û�ȡid
			return position;
		}

        // ��ȡһ����Ŀ��ͼ
		public View getView(int position, View convertView, ViewGroup parent) { 
             // ����convertView
			View item = convertView != null ? convertView : View.inflate(
					getApplicationContext(), R.layout.item, null);
           // ��ȡ����ͼ�е�TextView		
        	TextView idTV = (TextView) item.findViewById(R.id.idTV);	
		TextView nameTV = (TextView) item.findViewById(R.id.nameTV);
		TextView balanceTV = (TextView) item.findViewById(R.id.balanceTV);
          // ���ݵ�ǰλ�û�ȡAccount����
          final Account a = list.get(position); 
          // ��Account�����е����ݷŵ�TextView��
		 idTV.setText(a.getId() + ""); 
		 nameTV.setText(a.getName());
		 balanceTV.setText(a.getBalance() + "");
		 ImageView upIV = (ImageView) item.findViewById(R.id.upIV);
		 ImageView downIV = (ImageView) item.findViewById(R.id.downIV);
		 ImageView deleteIV = (ImageView) item.findViewById(R.id.deleteIV);
           //���ϼ�ͷ�ĵ���¼������ķ���
		 upIV.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					a.setBalance(a.getBalance() + 1); // �޸�ֵ
					notifyDataSetChanged(); // ˢ�½���
					dao.update(a); // �������ݿ�
				}
		 });
          //���¼�ͷ�ĵ���¼������ķ���
		downIV.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					a.setBalance(a.getBalance() - 1);
					notifyDataSetChanged();
					dao.update(a);
				}
		});
          //ɾ��ͼƬ�ĵ���¼������ķ���
		 deleteIV.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
                       //ɾ������֮ǰ���ȵ���һ���Ի���
					android.content.DialogInterface.OnClickListener listener = 
							new android.content.DialogInterface.
                           OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							list.remove(a);          // �Ӽ�����ɾ��
							dao.delete(a.getId()); // �����ݿ���ɾ��
							notifyDataSetChanged();// ˢ�½���
						}
					};
					Builder builder = new Builder(MainActivity.this); // �����Ի���
					builder.setTitle("ȷ��Ҫɾ����?");                    // ���ñ���
                        // ����ȷ����ť���ı��Լ�������
					builder.setPositiveButton("ȷ��", listener);	
				     builder.setNegativeButton("ȡ��", null);         // ����ȡ����ť
					builder.show(); // ��ʾ�Ի���
				}
			});
			return item;
		}
	}
         //ListView��Item����¼�
	private class MyOnItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
          // ��ȡ���λ���ϵ�����
		Account a = (Account) parent.getItemAtPosition(position);	
		Toast.makeText(getApplicationContext(), a.toString(),
					Toast.LENGTH_SHORT).show();
		}
	}
}

