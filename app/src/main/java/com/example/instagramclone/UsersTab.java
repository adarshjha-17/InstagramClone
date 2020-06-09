package com.example.instagramclone;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersTab extends Fragment {

    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter arrayAdapter;
    private TextView loadingTextView;


    public UsersTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_users_tab, container, false);
        listView = view.findViewById(R.id.listView);
        loadingTextView = view.findViewById(R.id.loadingTextView);
        arrayList = new ArrayList();
        arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);


        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());

        loadingTextView.setVisibility(View.INVISIBLE);
        parseQuery.findInBackground(new FindCallback<ParseUser>() {

            @Override
            public void done(List<ParseUser> objects, ParseException e) {

                if(e == null){

                    if(objects.size() > 0){

                        for(ParseUser user : objects){

                            arrayList.add(user.getUsername());
                        }

                        listView.setVisibility(View.VISIBLE);
                        listView.setAdapter(arrayAdapter);

                    }
                }
            }
        });

        return view;
    }

}
