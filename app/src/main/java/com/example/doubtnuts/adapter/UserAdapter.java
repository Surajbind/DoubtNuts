package com.example.doubtnuts.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doubtnuts.ChatActivity;
import com.example.doubtnuts.ChattingActivity;
import com.example.doubtnuts.R;
import com.example.doubtnuts.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Viewholder> {
    Context homeActivity;
    ArrayList<User> usersArrayList;

    public UserAdapter(ChatActivity homeActivity, ArrayList<User> usersArrayList) {
        this.homeActivity = homeActivity;
        this.usersArrayList = usersArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(homeActivity).inflate(R.layout.item_user_row, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        User users = usersArrayList.get(position);

        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(users.getId()))
        {
            holder.itemView.setVisibility(View.GONE);
        }

        holder.user_name.setText(users.getUsername());
        Picasso.get().load(users.getProfileimageurl()).placeholder(R.drawable.proflie).into(holder.user_profile);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homeActivity, ChattingActivity.class);
                intent.putExtra("name", users.getUsername());
                intent.putExtra("ReciverImage", users.getProfileimageurl());
                intent.putExtra("uid", users.getId());
                homeActivity.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    static class Viewholder extends RecyclerView.ViewHolder {
        CircleImageView user_profile;
        TextView user_name;
        TextView user_status;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            user_profile = itemView.findViewById(R.id.user_image);
            user_name = itemView.findViewById(R.id.user_name);
            user_status = itemView.findViewById(R.id.user_status);

        }
    }
}
