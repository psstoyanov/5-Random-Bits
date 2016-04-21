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


        // Firebase:
        // www.firebase.com

        // Quick start with Firebase 2 minute YouTube tutorial:
        // https://www.youtube.com/watch?v=cab-p7pJBDw

        // Replace with your Firebase project URL:
        // https://example-your-firebase-project,firebaseio.com/Articles

        // The JSON structure for this firebase project is linked bellow:
        // The root of the Firebase project:
        // https://drive.google.com/open?id=0B9B86M6T4uiaNkk0YVQ3dGwzajA
        // Download the JSON file and import it to your Firebase project at root level.
        mRef = new Firebase("https://5-random-bits.firebaseio.com/Articles");



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

                    String mTitle =  (String) article.getTitle();
                    System.out.println(mTitle);
                    title.setText(mTitle);

                    // Very simple example.
                    // A text and an ImageView using Glide.
                    // Data from Firebase is fetched and loaded.
                    // The Data is extracted with the help of a class and Jackson parsing.

                    // Excellent explanation of how you can use classes and Firebase to extract data by Frank van Puffelen:
                    // http://stackoverflow.com/questions/32108969/why-do-i-get-failed-to-bounce-to-type-when-i-turn-json-from-firebase-into-java

                    // The names must be identical.
                    // The root of the Firebase project:
                    // https://drive.google.com/open?id=0B9B86M6T4uiaNkk0YVQ3dGwzajA
                    // Download the JSON file and import it to your Firebase project at root level.

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

    }

}


