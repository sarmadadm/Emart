package com.example.e_mart;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class homeFragment extends Fragment {


    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    public homeFragment() {
        // Required empty public constructor
    }


    /////top deals add//////
    private TextView horiLayoutTitle;
    private Button horiviewAllbtn;
    private  RecyclerView horizontalRecyler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recyler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("home", "Home"));
        categoryModelList.add(new CategoryModel("Electronics", "Electronics"));
        categoryModelList.add(new CategoryModel("Furniture", "Furniture"));
        categoryModelList.add(new CategoryModel("Fashion", "Fashion"));
        categoryModelList.add(new CategoryModel("Toys", "Toys"));
        categoryModelList.add(new CategoryModel("Sports", "Sports"));
        categoryModelList.add(new CategoryModel("Wall Art", "Wall Art"));
        categoryModelList.add(new CategoryModel("Books", "Books"));
        categoryModelList.add(new CategoryModel("Shoes", "Shoes"));
        categoryModelList.add(new CategoryModel("Mobiles", "Books"));
        categoryModelList.add(new CategoryModel("Computers", "Shoes"));
        categoryModelList.add(new CategoryModel("Cameras", "Books"));
        categoryModelList.add(new CategoryModel("Accessories", "Shoes"));


        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();


        ///////////////horizontal product
        horiLayoutTitle =view.findViewById(R.id.horizontal_scroll_title);
        horiviewAllbtn = view.findViewById(R.id.horizontal_scroll_Button);
        horizontalRecyler = view.findViewById(R.id.horizontal_scroll_recycler);

        List<horizontalProductScrollModel> horizontalProductScrollModels = new ArrayList<>();
        horizontalProductScrollModels.add(new horizontalProductScrollModel(R.drawable.splash, "Basic Title" , "splash Screen","65000"));
        horizontalProductScrollModels.add(new horizontalProductScrollModel(R.drawable.slider1, "Item 2" , "32gb - 4gb","25000"));
        horizontalProductScrollModels.add(new horizontalProductScrollModel(R.drawable.slider2, "Item 3" , "Door","65000"));
        horizontalProductScrollModels.add(new horizontalProductScrollModel(R.drawable.slider3, "Item4" , "sunset","56230"));
        horizontalProductScrollModels.add(new horizontalProductScrollModel(R.drawable.slider4, "Item5" , "glasses","50343"));
        horizontalProductScrollModels.add(new horizontalProductScrollModel(R.drawable.slider5, "Item6" , "stag","65430"));
        horizontalProductScrollModels.add(new horizontalProductScrollModel(R.drawable.slider6, "Item7" , "moonrise","32101"));
        horizontalProductScrollModels.add(new horizontalProductScrollModel(R.drawable.slider7, "Item8" , "cat","98745"));


        horizontalProductScrollAdapter horizontalProductScrollAdapter = new horizontalProductScrollAdapter(horizontalProductScrollModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyler.setLayoutManager(linearLayoutManager);

        horizontalRecyler.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();

        //////horizontal product layout

        RecyclerView testing = view.findViewById(R.id.testing);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        /////Grid Product Layout
        TextView gridlayouttitle = view.findViewById(R.id.grid_product_layout_title);
        Button gridlayoutbtn = view.findViewById(R.id.grid_product_btn);
        GridView gridView = view.findViewById(R.id.grid_product_layout_dynamic_view);

        gridView.setAdapter(new grid_product_layout_adapter(horizontalProductScrollModels));

        return  view;

    }


}
