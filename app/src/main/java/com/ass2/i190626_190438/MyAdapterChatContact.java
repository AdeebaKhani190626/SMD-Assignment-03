package com.ass2.i190626_190438;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterChatContact extends RecyclerView.Adapter<MyAdapterChatContact.MyViewHolder>{

    Context context;
    ArrayList<ChatContact> chatContactArrayList;

    public MyAdapterChatContact(Context context, ArrayList<ChatContact> chatContactArrayList) {
        this.context = context;
        this.chatContactArrayList = chatContactArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.chat_contact_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ChatContact chatContact = chatContactArrayList.get(position);
        holder.name.setText(chatContact.name);
        holder.msg.setText(chatContact.msg);
        holder.time.setText(chatContact.time);
        holder.image.setImageResource(chatContact.dp);

        // apply open chat here
        holder.row_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IndividualChat.class);
                intent.putExtra("name",chatContactArrayList.get(position).getName());
                intent.putExtra("image",chatContactArrayList.get(position).getDp());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatContactArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, msg, time;
        ImageView not_seen;
        RelativeLayout row_chat;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.dpSet);
            name = itemView.findViewById(R.id.personName);
            msg = itemView.findViewById(R.id.messageDisp);
            time = itemView.findViewById(R.id.timeDisp);
            not_seen = itemView.findViewById(R.id.notChecked);
            row_chat = itemView.findViewById(R.id.row_chatrv);
        }
    }
}
