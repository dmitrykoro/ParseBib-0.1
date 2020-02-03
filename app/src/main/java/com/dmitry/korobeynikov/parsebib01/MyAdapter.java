package com.dmitry.korobeynikov.parsebib01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import name.ank.lab4.BibEntry;
import name.ank.lab4.Keys;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<BibEntry> bibs;
    private LayoutInflater inflater;


    MyAdapter(Context context, List<BibEntry> bibs) {
        this.bibs = bibs;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(bibs.get(position).getType());
        holder.typeView.setText(stringBuilder);

        holder.authorView.setText(bibs.get(position).getField(Keys.AUTHOR));
        holder.booktitleView.setText(bibs.get(position).getField(Keys.TITLE));
        holder.yearView.setText(bibs.get(position).getField(Keys.YEAR));
    }


    @Override
    public int getItemCount() {
        return bibs.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView typeView;
        private TextView authorView;
        private TextView booktitleView;
        private TextView yearView;

        private ViewHolder(View view) {
            super(view);
            typeView = view.findViewById(R.id.type);
            authorView = view.findViewById(R.id.author);
            booktitleView = view.findViewById(R.id.booktitle);
            yearView = view.findViewById(R.id.year);
        }
    }
}
