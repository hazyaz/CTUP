package com.hazyaz.ctup.chat_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hazyaz.ctup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Request_fragment extends Fragment {


    public Request_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request_fragment, container, false);
    }

}
