package com.hazyaz.ctup.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.hazyaz.ctup.R;
import com.hazyaz.ctup.utils.Message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    private String timing;
    private String secUser;
    private String currentUser;
    private List<Message> mMessageList;
    private DatabaseReference mUserDatabase;
    private FirebaseAuth mAuth;
    private String mCurrentUserId;


    public MessageAdapter(List<Message> mMessageList, String secondUser) {

        this.secUser = secondUser;
        this.mMessageList = mMessageList;

    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_single_layout ,parent, false);


        return new MessageViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final MessageViewHolder viewHolder, int i) {

        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");


        mAuth = FirebaseAuth.getInstance();
        String currentUserId = mAuth.getCurrentUser().getUid();

        Message c = mMessageList.get(i);
        String from_user = c.getFrom();
        String dateString = formatter.format(new Date(c.getTime()));


        if (c.getFrom().equals(secUser)) {

            viewHolder.messageText2.setText(c.getMessage());
            viewHolder.timeText.setText(dateString);

        } else {

            viewHolder.messageText.setText(c.getMessage());
            viewHolder.timeText.setText(dateString);
        }


    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView messageText;
        public TextView timeText;
        public TextView messageText2;


        public MessageViewHolder(View view) {
            super(view);


            messageText = view.findViewById(R.id.message_sender);
            timeText = view.findViewById(R.id.time_sender);


        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }






}