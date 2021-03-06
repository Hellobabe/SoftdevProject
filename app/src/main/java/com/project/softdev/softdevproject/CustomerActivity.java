package com.project.softdev.softdevproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Iterator;


public class CustomerActivity extends BrowserActivity {

    @Override
    protected int setLayout() {
        return R.layout.browser_activity;
    }

    @Override
    protected int getToolbar() {
        return R.id.browser_toolbar;
    }

    @Override
    protected Activity getContext() {
        return this;
    }

    @Override
    protected Resources getRes() {
        return getResources();
    }

    @Override
    protected Iterator getIterator() {
        return Session.getInstance().getMasterIterator();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.customer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_cart:
                startActivity(new Intent(CustomerActivity.this, CartActivity.class));
                return true;
            case R.id.return_home:
                onBackPressed();
                startActivity(new Intent(CustomerActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}