package com.example.androidpractice03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.androidpractice03.ContentAdapter.Callback;

public class MainActivity extends AppCompatActivity implements Callback {

    private Button btnGoToListView;

    String[] flowers = {
            "冬天有梅花-ArrayAdapter实现",
            "夏天有荷花",
            "春天有桃花",
            "秋天有什么花？"
    };
    String[] languages = {
            "Java",
            "Jsp",
            "Asp",
            "PHP"
    };

    private  String[] names = new String[] {"虎头","弄玉","李清照","李白"};
    private String [] descs = new String[] {"可爱的小孩", "一个擅长音乐的女孩", "一个擅长文学的女性", "浪漫主义诗人"};
    private int[] imgIds = new int[] {R.drawable.triger, R.drawable.nongyu,R.drawable.qingzhao,R.drawable.libai};
    List<Map<String,Object>> listItems=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ListView listView2 = findViewById(R.id.listView2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,flowers);
        listView2.setAdapter(adapter);

        ListView listView3 = findViewById(R.id.listView3);
        ArrayAdapter<String> checkedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked,languages);
        listView3.setAdapter(checkedAdapter);
        listView3.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        btnGoToListView = findViewById(R.id.btnGoToListView);
        btnGoToListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);

                // (可选) 如果需要传递数据，可以使用 intent.putExtra("key", value);

                // 启动 Activity
                startActivity(intent);
            }
        });

        Button btnGoToMenu = findViewById(R.id.btn_go_to_menu);
        btnGoToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个 Intent 来启动 MenuDemoActivity
                Intent intent = new Intent(MainActivity.this, MenuDemoActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void InitData(){
        listItems = new ArrayList<Map<String,Object>>();
        for(int i = 0;i<names.length;i++){
            Map<String,Object> listItem = new HashMap<String, Object>();
            listItem.put("imgId",imgIds[i]);
            listItem.put("name",names[i]);
            listItem.put("descs",descs[i]);
            listItems.add(listItem);
        }
    }

    private void Init(){
        ListView list =findViewById(R.id.mylist);
        list.setAdapter(new ContentAdapter(this,listItems,this));
    }

    @Override
    public void click(View v) {

    }
}