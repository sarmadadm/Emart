package com.example.e_mart;

public class CategoryModel {
    private  String getCategoryIcon;
    private  String categoryName;

    public CategoryModel(String getCategoryIcon, String categoryName) {
        this.getCategoryIcon = getCategoryIcon;
        this.categoryName = categoryName;
    }

    public String getGetCategoryIcon() {
        return getCategoryIcon;
    }

    public void setGetCategoryIcon(String getCategoryIcon) {
        this.getCategoryIcon = getCategoryIcon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
