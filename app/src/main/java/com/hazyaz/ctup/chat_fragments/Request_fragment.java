package com.hazyaz.ctup.chat_fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hazyaz.ctup.R;
import com.hazyaz.ctup.utils.Friends;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;


public class Request_fragment extends Fragment {

    public View mRequestView;
    String mStatus;
    private FirebaseAuth mAuth;
    private DatabaseReference mRequestDatabase;
    private String mCurrentUser;
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


        mRequestDatabase = FirebaseDatabase.getInstance().getReference().child("friendsReq").child(mCurrentUser);


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


                        mRequestDatabase.child(list_user_id).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                if (dataSnapshot.child("request_type").exists()) {
                                    mStatus = dataSnapshot.child("request_type").getValue().toString();
                                }

                                mUserReqDatabase.child(list_user_id).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(final DataSnapshot dataSnapshot) {

                                        if (mStatus.equals("received")) {

                                            final String userName = dataSnapshot.child("name").getValue().toString();
                                            String userThumb = dataSnapshot.child("thumb_image").getValue().toString();


                                            Log.d("eee", "erroeherein friend req ");
                                            friendsViewHolder.userNameView.setText(userName);
                                            Picasso.get().load(userThumb).into(friendsViewHolder.userImageView);


                                            friendsViewHolder.mRejectRequest.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    mRequestDatabase.child(list_user_id).removeValue(new DatabaseReference.CompletionListener() {
                                                        @Override
                                                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                                            Toast.makeText(getContext(), "Request Removed", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            });


                                            friendsViewHolder.mAcceptRequest.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    final String currentDate = DateFormat.getDateTimeInstance().format(new Date());
                                                    mRequestDatabase.child(list_user_id).removeValue(new DatabaseReference.CompletionListener() {
                                                        @Override
                                                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                                                        }
                                                    });
                                                    mFriendsDatabase.child(list_user_id).child("date").setValue(currentDate).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(getContext(), "friedns added", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });


                                                }
                                            });


                                        } else if (mStatus.equals("sent")) {

                                            final String userName = dataSnapshot.child("name").getValue().toString();
                                            String userThumb = dataSnapshot.child("thumb_image").getValue().toString();


                                            Log.d("eee", "erroeherein friend req ");
                                            friendsViewHolder.userNameView.setText(userName);
                                            Picasso.get().load(userThumb).into(friendsViewHolder.userImageView);

                                            friendsViewHolder.mAcceptRequest.setVisibility(View.VISIBLE);
                                            friendsViewHolder.mAcceptRequest.setText("Cancel friend Request");
                                            friendsViewHolder.mAcceptRequest.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    mRequestDatabase.child(list_user_id).removeValue();
//                                                    new DatabaseReference.CompletionListener() {
//                                                        @Override
//                                                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
//                                                            Toast.makeText(getContext(), "Request Removed", Toast.LENGTH_SHORT).show();
//                                                        }
//                                                    });

                                                }
                                            });


                                            friendsViewHolder.mRejectRequest.setVisibility(View.GONE);
                                            friendsViewHolder.userStatusView.setText("You have send req to this");
                                            Toast.makeText(getContext(), "Nofriedn bacche", Toast.LENGTH_LONG).show();
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                };
        mRecyclerView.setAdapter(friendsRecyclerViewAdapter);


    }


    public static class FriendsViewHolder extends RecyclerView.ViewHolder {

        View mView;
        Button mAcceptRequest;
        Button mRejectRequest;
        TextView userNameView;
        ImageView userImageView;
        TextView userStatusView;

        public FriendsViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            mAcceptRequest = itemView.findViewById(R.id.AcceptRequest);
            mRejectRequest = itemView.findViewById(R.id.RejectRequest);
            userNameView = mView.findViewById(R.id.requestUserName);
            userImageView = mView.findViewById(R.id.userImageRequst);
            userStatusView = mView.findViewById(R.id.simpleRequestText);
        }



    }
}



