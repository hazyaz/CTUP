package com.hazyaz.ctup;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hazyaz.ctup.chat_fragments.Chat_fragment;
import com.hazyaz.ctup.chat_fragments.Friends_fragment;
import com.hazyaz.ctup.chat_fragments.Request_fragment;

public class CustomPagerAdapter extends FragmentPagerAdapter {


    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public Fragment getItem(int position) {
        if(position == 1 ) {
            Request_fragment request_fragment = new Request_fragment();
            return request_fragment;
        }

           else if(position == 2) {
                Chat_fragment chat_fragment = new Chat_fragment();
                return chat_fragment;
            }

            Friends_fragment friends_fragment = new Friends_fragment();
            return friends_fragment;

    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position)
        {
            case 0:
                return "REQUEST";

            case 1:
                return "CHAT";


            case 2:
                return "FRIENDS";

                default:
                    return null;

        }

    }
}
