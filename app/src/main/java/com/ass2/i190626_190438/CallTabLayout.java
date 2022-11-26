package com.ass2.i190626_190438;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.RecognitionListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CallTabLayout extends Fragment {

    View view;
    RecyclerView rv;
    ArrayList<MyContacts> myContactsArrayList = new ArrayList<>();
    MyAdapterCallContacts myAdapterCallContacts;

    public CallTabLayout() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.call_tablayout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.recyclerviewcontacts);

        myContactsArrayList.add(new MyContacts("Adeeba Khan","03328333644"));
        //loadContacts();
        myAdapterCallContacts = new MyAdapterCallContacts(getContext(),myContactsArrayList);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);
        rv.setAdapter(myAdapterCallContacts);

        myAdapterCallContacts.notifyDataSetChanged();

        //rv.setLayoutManager(new LinearLayoutManager(getContext()));
        //rv.setHasFixedSize(true);
    }

    /* private void loadContacts() {
        Cursor cursor = getContext().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,ContactsContract
                .CommonDataKinds.Phone.NUMBER);
        //ArrayList<MyContacts> myContactsArrayList = new ArrayList<>();
        myContactsArrayList = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext())
            {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                if (number.length()> 0) {
                    Cursor phoneCursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null
                    ,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",new String[]{id},null);

                    if (phoneCursor.getCount() > 0) {
                        while (phoneCursor.moveToNext()) {
                            @SuppressLint("Range") String phoneNumberValue= phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            MyContacts myContacts = new MyContacts(name, phoneNumberValue);
                            myContactsArrayList.add(myContacts);
                        }
                    }
                    phoneCursor.close();
                }
            }

            // initialize the adapter and set it to recycler view
            MyAdapterCallContacts myAdapterCallContacts = new MyAdapterCallContacts(getContext(),myContactsArrayList);
            rv.setAdapter(myAdapterCallContacts);
            myAdapterCallContacts.notifyDataSetChanged();
        }
        else
        {
            Toast.makeText(getContext(), "No Contacts Found!", Toast.LENGTH_SHORT).show();
        }
    } */
}
