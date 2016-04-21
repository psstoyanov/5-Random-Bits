package com.example.paskalstoyanov.a5randombits;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticlesHolder extends RecyclerView.ViewHolder {
    View mView;

    public ArticlesHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }



    public void setTitle(String name) {
        TextView field = (TextView) mView.findViewById(R.id.article_title);
        field.setText(name);
    }

    public void setImage(String imageUrl)
    {
        ImageView field = (ImageView) mView.findViewById(R.id.thumbnail);
    }

}
