package com.jovanovic.stefan.sqlitetutorial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public abstract class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList name, stream, age, address;

    CustomAdapter(Activity activity, Context context, ArrayList name, ArrayList stream, ArrayList age,
                  ArrayList address){
        this.activity = activity;
        this.context = context;
        this.name = name;
        this.age = age;
        this.stream = stream;
        this.address = address;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.name.setText(String.valueOf(name.get(position)));
        holder.stream.setText(String.valueOf(stream.get(position)));
        holder.age.setText(String.valueOf(age.get(position)));
        holder.address.setText(String.valueOf(address.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(name.get(position)));
                intent.putExtra("title", String.valueOf(age.get(position)));
                intent.putExtra("author", String.valueOf(stream.get(position)));
                intent.putExtra("pages", String.valueOf(address.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public BreakIterator stream;
        public BreakIterator age;
        public BreakIterator address;
        TextView name, book_title_txt, book_author_txt, book_pages_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameV);
            book_title_txt = itemView.findViewById(R.id.streamV);
            book_author_txt = itemView.findViewById(R.id.addressV);
            book_pages_txt = itemView.findViewById(R.id.ageV);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
