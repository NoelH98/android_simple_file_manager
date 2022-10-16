package com.noel.bar_hub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private Context context;
    private File[] filesAndFolders;

    public Adapter(Context context, File[] filesAndFolders){
        this.context = context;
        this.filesAndFolders = filesAndFolders;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {

       View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int i) {

        final File selectedFile = filesAndFolders[i];
        holder.textView.setText(selectedFile.getName());

        if(selectedFile.isDirectory()){
            holder.imageView.setImageResource(R.drawable.folder);
        }else {
            holder.imageView.setImageResource(R.drawable.file);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedFile.isDirectory()){
                    Intent i = new Intent(context, FileActivity.class);
                    String path = selectedFile.getAbsolutePath();
                    i.putExtra("path",path);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else {

                    try {
                        Intent in = new Intent();
                        in.setAction(Intent.ACTION_VIEW);
                        String type = "image/*";
                        in.setDataAndType(Uri.parse(selectedFile.getAbsolutePath()), type);
                        in.setFlags(in.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(in);
                    } catch(Exception e){
                        Toast.makeText(context.getApplicationContext(), "Cannot open this file", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu pop = new PopupMenu(context, v);
                pop.getMenu().add("DELETE");
                pop.getMenu().add("MOVE");
                pop.getMenu().add("RENAME");
                pop.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return filesAndFolders.length;
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.textV);
            imageView = itemView.findViewById(R.id.icon_view);
        }
    }
}
