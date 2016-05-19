package com.example.paskalstoyanov.a5randombits;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {



    private DatabaseReference mRef;
    private RecyclerView mArticles;
    private FirebaseRecyclerAdapter<Articles, ArticlesHolder> mRecycleViewAdapter;
    Context mContext;

    private ArticleListAdapter mArticleListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // other setup code
        setContentView(R.layout.activity_main);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
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
        mRef = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://5-random-bits.firebaseio.com/Articles");
        mRef.keepSynced(true);

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



        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://5-random-bits.firebaseio.com/Articles");

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
                            //.crossFade()
                            .into(imageViewTarget);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

}


