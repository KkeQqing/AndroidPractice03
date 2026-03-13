package com.example.androidpractice03;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity implements ContentAdapter.Callback {

    // 1. 定义数据源 (与你 MainActivity 中的数据保持一致)
    private String[] names = new String[] {"虎头", "弄玉", "李清照", "李白"};
    private String[] descs = new String[] {"可爱的小孩", "一个擅长音乐的女孩", "一个擅长文学的女性", "浪漫主义诗人"};
    private int[] imgIds = new int[] {
            R.drawable.triger,
            R.drawable.nongyu,
            R.drawable.qingzhao,
            R.drawable.libai
    };

    private List<Map<String, Object>> listItems;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 2. 绑定布局文件 (对应你提供的 activity_listview.xml)
        setContentView(R.layout.activity_listview);

        // 3. 初始化数据和列表
        initData();
        initListView();
    }

    /**
     * 准备列表数据
     */
    private void initData() {
        listItems = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> item = new HashMap<>();
            // 这里的 key ("imgId", "name", "descs") 必须与 ContentAdapter 中获取数据的 key 一致
            item.put("imgId", imgIds[i]);
            item.put("name", names[i]);
            item.put("desc", descs[i]);
            listItems.add(item);
        }
    }

    /**
     * 初始化 ListView 并设置适配器
     */
    private void initListView() {
        // 4. 找到 XML 中的 ListView (ID: mylist)
        listView = findViewById(R.id.mylist);

        // 5. 创建适配器
        // 参数说明: Context上下文, 数据源, 回调接口实现(当前 Activity)
        ContentAdapter adapter = new ContentAdapter(this, listItems, this);

        // 6. 设置适配器
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 弹出对话框
                ShowDialog(names[position] + "被单击了");
                // 显示 Toast
                Toast.makeText(ListViewActivity.this, names[position] + "被单击了", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, names[position] + "被选中了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 空实现
            }
        });

    }

    /**
     * 实现 Callback 接口中的 click 方法
     * 当列表项被点击时触发
     */
    @Override
    public void click(View v) {
        if (v.getTag() instanceof Integer) {
            int position = (Integer) v.getTag();

            // 构造提示信息
            String msg = "ListView 内部的按钮被点击了！\n位置-->" + position +
                    "\n内容-->" + listItems.get(position).get("name");

            Toast.makeText(ListViewActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public void ShowDialog(String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("简单对话框")

                .setMessage(content); // 使用传入的内容

        setPositiveButton(builder);
        setNegativeButton(builder)
                .create()
                .show();
    }

    private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder) {
        return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ListViewActivity.this, "单击了【确定】按钮！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder) {
        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ListViewActivity.this, "单击了【取消】按钮！", Toast.LENGTH_SHORT).show();
            }
        });
    }

}