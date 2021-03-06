package com.project.softdev.softdevproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


public class ProductView extends AppCompatActivity {

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_view_activity);

        ImageView imageView = (ImageView) findViewById(R.id.product_image_full);
        EditText nameView = (EditText) findViewById(R.id.product_name_full);
        EditText priceView = (EditText) findViewById(R.id.product_price_full);
        EditText costView = (EditText) findViewById(R.id.product_cost_full);
        EditText qtyView = (EditText) findViewById(R.id.product_qty_full);
        EditText descriptionView = (EditText) findViewById(R.id.product_description_full);

        ListItemModel item = (ListItemModel) getIntent().getExtras().get("content");

        imageView.setImageResource(item.getImageResource());
        nameView.setText(item.getProductName());
        priceView.setText("" + item.getPrice());
        qtyView.setText("" + item.getQty());
        qtyView.setVisibility(View.GONE);
        descriptionView.setText(item.getDescription());

        nameView.setKeyListener(null);
        priceView.setKeyListener(null);
        qtyView.setKeyListener(null);
        descriptionView.setKeyListener(null);

        View v = findViewById(R.id.product_view_invoice);
        v.setVisibility(View.GONE);
        if(Session.getInstance().isSeller()){
            costView.setText("" + item.getCost());
            costView.setKeyListener(null);
            v.setVisibility(View.VISIBLE);
            }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.product_view_toolbar);
        setSupportActionBar(toolbar);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        menu.findItem(R.id.action_order).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_back:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
