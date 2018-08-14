package com.project.softdev.softdevproject;

import android.content.ClipData;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class ContactUsActivity extends AppCompatActivity {

    RatingBar rating_bar;
    EditText feedback_username, feedback_email, feedback_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.feedback_toolbar);
        setSupportActionBar(toolbar);

        feedback_username = (EditText) findViewById(R.id.feedback_username);
        feedback_message = (EditText) findViewById(R.id.feedback_message);
        feedback_email = (EditText) findViewById(R.id.feedback_email);
    }



    private void showCustomDialog() {
         String name = feedback_username.getText().toString().trim();
         String email = feedback_email.getText().toString().trim();
         String message = feedback_message.getText().toString().trim();

        if (name.isEmpty()) {
            feedback_username.setError("Please enter your name");
            feedback_username.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            feedback_email.setError("Please enter your email address");
            feedback_email.requestFocus();
            return;
        }
        if (message.isEmpty()) {
            feedback_message.setError("Please enter your message to us");
            feedback_message.requestFocus();
            return;
        } else {

            new AlertDialog.Builder(this)
                    .setTitle("Confirm Feedback")
                    .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Session.getInstance().checkout();
                            Toast.makeText(getApplicationContext(), "You have submitted your feedback, thank you!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        menu.findItem(R.id.action_order).setIcon(R.drawable.ic_order_now);
        menu.findItem(R.id.action_order).setTitle("Checkout");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_order:
                showCustomDialog();

                return true;
            case R.id.action_back:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}