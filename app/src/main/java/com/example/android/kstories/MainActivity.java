package com.example.android.kstories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.kstories.user.UserWelcomeActivity;


public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // Find the View that shows the Lauryn Hill songs category
        Button lauryn = (Button) findViewById(R.id.useraccount_button);

        // Set a click listener on that View
        lauryn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent laurynIntent = new Intent(MainActivity.this, UserWelcomeActivity.class);
                startActivity(laurynIntent);


            }
        });
    }

//    public void openLaurynList(View view) {
//
//        // Start the activity connect to the
//        // specified class
//
//        Intent i = new Intent(this, UserWelcomeActivity.class);
//        startActivity(i);
//    }
}