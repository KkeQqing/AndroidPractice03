package com.example.androidpractice03;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}