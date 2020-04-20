package com.example.homwork02;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MyOwnSpinnerActivity extends Activity {
    Spinner spinner_1;//选择男歌手
    private ArrayAdapter<String> myadapter;
    private List<String> selectedsingers;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("选择歌手");
        setContentView(R.layout.activity_my_own_spinner);//采用spinner.xml布局
        spinnerfunction();
    }
    private static final String[] malesingers = { "罗大佑" ,"李宗盛", "刘文正", "费翔", "费玉清"};
    private void spinnerfunction() {
        spinner_1 = (Spinner) findViewById(R.id.spinner_1);
        selectedsingers = new ArrayList<String>();
        for (int i = 0; i < malesingers.length; i++) {
            selectedsingers.add(malesingers[i]);//给列表赋字符数组中的值
        }
        myadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, selectedsingers);
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(myadapter);
    }
}