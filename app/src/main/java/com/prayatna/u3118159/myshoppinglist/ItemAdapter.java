package com.prayatna.u3118159.myshoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prayatna on 24/03/16.
 */
class ItemAdapter extends ArrayAdapter<Item> {

    private List<Item> items;

    public ItemAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        items = objects;
    }

//    public ItemAdapter(Context context, ArrayList<Item> items) {
//        super(context, 0, items);
//    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.list_item, parent, false);
        }

        Item item = getItem(position);

        TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
        nameText.setText(item.getItemName());

        TextView qtyText = (TextView) convertView.findViewById(R.id.qtyText);
        qtyText.setText(Integer.toString(item.getItemQty()));

        ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
//        iv.setImageResource(R.mipmap.ic);

        return convertView;
    }
}
