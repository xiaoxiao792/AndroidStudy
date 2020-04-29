package com.example.homework04;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PersonActivity extends AppCompatActivity {
    // 声明对数据库进行增删改查操作的DBAdapter类
    private DBAdapter dbAdapter;
    // 声明学生activity中的按钮控件：添加课程、ID查询、ID删除
    private Button btnDataAdd, btnDataDeleteAll, btnIdDelete;
    // 声明学生activity中各个属性对应编辑框
    private EditText nameEdit, classEdit, personIdEdit, IdEdit;
    // 声明提示显示框lableview和数据显示框display
    private TextView lableView, display;

    private int id;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        //接收CourseActivity传入的课程id数据
        //Intent intent = PersonActivity.this.getIntent();
        //Bundle bundle = intent.getExtras();
        //id = bundle.getInt("courseID");
        //初始化
        setupView();
        // 获得实例
        dbAdapter = new DBAdapter(this);
        // 打开数据库
        dbAdapter.open();
        showAll();
        // 添加一条数据.按钮
        btnDataAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isRight()) {
                    return;
                }
                Person person = new Person();
                person.id = Integer.parseInt(personIdEdit.getText().toString());
                person.myName = nameEdit.getText().toString();
                person.myClass = classEdit.getText().toString();
                //对相应的课程插入数据
                long colunm = dbAdapter.insert(person, id);
                if (colunm == -1) {
                    lableView.setText("添加错误");
                } else {
                    lableView.setText("成功添加数据 , ID: " + String.valueOf(colunm));
                }
                showAll();
            }
        });
        // 删除全部数据
        btnDataDeleteAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dbAdapter.deleteAllData(id);
                lableView.setText("数据全部删除");
                display.setText("");
            }
        });
        // ID删除
        btnIdDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!right()) {
                    return;
                }
                int id = Integer.parseInt(IdEdit.getText().toString());
                dbAdapter.deleteOneData(id);
                showAll();
            }
        });
    }
    //判断idedit是否输入数据
    private boolean right() {
        if (IdEdit.getText().length() == 0) {
            lableView.setText("请输入符合场常理的数据");
            return false;
        } else {
            return true;
        }
    }
    //判断person相应的属性输入框是否输入数据
    private boolean isRight() {
        if (classEdit.getText().length() == 0
                || nameEdit.getText().length() == 0
                || personIdEdit.getText().length() == 0) {
            lableView.setText("请输入符合场常理的数据");
            return false;
        } else {
            return true;
        }
    }

    // 对学生activity进行初始化，获得xml中定义的相应的控件
    private void setupView() {
        btnDataAdd = (Button) findViewById(R.id.person_btn_dataAdd);
        btnDataDeleteAll = (Button) findViewById(R.id.person_btn_dataDeleteAll);

        btnIdDelete = (Button) findViewById(R.id.person_ID_delete);

        nameEdit = (EditText) findViewById(R.id.person_nameEdit);
        classEdit = (EditText) findViewById(R.id.person_classEdit);
        personIdEdit = (EditText) findViewById(R.id.person_idEdit);
        IdEdit = (EditText) findViewById(R.id.person_ID_entry);

        lableView = (TextView) findViewById(R.id.person_lable);
        display = (TextView) findViewById(R.id.person_display);
    }

    // 显示课程号为id的所有学生信息
    private void showAll() {
        Person[] peoples = dbAdapter.queryAllData(id);
        if (peoples == null) {
            lableView.setText("数据库里一个person也没有");
            display.setText("");
            return;
        }
        lableView.setText("数据库:");
        String result = "";
        for (int i = 0; i < peoples.length; i++) {
            result += peoples[i].toString() + "\n";
        }
        display.setText(result);
    }

}