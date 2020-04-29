package com.example.homework04;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CourseActivity extends AppCompatActivity {
    // 声明对数据库进行增删改查操作的DBAdapter类
    private DBAdapter dbAdapter;
    // 声明课程activity中的按钮控件：添加课程、ID查询、ID删除
    private Button btnDataAdd, btnIDQuery, btnIdDelete, btnIdAlter;
    // 声明课程activity中各个属性对应编辑框
    private EditText courseIdEdit, nameEdit, objEdit, phoneEdit, IdEdit;
    // 声明提示显示框lableview和数据显示框display
    private TextView lableView, display;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        // 对activity进行初始化，获得相应的控件
        setupView();
        // 获得数据库实例
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        // 显示数据库中所有的course
        showAll();
        // 添加一条数据.按钮
        btnDataAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isRight()) {
                    return;
                }
                Course course = new Course();
                // 从编辑框中获得相应的属性值
                course.id = Integer.parseInt(courseIdEdit.getText().toString());
                course.name = nameEdit.getText().toString();
                course.obj = objEdit.getText().toString();
                course.phone = phoneEdit.getText().toString();
                // 插入一个数据，并获得插入的row标识
                long colunm = dbAdapter.insert(course);
                if (colunm == -1) {
                    lableView.setText("添加错误");
                } else {
                    lableView.setText("成功添加数据 , ID: " + String.valueOf(colunm));
                }
                showAll();
            }
        });
        // ID查询操作
        btnIDQuery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!right()) {
                    return;
                }
                int id = Integer.parseInt(IdEdit.getText().toString());
                // 定义intent，实现activity跳转，并通过bundle携带数据将对应的课程id传入PersonActivity.java中
                Intent intent = new Intent();
                intent.setClass(CourseActivity.this, PersonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("courseID", id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        // ID删除
        btnIdDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!right()) {
                    return;
                }
                int id = Integer.parseInt(IdEdit.getText().toString());
                // 删除对应id的课程
                dbAdapter.deleteOneCourse(id);
                showAll();
            }
        });
        // ID修改
        btnIdAlter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!right() || nameEdit.getText().length() != 0
                        || objEdit.getText().length() != 0
                        || phoneEdit.getText().length() != 0) {
                    return;
                }
                int id = Integer.parseInt(IdEdit.getText().toString());
                // 修改对应id的课程
                Course course = new Course();
                course.name = nameEdit.getText().toString();
                course.obj = objEdit.getText().toString();
                course.phone = phoneEdit.getText().toString();
                dbAdapter.updateData(id, course);
                showAll();
            }
        });
    }

    private boolean right() {
        if (IdEdit.getText().length() == 0) {
            lableView.setText("请输入符合场常理的数据");
            return false;
        } else {
            return true;
        }
    }

    private boolean isRight() {
        if (courseIdEdit.getText().length() == 0
                || nameEdit.getText().length() == 0
                || objEdit.getText().length() == 0
                || phoneEdit.getText().length() == 0) {
            lableView.setText("请输入符合场常理的数据");
            return false;
        } else {
            return true;
        }
    }

    // 对activity进行初始化
    private void setupView() {
        btnDataAdd = (Button) findViewById(R.id.course_btn_dataAdd);
        btnIDQuery = (Button) findViewById(R.id.course_ID_query);
        btnIdDelete = (Button) findViewById(R.id.course_ID_delete);
        btnIdAlter = (Button) findViewById(R.id.course_ID_alter);

        nameEdit = (EditText) findViewById(R.id.course_nameEdit);
        courseIdEdit = (EditText) findViewById(R.id.course_idEdit);
        objEdit = (EditText) findViewById(R.id.course_objEdit);
        phoneEdit = (EditText) findViewById(R.id.course_phoneEdit);
        IdEdit = (EditText) findViewById(R.id.course_ID_entry);

        lableView = (TextView) findViewById(R.id.course_lable);
        display = (TextView) findViewById(R.id.course_display);
    }

    private void showAll() {
        Course[] courses = dbAdapter.queryAllCourse();
        if (courses == null) {
            lableView.setText("数据库里一个course也没有");
            display.setText("");
            return;
        }
        lableView.setText("数据库:");
        String result = "";
        for (int i = 0; i < courses.length; i++) {
            result += courses[i].toString() + "\n";
        }
        display.setText(result);
    }
}
