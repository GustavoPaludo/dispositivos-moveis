package com.udesc.projetofinal.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.udesc.projetofinal.models.OrderModel;
import com.udesc.projetofinal.models.ProductModel;
import com.udesc.projetofinal.models.UserModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApplicationDataManager {
    private static final String PREF_NAME_USER = "UserData";
    private static final String KEY_USER = "user";
    private static final String PREF_NAME_PRODUCT = "ProductData";
    private static final String KEY_PRODUCT_LIST = "product";
    private static final String PREF_NAME_CART = "CartData";
    private static final String KEY_CART_LIST = "cart";

    private static final String PREF_NAME_ORDER = "OrderData";
    private static final String KEY_ORDER_LIST = "order";

    public static void saveUser(Context context, UserModel userModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userModel);
        editor.putString(KEY_USER, json);
        editor.apply();
    }

    public static UserModel getUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_USER, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_USER, null);
        return gson.fromJson(json, UserModel.class);
    }

    public static void clearUserData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USER);
        editor.apply();
    }

    public static void saveProducts(Context context, List<ProductModel> productList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_PRODUCT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(productList);
        editor.putString(KEY_PRODUCT_LIST, json);
        editor.apply();
    }

    public static List<ProductModel> getProducts(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_PRODUCT, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_PRODUCT_LIST, null);
        if (json != null) {
            Type type = new TypeToken<List<ProductModel>>() {}.getType();
            return gson.fromJson(json, type);
        }
        return new ArrayList<>();
    }

    public static void clearProductList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_PRODUCT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_PRODUCT_LIST);
        editor.apply();
    }

    public static void saveCart(Context context, List<ProductModel> cartList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_CART, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartList);
        editor.putString(KEY_CART_LIST, json);
        editor.apply();
    }

    public static List<ProductModel> getCart(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_CART, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_CART_LIST, null);
        if (json != null) {
            Type type = new TypeToken<List<ProductModel>>() {}.getType();
            return gson.fromJson(json, type);
        }
        return new ArrayList<>();
    }

    public static void addToCart(Context context, ProductModel product) {
        List<ProductModel> cartList = getCart(context);
        cartList.add(product);
        saveCart(context, cartList);
        List<ProductModel> list = getCart(context);
        Log.d("CART", "CART: " + list);
    }

    public static void removeFromCart(Context context, ProductModel productToRemove) {
        List<ProductModel> cartList = getCart(context);

        Iterator<ProductModel> iterator = cartList.iterator();
        while (iterator.hasNext()) {
            ProductModel product = iterator.next();
            if (product.getId() == productToRemove.getId()) {
                iterator.remove();
                break;
            }
        }

        saveCart(context, cartList);
    }

    public static void clearCart(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_CART, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_CART_LIST);
        editor.apply();
    }

    public static void saveOrders(Context context, List<OrderModel> orderList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_ORDER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(orderList);
        editor.putString(KEY_ORDER_LIST, json);
        editor.apply();
    }

    public static List<OrderModel> getOrders(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_ORDER, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_ORDER_LIST, null);
        if (json != null) {
            Type type = new TypeToken<List<OrderModel>>() {}.getType();
            return gson.fromJson(json, type);
        }
        return new ArrayList<>();
    }

    public static void clearOrderList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME_ORDER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_ORDER_LIST);
        editor.apply();
    }
}

