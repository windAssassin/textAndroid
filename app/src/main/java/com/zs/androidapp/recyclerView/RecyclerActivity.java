package com.zs.androidapp.recyclerView;

import android.os.Bundle;

import com.zs.androidapp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

class RecyclerActivity extends ComponentActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        mRecyclerView = findViewById(R.id.text_recycler);
        setData();
    }


    private void setData(){
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            data.add(String.valueOf(i));
        }
        RecyclerAdapter adapter = new RecyclerAdapter(data);
        LinearLayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

    }

}
