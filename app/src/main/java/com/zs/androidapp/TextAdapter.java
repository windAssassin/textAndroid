package com.zs.androidapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangshen on 2022/6/14.
 */

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.InnerHolder> {
    @Override
    public InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(InnerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(View itemView) {
            super(itemView);
        }
    }
}
