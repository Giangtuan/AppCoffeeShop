package com.example.foodorder.viewmodel;

import android.content.Context;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.R;
import com.example.foodorder.adapter.ContactAdapter;
import com.example.foodorder.model.Contact;

public class ContactViewModel {

    private Context mContext;
    public ObservableList<Contact> listContacts = new ObservableArrayList<>();

    public ContactViewModel(Context mContext) {
        this.mContext = mContext;
        getListContacts();
    }

    public void getListContacts() {
        listContacts.add(new Contact(Contact.FACEBOOK, R.drawable.ic_facebook, mContext.getString(R.string.label_facebook)));
        listContacts.add(new Contact(Contact.HOTLINE, R.drawable.ic_hotline, mContext.getString(R.string.label_call)));
        listContacts.add(new Contact(Contact.GMAIL, R.drawable.ic_gmail, mContext.getString(R.string.label_gmail)));
        listContacts.add(new Contact(Contact.ZALO, R.drawable.ic_zalo, mContext.getString(R.string.label_zalo)));
    }

    @BindingAdapter({"list_data"})
    public static void loadListContacts(RecyclerView recyclerView, ObservableList<Contact> list) {
        GridLayoutManager layoutManager = new GridLayoutManager(recyclerView.getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        ContactAdapter contactAdapter = new ContactAdapter(list);
        recyclerView.setAdapter(contactAdapter);
    }

    public void release() {
        mContext = null;
    }
}
