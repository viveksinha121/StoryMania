package com.example.storymania;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Story> mDataset;
    private ArrayList<Author> authors;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView story_title,story_desc,story_author;
        public ImageView si;
        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            story_title = (TextView) v.findViewById(R.id.story_title);
            story_author = (TextView) v.findViewById(R.id.story_author);
            story_desc = (TextView) v.findViewById(R.id.story_desc);
            si = (ImageView) v.findViewById(R.id.si);
        }
    }

    public MyAdapter(Context context, ArrayList<Story> myDataset, ArrayList<Author> authors) {
        this.context=context;
        mDataset = myDataset;
        this.authors=authors;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.storycard, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.story_title.setText(mDataset.get(position).getTitle());
        holder.story_desc.setText(mDataset.get(position).getDescription());
        for(int i=0; i < authors.size(); i++){
            if(authors.get(i).getId().equalsIgnoreCase(mDataset.get(position).getDb())){
                holder.story_author.setText("By: " + authors.get(i).getUsername());
                break;
            }
        }

        Picasso.with(context).load(mDataset.get(position).getSi()).into(holder.si);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
