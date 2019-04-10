package com.hazyaz.ctup.chat_fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hazyaz.ctup.R;
import com.hazyaz.ctup.utils.Friends;
import com.squareup.picasso.Picasso;


public class Request_fragment extends Fragment {

    public View mRequestView;
    private FirebaseAuth mAuth;
    private DatabaseReference mRequestDatabase;
    private String mCurrentUser;
    private Button mAcceptRequest;
    private Button mRejectRequest;
    private DatabaseReference mFriendsDatabase;
    private DatabaseReference mUserReqDatabase;
    private RecyclerView mRecyclerView;

    public Request_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRequestView = inflater.inflate(R.layout.fragment_request_fragment, container, false);

        mRecyclerView = mRequestView.findViewById(R.id.requestRecyclerView);

        mAuth = FirebaseAuth.getInstance();

        mCurrentUser = mAuth.getCurrentUser().getUid();

        mAcceptRequest = mRequestView.findViewById(R.id.AcceptRequest);
        mRejectRequest = mRequestView.findViewById(R.id.RejectRequest);


        mRequestDatabase = FirebaseDatabase.getInstance().getReference().child("friendsReq").child(mCurrentUser);
        mRequestDatabase.keepSynced(true);

        mFriendsDatabase = FirebaseDatabase.getInstance().getReference().child("Friends").child(mCurrentUser);
        mFriendsDatabase.keepSynced(true);


        mUserReqDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        mUserReqDatabase.keepSynced(true);


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return mRequestView;

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Friends, Request_fragment.FriendsViewHolder> friendsRecyclerViewAdapter =
                new FirebaseRecyclerAdapter<Friends, Request_fragment.FriendsViewHolder>(

                        Friends.class,
                        R.layout.single_friend_request,
                        Request_fragment.FriendsViewHolder.class,
                        mRequestDatabase


                ) {
                    @Override
                    protected void populateViewHolder(final Request_fragment.FriendsViewHolder friendsViewHolder, final Friends friends, int i) {


                        final String list_user_id = getRef(i).getKey();

                        mUserReqDatabase.child(list_user_id).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                final String userName = dataSnapshot.child("name").getValue().toString();
                                String userThumb = dataSnapshot.child("thumb_image").getValue().toString();

                                friendsViewHolder.setName(userName);
                                friendsViewHolder.setUserImage(userThumb, getContext());


                                mAcceptRequest.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                    }
                                });


                                mRejectRequest.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                    }
                                });


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                };

        mRecyclerView.setAdapter(friendsRecyclerViewAdapter);

    }

    public static class FriendsViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public FriendsViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }


        public void setName(String name) {

            TextView userNameView = mView.findViewById(R.id.requestUserName);
            userNameView.setText(name);

        }

        public void setUserImage(String thumb_image, Context ctx) {

            ImageView userImageView = mView.findViewById(R.id.userImageRequst);
            Picasso.get().load(thumb_image).into(userImageView);

        }

    }
}





