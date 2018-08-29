package com.project.softdev.softdevproject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Iterator;


public class CartActivity extends BrowserActivity {

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
        return Session.getInstance().getCartIterator();
    }


    private void showCustomDialog() {

        LayoutInflater mInflater = this.getLayoutInflater();
        View mView = mInflater.inflate(R.layout.payment_layout, null);

            new AlertDialog.Builder(this)
                    .setTitle("Confirm transaction")
                    .setMessage("If you've reviewed your cart, please enter your full name, complete Address, City and Contact Number to complete your order.")
                    .setView(mView)
                    .setPositiveButton("Order", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Session.getInstance().checkout();
                            Toast.makeText(getApplicationContext(), "You have ordered your medicine, wait for the delivery.", Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
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
        switch (item.getItemId()) {
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