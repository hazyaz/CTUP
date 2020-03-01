package com.hazyaz.ctup.adapters;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.hazyaz.ctup.R;
import com.hazyaz.ctup.utils.Message;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private String timing;
    private String secUser;
    private String currentUser;
    private List<Message> mMessageList;
    private DatabaseReference mUserDatabase;
    private FirebaseAuth mAuth;
    private String mCurrentUserId;


    public MessageAdapter(List<Message> mMessageList, String secondUser, String mCurrentUser) {

        this.secUser = secondUser;
        this.mMessageList = mMessageList;
        this.mCurrentUserId = mCurrentUser;

    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_single_layout, parent, false);


        return new MessageViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final MessageViewHolder viewHolder, int i) {

        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");


        mAuth = FirebaseAuth.getInstance();


        Message c = mMessageList.get(i);
        String from_user = c.getFrom();
        String type = c.getType();

        String dateString = formatter.format(new Date(c.getTime()));
        Log.d("1244544", from_user);
        if (c.getFrom().equals(secUser) && c.getMessage() != null) {

            Log.d("uuuu", c.getMessage());

            if (c.getType().equals("text")) {
                viewHolder.messageText.setText(c.getMessage());
            viewHolder.timeText.setText(dateString);

//            } else if(c.getType().equals("image")) {

                Picasso.get().load(c.getMessage()).into(viewHolder.imageView1);

//                viewHolder.messageText.setVisibility(View.INVISIBLE);
//                viewHolder.timeText.setVisibility(View.INVISIBLE);

            }

            viewHolder.messageText1.setText("");
            viewHolder.timeText1.setText("");

        } else if (c.getFrom().equals(mCurrentUserId) && c.getMessage() != null) {

            Log.d("uuuuuuuuuu", c.getFrom());
            Log.d("uuu", c.getMessage());

            if (c.getType().equals("text")) {

                viewHolder.messageText1.setText(c.getMessage());
                viewHolder.timeText1.setText(dateString);

//            } else if(c.getType().equals("image")){

                Picasso.get().load(c.getMessage()).into(viewHolder.imageView2);

//                viewHolder.messageText1.setVisibility(View.INVISIBLE);
//                viewHolder.timeText1.setVisibility(View.INVISIBLE);

            }
            viewHolder.messageText.setText("");
            viewHolder.timeText.setText("");
        }


    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView messageText;
        public TextView timeText;
        public TextView messageText1;
        public TextView timeText1;
        public ImageView imageView1;
        public ImageView imageView2;


        public MessageViewHolder(View view) {
            super(view);


            messageText1 = view.findViewById(R.id.message_receiver);
            timeText1 = view.findViewById(R.id.time_receiver);

            messageText = view.findViewById(R.id.message_sender);
            timeText = view.findViewById(R.id.time_sender);

            imageView1 = view.findViewById(R.id.message_image_layout_1);
            imageView2 = view.findViewById(R.id.message_image_layout_2);


        }
    }


}
