package com.example.paskalstoyanov.a5randombits;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by paskalstoyanov on 19/04/16.
 */
public class ArticleListAdapter extends FirebaseListAdapter<Articles> {

    Context mContext;

    public ArticleListAdapter(Query mRef, Activity activity, int layout)
    {
        //super(ref, Articles.class,layout, activity);
        super(mRef, Articles.class,layout, activity);
        mContext = activity;
    }


    @Override
    protected void populateView(View view, Articles articles) {

        String title = articles.getTitle();
        TextView titleText = (TextView) view.findViewById(R.id.article_title);
        titleText.setText(title);
        String imageUrl = articles.getImageUrl();
        ImageView thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        // If the message was sent by this user, color it differently

        Glide
                .with(mContext)
                .load(imageUrl)
                .centerCrop()
                .crossFade()
                .into(thumbnail);

    }
}
