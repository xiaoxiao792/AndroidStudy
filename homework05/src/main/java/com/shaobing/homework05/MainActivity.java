package com.shaobing.homework05;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button1,button2,button3;
    private static final int ITEM_NEW_Male1 = Menu.FIRST;
    private static final int ITEM_NEW_Male2 = Menu.FIRST+1;
    private static final int ITEM_NEW_Female1 = Menu.FIRST+2;
    private static final int ITEM_NEW_Female2 = Menu.FIRST+3;
    private static final int ITEM_NEW_Combo1 = Menu.FIRST+4;
    private static final int ITEM_NEW_Combo2 = Menu.FIRST+5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.button1);
        this.registerForContextMenu(findViewById(R.id.button1));
        button2=(Button)findViewById(R.id.button1);
        this.registerForContextMenu(findViewById(R.id.button2));
        button3=(Button)findViewById(R.id.button1);
        this.registerForContextMenu(findViewById(R.id.button3));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {
            case ITEM_NEW_Male1:
                setTitle("您选择的男歌手是："+item.getTitle());
                break;
            case ITEM_NEW_Male2:
                setTitle("您选择的男歌手是："+item.getTitle());
                break;
            case ITEM_NEW_Female1:
                setTitle("您选择的女歌手是："+item.getTitle());
                break;
            case ITEM_NEW_Female2:
                setTitle("您选择的女歌手是："+item.getTitle());
                break;
            case ITEM_NEW_Combo1:
                setTitle("您选择的组合是："+item.getTitle());
                break;
            case ITEM_NEW_Combo2:
                setTitle("您选择的组合是："+item.getTitle());
                break;
            default:
                break;
        }
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        menu.setHeaderIcon(R.drawable.ic_launcher);
        if(v==findViewById(R.id.button1)){
            menu.add(0, ITEM_NEW_Male1, 0, "林俊杰");
            menu.add(0, ITEM_NEW_Male2, 0, "张杰");
        }
        if(v==findViewById(R.id.button2)){
            menu.add(0, ITEM_NEW_Female1 , 0, "邓紫琪");
            menu.add(0, ITEM_NEW_Female2, 0, "张含韵");
        }
        if(v==findViewById(R.id.button3)){
            menu.add(0, ITEM_NEW_Combo1, 0, "飞轮海");
            menu.add(0, ITEM_NEW_Combo2, 0, "甲壳虫");
        }

    }
}
