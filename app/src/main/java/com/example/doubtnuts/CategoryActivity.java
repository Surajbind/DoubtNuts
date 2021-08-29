package com.example.doubtnuts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.doubtnuts.adapter.PostAdapter;
import com.example.doubtnuts.models.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private PostAdapter postAdapter;
    private List<Post> postList;

    String title;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerViewCategory);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        postList = new ArrayList<>();
        postAdapter = new PostAdapter(CategoryActivity.this, postList);
        recyclerView.setAdapter(postAdapter);

        if(getIntent().getExtras() !=null){
            title = getIntent().getStringExtra("title");
            getSupportActionBar().setTitle(title);
            readPost();

        }



    }

    private void readPost() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("questions_posts");
        Query query = reference.orderByChild("topic").equalTo(title);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    postList.clear();
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        Post post = dataSnapshot.getValue(Post.class);
                        postList.add(post);
                    }
                    postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CategoryActivity.this, "Wassup Nahi Hoga Yeh", Toast.LENGTH_SHORT).show();
            }
        });

    }
}