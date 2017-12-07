package com.leo.scrollgradeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.leo.scrollgradeview.view.ScrollViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ScrollViewGroup svg = (ScrollViewGroup) findViewById(R.id.svg);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, svg.getSelectedValue(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
