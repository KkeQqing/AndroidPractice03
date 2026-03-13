package com.example.androidpractice03;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // 必须导入这个 Toolbar

public class MenuDemoActivity extends AppCompatActivity {

    private TextView tvInfo;
    private TextView contextMenuArea;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_demo);

        // 1. 初始化 Toolbar 并设置为 ActionBar (关键步骤！)
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 2. 初始化其他视图
        contextMenuArea = findViewById(R.id.context_menu_area);

        // 3. 为特定视图注册上下文菜单
        registerForContextMenu(contextMenuArea);
    }

    // --- 1. 创建选项菜单 (Options Menu) ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 填充菜单资源
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    // --- 2. 处理选项菜单点击 (包括子菜单) ---
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this, "点击了：设置", Toast.LENGTH_SHORT).show();
            tvInfo.setText("当前操作：设置");
            return true;
        } else if (id == R.id.action_share) {
            // 子菜单项
            Toast.makeText(this, "点击了：分享 (子菜单)", Toast.LENGTH_SHORT).show();
            tvInfo.setText("当前操作：分享");
            return true;
        } else if (id == R.id.action_feedback) {
            // 子菜单项
            Toast.makeText(this, "点击了：反馈 (子菜单)", Toast.LENGTH_SHORT).show();
            tvInfo.setText("当前操作：反馈");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // --- 3. 创建上下文菜单 (Context Menu) ---
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("请选择操作");
    }

    // --- 4. 处理上下文菜单点击 ---
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.context_edit) {
            tvInfo.setText("执行了：编辑操作");
            return true;
        } else if (id == R.id.context_delete) {
            tvInfo.setText("执行了：删除操作");
            return true;
        } else if (id == R.id.context_details) {
            tvInfo.setText("执行了：查看详情操作");
            return true;
        }

        return super.onContextItemSelected(item);
    }
}