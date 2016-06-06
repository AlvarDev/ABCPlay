package com.alvardev.android.abcplay.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvardev.android.abcplay.R;
import com.alvardev.android.abcplay.entities.UserEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>
        implements View.OnClickListener{

    private List<UserEntity> mData;
    private View.OnClickListener listener;
    private Context context;

    public StudentAdapter(List<UserEntity> myData, Context context) {
        this.mData = myData;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivDino;
        public TextView tvName;
        public TextView tvColorsGrp;
        public TextView tvColorsInd;
        public TextView tvShapesGrp;
        public TextView tvShapesInd;
        public TextView tvVowelsGrp;
        public TextView tvVowelsInd;
        public TextView tvFinalScore;

        public ViewHolder(View v) {
            super(v);
            ivDino = (ImageView)v.findViewById(R.id.iv_dino);
            tvName = (TextView)v.findViewById(R.id.tv_name);
            tvColorsGrp = (TextView)v.findViewById(R.id.tv_colors_grp);
            tvColorsInd = (TextView)v.findViewById(R.id.tv_colors_ind);
            tvShapesGrp = (TextView)v.findViewById(R.id.tv_shapes_grp);
            tvShapesInd = (TextView)v.findViewById(R.id.tv_shapes_ind);
            tvVowelsGrp = (TextView)v.findViewById(R.id.tv_vowels_grp);
            tvVowelsInd = (TextView)v.findViewById(R.id.tv_vowels_ind);
            tvFinalScore = (TextView)v.findViewById(R.id.tv_final_score);
        }
    }

    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_student, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Picasso.with(this.context).load(mData.get(position).getDino())
        //        .placeholder(R.drawable.img_empty)
        //        .error(R.drawable.img_empty)
        //        .into(holder.ivIcoMedia);

        String colorGrp = "Grupal: " + mData.get(position).getsColorsGroup();
        String colorInd = "Individual: " + mData.get(position).getsColorsSingle();
        String shapeGrp = "Grupal: " + mData.get(position).getsShapesGroup();
        String shapeInd = "Individual: " + mData.get(position).getsShapesSingle();
        String vowelGrp = "Grupal: " + mData.get(position).getsVowelsGroup();
        String vowelInd = "Individual: " + mData.get(position).getsVowelsSingle();

        holder.tvName.setText(mData.get(position).getName());
        holder.tvColorsGrp.setText(colorGrp);
        holder.tvColorsInd.setText(colorInd);
        holder.tvShapesGrp.setText(shapeGrp);
        holder.tvShapesInd.setText(shapeInd);
        holder.tvVowelsGrp.setText(vowelGrp);
        holder.tvVowelsInd.setText(vowelInd);
        holder.tvFinalScore.setText(mData.get(position).getFinalScore());
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
