package com.ass2.i190626_190438;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessagesTabLayout extends Fragment {

    View view;
    ArrayList<ChatContact> chatContactArrayList = new ArrayList<>();;
    RecyclerView rv;
    MyAdapterChatContact myAdapterChatContact;

    public MessagesTabLayout() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.message_tablayout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.recyclerviewchat);
        dataInitialize();
        myAdapterChatContact = new MyAdapterChatContact(getContext(),chatContactArrayList);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);
        rv.setVerticalScrollBarEnabled(true);
        rv.setAdapter(myAdapterChatContact);
        myAdapterChatContact.notifyDataSetChanged();
    }

    private void dataInitialize() {

        ChatContact chatContact = new ChatContact("Adeeba Khan", "Testing recycler view", R.drawable.flower, "07:46");
        chatContactArrayList.add(chatContact);
        ChatContact chatContact02 = new ChatContact("Fatima Asim", "Hope it works", R.drawable.butterfly, "07:46");
        chatContactArrayList.add(chatContact02);
        chatContactArrayList.add(new ChatContact("Adeeba Khan", "Testing recycler view", R.drawable.flower, "07:46"));
        chatContactArrayList.add(new ChatContact("Fatima Asim", "Hope it works", R.drawable.butterfly, "07:46"));
        chatContactArrayList.add(new ChatContact("Adeeba Khan", "Testing recycler view", R.drawable.flower, "07:46"));
        chatContactArrayList.add(new ChatContact("Fatima Asim", "Hope it works", R.drawable.butterfly, "07:46"));
        chatContactArrayList.add(new ChatContact("Adeeba Khan", "Testing recycler view", R.drawable.flower, "07:46"));
        chatContactArrayList.add(new ChatContact("Fatima Asim", "Hope it works", R.drawable.butterfly, "07:46"));

    }
}
