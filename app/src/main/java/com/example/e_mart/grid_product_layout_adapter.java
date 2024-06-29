package com.example.e_mart;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class grid_product_layout_adapter extends BaseAdapter {

    List<horizontalProductScrollModel> horizontalProductScrollModels;

    public grid_product_layout_adapter(List<horizontalProductScrollModel> horizontalProductScrollModels) {
        this.horizontalProductScrollModels = horizontalProductScrollModels;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view ;
        if (convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,null);
            view.setElevation(0);
            view.setBackgroundColor(Color.parseColor("#ffffff"));

            ImageView productImage = view.findViewById(R.id.product_image_horizontal_scroll);
            TextView productTitle = view.findViewById(R.id.product_title_horizontal_scroll);
            TextView productDesc = view.findViewById(R.id.product_desc_horizontal_scroll);
            TextView productPrice = view.findViewById(R.id.product_price_horizontal_scroll);

            productImage.setImageResource(horizontalProductScrollModels.get(position).getProductImage());
            productTitle.setText(horizontalProductScrollModels.get(position).getProductTitle());

            productDesc.setText(horizontalProductScrollModels.get(position).getProductDesc());
            productPrice.setText(horizontalProductScrollModels.get(position).getProductPrice());
        }
        else{
            view = convertView;
        }
        return  view;
    }
}
