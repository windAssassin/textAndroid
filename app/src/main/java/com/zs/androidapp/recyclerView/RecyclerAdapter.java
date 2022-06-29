package com.zs.androidapp.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zs.androidapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> mData;

    public RecyclerAdapter(List<String> data){
        this.mData = data;
    }

    @NonNull
    @android.support.annotation.NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @android.support.annotation.NonNull
                                                                     ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_child_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @android.support.annotation.NonNull
                                             RecyclerAdapter.ViewHolder holder, int position) {
        holder.mTitleView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleView;

        public ViewHolder(@NonNull @android.support.annotation.NonNull View itemView) {
            super(itemView);
            mTitleView = itemView.findViewById(R.id.title);
        }
    }
}
