package com.example.instagramclone;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName, edtBio, edtProfession, edtHobbies, edtFavSport;
    private Button btnUpdateInfo;
    private ParseUser parseUser;
    private ConstraintLayout mainLayout;


    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtBio = view.findViewById(R.id.edtBio);
        edtProfession = view.findViewById(R.id.edtProfession);
        edtHobbies = view.findViewById(R.id.edtHobbies);
        edtFavSport = view.findViewById(R.id.edtFavSport);
        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);
        mainLayout = view.findViewById(R.id.mainLayout);


/////////////////////////////////// On Click to hide keyboard on anywhere click //////////////////////////////////////////////////////////////////////////////////////////

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),0);
                }catch (Exception e){

                    e.printStackTrace();
                }
            }
        });




/////////////////////////////////// To get profile data from server into Edit Text//////////////////////////////////////////////////////////////////////////////////////
        parseUser = ParseUser.getCurrentUser();

        setProfileInfo(edtProfileName,"Profile_name");
        setProfileInfo(edtBio,"Bio");
        setProfileInfo(edtProfession,"Profession");
        setProfileInfo(edtHobbies,"Hobbies");
        setProfileInfo(edtFavSport,"Fav_Sport");

////////////////////////// On CLick Listener To update data on server //////////////////////////////////////////////////////////////////////////////////////////////////

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("Profile_name",edtProfileName.getText().toString());
                parseUser.put("Bio",edtBio.getText().toString());
                parseUser.put("Profession",edtProfession.getText().toString());
                parseUser.put("Hobbies",edtProfession.getText().toString());
                parseUser.put("Fav_Sport",edtFavSport.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Updating Info...");
                progressDialog.show();

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e == null){

                            FancyToast.makeText(getContext(),"Updated Info.",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                        }else{

                            FancyToast.makeText(getContext(),e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                        }

                        progressDialog.dismiss();
                    }
                });


            }
        });

       return view;
    }


////////////////////// Method to set profile info from server ////////////////////////////////////////////////////////////////////

    public void setProfileInfo(EditText view, String info){

        if(parseUser.get(info) == null){

            view.setText("");
        }else {
            view.setText(parseUser.get(info).toString());
        }
    }


}
