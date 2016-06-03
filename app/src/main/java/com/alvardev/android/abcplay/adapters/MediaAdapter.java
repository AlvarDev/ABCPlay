package com.alvardev.android.abcplay.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alvardev.android.abcplay.R;
import com.alvardev.android.abcplay.entities.MediaEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder>
        implements View.OnClickListener{

    private List<MediaEntity> mData;
    private View.OnClickListener listener;
    private Context context;

    public MediaAdapter(List<MediaEntity> myData, Context context) {
        this.mData = myData;
        this.context = context;
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
        Picasso.with(this.context).load(mData.get(position).getIco())
                .placeholder(R.drawable.img_logo_main)
                .error(R.drawable.img_logo_main)
                .into(holder.ivIcoMedia);
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
