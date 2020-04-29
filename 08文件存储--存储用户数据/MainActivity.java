package com.example.savefile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	private EditText et_input,et_filename;
	private TextView tv_msg;
	private Button btn_write,btn_read,btn_writesd,btn_readsd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        btn_write.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String fileName=et_filename.getText().toString().trim();
				String content=et_input.getText().toString().trim();
				FileOutputStream fos;
				try {
					fos=openFileOutput(fileName, MODE_PRIVATE);
					fos.write(content.getBytes());
					fos.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
        
        btn_read.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String fileName=et_filename.getText().toString().trim();
				String content="";
				FileInputStream fis;
				try {
					fis=openFileInput(fileName);
					byte[] buffer=new byte[fis.available()];
					fis.read(buffer);
					content=new String(buffer);
					tv_msg.setText(content);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
        
        btn_writesd.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String state=Environment.getExternalStorageState();
				if (state.equals(Environment.MEDIA_MOUNTED)) {
					File SDpath=Environment.getExternalStorageDirectory();
					String fileName=et_filename.getText().toString().trim();
					File file=new File(SDpath, fileName);
					String content=et_input.getText().toString().trim();
					FileOutputStream fos;
					try {
						fos=new FileOutputStream(file);
						fos.write(content.getBytes());
						fos.close();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		}); 
        
        btn_readsd.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String state=Environment.getExternalStorageState();
				if (state.equals(Environment.MEDIA_MOUNTED)) {
					File SDpath=Environment.getExternalStorageDirectory();
					String fileName=et_filename.getText().toString().trim();
					File file=new File(SDpath, fileName);
					
					String content="";
					FileInputStream fis;
					try {
						fis=new FileInputStream(file);
						BufferedReader br=new BufferedReader(new InputStreamReader(fis));
						content=br.readLine();
						tv_msg.setText(content);
						fis.close();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		}); 
    }
    
    private void initView() {
		et_filename=(EditText)findViewById(R.id.et_filename);
		et_input=(EditText)findViewById(R.id.et_input);
		tv_msg=(TextView)findViewById(R.id.tv_msg);
		btn_read=(Button)findViewById(R.id.btn_read);
		btn_readsd=(Button)findViewById(R.id.btn_readsd);
		btn_write=(Button)findViewById(R.id.btn_write);
		btn_writesd=(Button)findViewById(R.id.btn_writesd);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
