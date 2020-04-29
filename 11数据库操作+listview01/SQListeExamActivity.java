package com.example.helloworld;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQListeExamActivity extends AppCompatActivity {

    private EditText et_name,et_number;
    private Button btn_add,btn_del,btn_update,btn_select,btn_all;
    private ListView lv_all;
    private PersonSQLiteOpenHelper helper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqliste_exam);
        initView();
        helper=new PersonSQLiteOpenHelper(SQListeExamActivity.this);
        btn_add.setOnClickListener(new MyButton());
        btn_del.setOnClickListener(new MyButton());
        btn_update.setOnClickListener(new MyButton());
        btn_select.setOnClickListener(new MyButton());
        btn_all.setOnClickListener(new MyButton());
    }
    private class MyButton implements View.OnClickListener{

        public void onClick(View arg0) {
            switch (arg0.getId()) {
                case R.id.btn_add:
                    long num=add(et_name.getText().toString().trim(),
                            et_number.getText().toString().trim());
                    Toast.makeText(SQListeExamActivity.this, "添加成功"+num, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_del:
                    int num1=delete(et_name.getText().toString().trim());
                    Toast.makeText(SQListeExamActivity.this, "删除成功"+num1, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_update:
                    int num2=update(et_name.getText().toString().trim(),
                            et_number.getText().toString().trim());
                    Toast.makeText(SQListeExamActivity.this, "修改成功"+num2, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_select:
                    Map<String, String> userMap=query(et_name.getText().toString().trim());
                    et_number.setText(userMap.get("number"));
                    break;
                case R.id.btn_all:
                    SimpleAdapter adapter=new SimpleAdapter(SQListeExamActivity.this,
                            select_all(),
                            android.R.layout.simple_list_item_2,
                            new String[]{"name","number"},
                            new int[]{android.R.id.text1,android.R.id.text2});
                    lv_all.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }

    }

    public Map<String, String> query(String name) {
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor c=db.query("person", null, "name=?", new String[]{name},null, null, null);
        Map<String, String> map=new HashMap<String, String>();
        if(c.moveToNext()){
            map.put("name", c.getString(c.getColumnIndex("name")));
            map.put("number", c.getString(c.getColumnIndex("number")));
        }
        return map;

    }

    public int update(String name, String number) {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("number", number);
        int num=db.update("person", values, "name=?", new String[]{name});
        db.close();
        return num;

    }

    public int delete(String name) {
        SQLiteDatabase db=helper.getWritableDatabase();
        int num=db.delete("person", "name=?", new String[]{name});
        db.close();
        return num;
    }

    public long add(String name,String number){

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("number", number);
        long id = db.insert("person", null, values);
        db.close();
        return id;

    }


    public List<Map<String, String>> select_all() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c=db.query("person", null,  null,  null,  null,  null,  null);
        List<Map<String, String>> list=new ArrayList<Map<String, String>>();
        while (c.moveToNext()) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", c.getString(c.getColumnIndex("name")));
            map.put("number", c.getString(c.getColumnIndex("number")));
            list.add(map);
        }
        c.close();
        db.close();
        return list;

    }
    private void initView() {
        et_name=(EditText)findViewById(R.id.et_name);
        et_number=(EditText)findViewById(R.id.et_number);
        btn_add=(Button)findViewById(R.id.btn_add);
        btn_del=(Button)findViewById(R.id.btn_del);
        btn_update=(Button)findViewById(R.id.btn_update);
        btn_select=(Button)findViewById(R.id.btn_select);
        btn_all=(Button)findViewById(R.id.btn_all);
        lv_all=(ListView)findViewById(R.id.lv_all);
    }
}

