package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.w3c.dom.Text;

import java.util.List;


public class SignUp extends AppCompatActivity {

    EditText nameEditText, kickSpeedEditText, kickPowerEditText, punchSpeedEditText, punchPowerEditText;
    public String name;
    private TextView getTextView;
    private Button getButton;
    String allKickBoxerObjects;
    int kickSpeed, kickPower, punchSpeed, punchPower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        nameEditText = findViewById(R.id.nameEditText);
        kickSpeedEditText = findViewById(R.id.kickSpeedEditText);
        kickPowerEditText = findViewById(R.id.kickPowerEditText);
        punchSpeedEditText = findViewById(R.id.punchSpeedEditText);
        punchPowerEditText = findViewById(R.id.punchPowerEditText);
        getTextView = findViewById(R.id.getTextView);
        getButton = findViewById(R.id.getButton);

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allKickBoxerObjects = "";

                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("kick_boxer");
                parseQuery.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e == null){

                            if(objects.size() > 0){

                                for (ParseObject object: objects){

                                    allKickBoxerObjects += object.get("name").toString() + "\t\t\t\t" +object.get("kick_power").toString() + "\n" ;

                                }

                                getTextView.setText(allKickBoxerObjects);
                                FancyToast.makeText(SignUp.this,"Get data successfully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();


                            }
                        }
                        else{

                            FancyToast.makeText(SignUp.this,"Can't get data from server",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                        }
                    }
                });
            }
        });


   }

    public void hello(View v) {

        try {

            name = nameEditText.getText().toString();
            kickSpeed = Integer.parseInt(kickSpeedEditText.getText().toString());
            kickPower = Integer.parseInt(kickPowerEditText.getText().toString());
            punchSpeed = Integer.parseInt(punchSpeedEditText.getText().toString());
            punchPower = Integer.parseInt(punchPowerEditText.getText().toString());


            final ParseObject kickBoxer = new ParseObject("kick_boxer");
            kickBoxer.put("name", name);
            kickBoxer.put("kick_speed", kickSpeed);
            kickBoxer.put("kick_power", kickPower);
            kickBoxer.put("punch_speed", punchSpeed);
            kickBoxer.put("punch_power", punchPower);
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + ", data saved successfully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                    }
                    else{
                        FancyToast.makeText(SignUp.this,e.getMessage() + " \n Data not saved try again",FancyToast.LENGTH_SHORT,FancyToast.ERROR, false).show();
                    }
                }
            });
        } catch (Exception e) {

            FancyToast.makeText(SignUp.this,e.getMessage() + "\n Please enter correct details",FancyToast.LENGTH_SHORT,FancyToast.WARNING,false).show();
        }
    }
}
