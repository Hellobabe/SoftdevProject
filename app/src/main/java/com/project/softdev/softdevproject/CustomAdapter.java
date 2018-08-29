package com.project.softdev.softdevproject;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter implements View.OnClickListener {

    private Activity activity;
    private ArrayList list;
    private static LayoutInflater inflater=null;
    public Resources resources;

    public CustomAdapter(Activity activity, ArrayList list, Resources resources) {
        this.activity = activity;
        this.list =list;
        this.resources = resources;

        inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateList(ArrayList<ListItemModel> newList){
        list = newList;
        notifyDataSetChanged();
    }

    public int getCount() {
        if(list.size()<=0)
            return 1;
        return list.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        private TextView text;
        private TextView price;
        private ImageView image;
        private TextView qty;
        private ImageView adder;
    }

    /****** Depends upon list size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        final ViewHolder holder;

        if(convertView==null){
            holder = new ViewHolder();

            /****** Inflate list_item.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.list_item, null);

            /****** View Holder Object to contain list_item.xml file elements ******/

            holder.text = (TextView) vi.findViewById(R.id.list_item_name);
            holder.price =(TextView)vi.findViewById(R.id.list_item_price);
            holder.image=(ImageView)vi.findViewById(R.id.list_item_image);
            holder.qty =(TextView)vi.findViewById(R.id.list_item_qty);
            holder.adder=(ImageView)vi.findViewById(R.id.list_item_option);

            if(activity instanceof CustomerActivity) {
                holder.adder.setImageResource(R.drawable.add_cart_button);
                holder.qty.setVisibility(View.GONE);
            }
            else if(activity instanceof CartActivity) {
                holder.adder.setImageResource(R.drawable.remove_button);
                holder.adder.setVisibility(View.GONE);
                holder.qty.setVisibility(View.VISIBLE);
            }
            /*else{
                holder.adder.setImageResource(R.drawable.icon_edit);
            }*/

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(list.size()<=0)
        {
            holder.text.setText(" Your Cart is empty");

        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            ListItemModel tempValues = null;
            tempValues = ( ListItemModel ) list.get( position );

            /************  Set Model values in Holder elements ***********/

            holder.text.setText(tempValues.getProductName());
            holder.price.setText(tempValues.getPrice());
            holder.image.setImageResource(tempValues.getImageResource());
            holder.qty.setText("Qty: \n" + tempValues.getQty());



            /******* Set Item Click Listner for LayoutInflater for each row *******/

            vi.setOnClickListener(new OnItemClickListener(position,false));
            holder.adder.setOnClickListener(new OnItemClickListener(position, true));
            holder.adder.setVisibility(View.VISIBLE);

        }
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");

    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements View.OnClickListener {
        private int mPosition;
        private boolean addRequest;

        OnItemClickListener(int position, boolean request){
            mPosition = position;
            addRequest = request;
        }

        @Override
        public void onClick(View v) {
            final BrowserActivity browserActivity = (BrowserActivity) activity;
            /****  Call  onItemClick Method inside CustomerActivity Class ( See Below )****/
            browserActivity.onItemClick(mPosition, addRequest);

        }
    }
}