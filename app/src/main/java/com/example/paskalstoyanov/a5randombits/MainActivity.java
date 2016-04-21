package com.example.paskalstoyanov.a5randombits;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.firebase.ui.FirebaseRecyclerAdapter;

public class MainActivity extends AppCompatActivity {



    private Firebase mRef;
    private RecyclerView mArticles;
    private FirebaseRecyclerAdapter<Articles, ArticlesHolder> mRecycleViewAdapter;
    Context mContext;

    private ArticleListAdapter mArticleListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // other setup code
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        mContext = this;

        //mArticles = (RecyclerView) findViewById(R.id.listview);

        mRef = new Firebase("https://5-random-bits.firebaseio.com/Articles");

//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setReverseLayout(false);
//
//        mArticles.setHasFixedSize(false);
//        mArticles.setLayoutManager(manager);
//
//        mRecycleViewAdapter = new FirebaseRecyclerAdapter<Articles, ArticlesHolder>(
//                Articles.class, R.layout.two_line_list_item, ArticlesHolder.class, mRef) {
//            @Override
//            public void populateViewHolder(ArticlesHolder chatView, Articles chat, int position) {
//                chatView.setTitle(chat.getTitle());
//                chatView.setBody(chat.getBody());
//
//            }
//        };
//
//        mArticles.setAdapter(mRecycleViewAdapter);

        final TextView title = (TextView) findViewById(R.id.text);
        TextView body = (TextView) findViewById(R.id.text2);
        final ImageView pic = (ImageView) findViewById(R.id.imageView);



        Firebase ref = new Firebase("https://5-random-bits.firebaseio.com/Articles");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot articlesSnapshot) {
                for (DataSnapshot articleSnapshot : articlesSnapshot.getChildren()) {
                    System.out.println(articleSnapshot.child("title").getValue());

                    GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(pic);



                    Articles article = articleSnapshot.getValue(Articles.class);
                    Log.d("firebase", article.getImageUrl());

                    String mTitle =  (String) article.getTitle();
                    System.out.println(mTitle);
                    title.setText(mTitle);

                    Glide
                            .with(mContext)
                            .load(article.getImageUrl())
                            .crossFade()
                            .into(imageViewTarget);
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
        //final ListView listView = getListView();

    }

}


