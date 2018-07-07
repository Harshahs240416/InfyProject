package com.infy.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.infosysproficiencyexcercise.R;
import com.infy.model.Row;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.VH> {

    private List<Row> rowList;

    public ListAdapter(String title, List<Row> rows){
        this.rowList = rows;
    }


    @NonNull
    @Override
    public ListAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        VH vh = new VH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.VH holder, int position) {
        Row row = rowList.get(position);
        if(row.getTitle() != null){
            holder.title.setText(row.getTitle());
        }else{
            holder.title.setText("No Title");
        }
        if(row.getDescription() != null){
            holder.subject.setText(row.getDescription());
        }else{
            holder.subject.setText("No Description");
        }
        if(row.getImageHref() != null){
            holder.imageView.setImageDrawable((Drawable)row.getImageHref());
        }else{
//            holder.imageView.setImageDrawable(null);
        }
    }

    @Override
    public int getItemCount() {
        return rowList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView subject;
        public ImageView imageView;

        public VH(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.title);
            subject = (TextView)view.findViewById(R.id.subject);
            imageView = (ImageView)view.findViewById(R.id.display_pic);
        }

    }
}
