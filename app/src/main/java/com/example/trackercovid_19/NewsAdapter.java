package com.example.trackercovid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsAdapterViewholder> {
    private ArrayList<String> post_title = new ArrayList<String>();
    private ArrayList<String> post_image = new ArrayList<String>();
    private ArrayList<String> post_author = new ArrayList<String>();
    private ArrayList<String> post_description = new ArrayList<String>();
    private Context mcontext;
    public NewsAdapter(Context context,ArrayList<String> list1,ArrayList<String> list5,ArrayList<String> list3,ArrayList<String> list4) {
        post_title = list1;
        post_image= list5;
        post_description = list3;
        post_author = list4;

        mcontext =context;
    }
    @NonNull
    @Override
    public NewsAdapterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newscard,parent , false);
        return new NewsAdapterViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapterViewholder holder, int position) {

        holder.title.setText(post_title.get(position));
        holder.description.setText(post_description.get(position));
        holder.author.setText(post_author.get(position));
        //loading image from internet
        Glide.with(mcontext).load(post_image.get(position)).into(holder.postImage);
    }

    @Override
    public int getItemCount() {
        return post_title.size();
    }

    public class NewsAdapterViewholder extends RecyclerView.ViewHolder {
        TextView title,description, author;
        ImageView postImage;
        public NewsAdapterViewholder(View view) {
            super(view);
            title=(TextView) itemView.findViewById(R.id.title);
            postImage= itemView.findViewById(R.id.postImage);
            description = (TextView) itemView.findViewById(R.id.description);
            author = (TextView) itemView.findViewById(R.id.author);
        }
    }
}
