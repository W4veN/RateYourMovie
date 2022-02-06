package com.example.hw_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable  {

    private Context mContext;
    private List<movie> mData;
    private  List<movie> mDataAll;


    public RecyclerViewAdapter(Context mContext, List<movie> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataAll = new ArrayList<>(mData);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tv_movie_title.setText(mData.get(position).getTitle());
        Picasso.get().load(mData.get(position).getThumbnail()).into(holder.img_movie_thumbnail);

        //set Click listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Intent intent = new Intent(mContext, MovieActivity.class);
                    //passing data to the  movie activity
                    intent.putExtra("MovieTitle", mData.get(position).getTitle());
                    intent.putExtra("Description", mData.get(position).getDescription());
                    intent.putExtra("Thumbnail", mData.get(position).getThumbnail());
                    intent.putExtra("Category",mData.get(position).getCategory());
                    intent.putExtra("Rate", mData.get(position).getRate());
                    intent.putExtra("Key", mData.get(position).getKey());
                    //start the activity
                    mContext.startActivity(intent);
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(mContext).setTitle("Delete").setMessage("Are you sure to delete this movie?").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String movieID=mData.get(position).getKey();
                        FirebaseDatabase.getInstance().getReference("movies").child(movieID).removeValue();
                        //return true;
                    }
                })
                        .setNegativeButton(android.R.string.no,null).setIcon(android.R.drawable.ic_dialog_alert).show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<movie> filteredList = new ArrayList<>();

            if (charSequence.toString().isEmpty()){
                filteredList.addAll(mDataAll);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(movie item : mDataAll) {
                    if(item.getTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        mData.clear();
        mData.addAll((List) filterResults.values);
        notifyDataSetChanged();
        }
    };


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_movie_title;
        ImageView img_movie_thumbnail;
        CardView cardView;
        public MyViewHolder(View itemView){
            super(itemView);
            tv_movie_title = (TextView) itemView.findViewById(R.id.movie_title_id);
            img_movie_thumbnail=(ImageView) itemView.findViewById(R.id.movie_image_id);
            cardView= (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}
