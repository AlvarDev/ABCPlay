package com.alvardev.android.abcplay.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alvardev.android.abcplay.R;
import com.alvardev.android.abcplay.entities.MediaEntity;

import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder>
        implements View.OnClickListener{

    private List<MediaEntity> mData;
    private View.OnClickListener listener;

    public MediaAdapter(List<MediaEntity> myData) {
        this.mData = myData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivIcoMedia;

        public ViewHolder(View v) {
            super(v);
            ivIcoMedia = (ImageView)v.findViewById(R.id.iv_ico_media);
        }
    }

    @Override
    public MediaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_media_row, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ivIcoMedia.setImageResource(mData.get(position).getIco());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onClick(v);
    }

}
