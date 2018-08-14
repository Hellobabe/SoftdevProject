package com.project.softdev.softdevproject;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public abstract class HomeAdapter extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());

        findViewById(R.id.camera_button).setOnClickListener(this);
        findViewById(R.id.prescript_button).setOnClickListener(this);
        findViewById(R.id.feedback_button).setOnClickListener(this);
        findViewById(R.id.non_prescript_button).setOnClickListener(this);
        findViewById(R.id.profile_button).setOnClickListener(this);
        findViewById(R.id.layout_profile_button).setOnClickListener(this);

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera_button:
                startActivity(new Intent());
                break;

            case R.id.prescript_button:
                startActivity(new Intent());
                break;

            case R.id.non_prescript_button:
                startActivity(new Intent(this, CustomerActivity.class));
                break;

            case R.id.feedback_button:
                startActivity(new Intent(this, ContactUsActivity.class));
                break;

            case R.id.profile_button:
                startActivity(new Intent(this, ProfileActivity.class));
                break;

            case R.id.layout_profile_button:
                startActivity(new Intent(this, ProfileActivity.class));
                break;

        }
    }

    protected abstract int setLayout();
    protected abstract int getToolbar();
    protected abstract Resources getRes();
}
