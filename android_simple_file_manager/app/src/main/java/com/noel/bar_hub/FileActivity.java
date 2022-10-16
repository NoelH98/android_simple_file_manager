package com.noel.bar_hub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class FileActivity extends AppCompatActivity {

    private RecyclerView rc;
    private TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        rc = findViewById(R.id.recycler);
        tx = findViewById(R.id.noFiles);

        String path = getIntent().getStringExtra("path");

        File root = new File(path);
        File[] filesAndFolders = root.listFiles();

        if(filesAndFolders==null || filesAndFolders.length == 0){
            tx.setVisibility(View.VISIBLE);
            return;
        }

        tx.setVisibility(View.INVISIBLE);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(new Adapter(getApplicationContext(),filesAndFolders));
    }
}
