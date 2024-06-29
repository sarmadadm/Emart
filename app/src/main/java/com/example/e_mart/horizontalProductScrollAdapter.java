package com.example.e_mart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class horizontalProductScrollAdapter extends RecyclerView.Adapter<horizontalProductScrollAdapter.ViewHolder> {

    private  List<horizontalProductScrollModel>horizontalProductScrollModellist;

    public horizontalProductScrollAdapter(List<horizontalProductScrollModel> horizontalProductScrollModellist) {
        this.horizontalProductScrollModellist = horizontalProductScrollModellist;
    }

    @NonNull
    @Override
    public horizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout,viewGroup,false);
        return new ViewHolder((view) );
    }

    @Override
    public void onBindViewHolder(@NonNull horizontalProductScrollAdapter.ViewHolder viewHolder, int position) {

       int resource = horizontalProductScrollModellist.get(position).getProductImage();
       String title = horizontalProductScrollModellist.get(position).getProductTitle();
       String desc = horizontalProductScrollModellist.get(position).getProductDesc();
       String price = horizontalProductScrollModellist.get(position).getProductPrice();
    }

    @Override
    public int getItemCount() {
        return horizontalProductScrollModellist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView procImage;
        private TextView productTitle;
        private TextView productDesc;
        private TextView productPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            procImage = itemView.findViewById(R.id.product_image_horizontal_scroll);
            productTitle = itemView.findViewById(R.id.product_title_horizontal_scroll);
          productDesc = itemView.findViewById(R.id.product_desc_horizontal_scroll);
            productPrice = itemView.findViewById(R.id.product_price_horizontal_scroll);
        }
        private void setProcImage(int resource)
        {
         procImage.setImageResource(resource);
        }
        private void setProductTitle(String title)
        {
            productTitle.setText(title);
        }
        private void setProductDesc(String desc)
        {
            productDesc.setText(desc);
        }
        private void setProductPrice(String price)
        {
            productDesc.setText(price);
        }



    }
}
