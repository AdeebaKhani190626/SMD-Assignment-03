package com.ass2.i190626_190438;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterCallContacts extends RecyclerView.Adapter<MyAdapterCallContacts.MyAdapterCallViewHolder> {

    Context context;
    ArrayList<MyContacts> myContactsArrayList;

    public MyAdapterCallContacts(Context context, ArrayList<MyContacts> myContactsArrayList) {
        this.context = context;
        this.myContactsArrayList = myContactsArrayList;
    }

    @NonNull
    @Override
    public MyAdapterCallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.call_contact_row,parent,false);
        return new MyAdapterCallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterCallViewHolder holder, int position) {

        MyContacts myContacts = myContactsArrayList.get(position);
        holder.tvName.setText(myContacts.name);
        holder.tvNumber.setText(myContacts.number);
    }

    @Override
    public int getItemCount() {
        return myContactsArrayList.size();
    }

    public static class MyAdapterCallViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName, tvNumber;
        AppCompatImageButton callButton, messageButton;

        public MyAdapterCallViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.nameContact);
            tvNumber = itemView.findViewById(R.id.contactNumber);
            callButton = itemView.findViewById(R.id.ibCall);
            messageButton = itemView.findViewById(R.id.ibMessage);

            /* callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" +  myContactsArrayList.get(getAdapterPosition()).getNumber()));
                    context.startActivity(intent);
                }
            }); */

            /* messageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:" + myContactsArrayList.get(getAdapterPosition()).getNumber()));
                    context.startActivity(i);
                }
            }); */
        }
    }
}
