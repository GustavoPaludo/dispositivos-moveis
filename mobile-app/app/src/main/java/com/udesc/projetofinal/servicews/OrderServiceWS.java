package com.udesc.projetofinal.servicews;

import android.content.Context;
import android.util.Log;

import com.udesc.projetofinal.AppConstants;
import com.udesc.projetofinal.data.ApplicationDataManager;
import com.udesc.projetofinal.models.OrderModel;
import com.udesc.projetofinal.models.ProductModel;
import com.udesc.projetofinal.models.UserModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class OrderServiceWS {
    public List<OrderModel> getOrders(Context context, Long userId) {
        ApplicationDataManager.clearOrderList(context);

        List<OrderModel> orderList = new ArrayList<>();
        String url = AppConstants.serverUrl + "/order-api/get-order-by-user?userId=" + userId;
        HttpHandler httpHandler = new HttpHandler();
        String jsonStr = httpHandler.makeServiceCall(url);
        Log.d("RESPONSE", "GET Response Code: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonStr);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    OrderModel orderModel = new OrderModel();
                    orderModel.setId(jsonObj.getLong("id"));
                    String registerDateStr = jsonObj.getString("registerDate");
                    Date registerDate = dateFormat.parse(registerDateStr);
                    orderModel.setRegisterDate(registerDate);                    orderModel.setValue(jsonObj.getDouble("value"));

                    JSONObject userJson = jsonObj.getJSONObject("userModel");
                    UserModel userModel = new UserModel();
                    userModel.setId(userJson.getLong("id"));
                    userModel.setName(userJson.getString("name"));
                    userModel.setEmail(userJson.getString("email"));
                    orderModel.setUserModel(userModel);

                    JSONArray productListJson = jsonObj.getJSONArray("productList");
                    List<ProductModel> productList = new ArrayList<>();
                    for (int j = 0; j < productListJson.length(); j++) {
                        JSONObject productJson = productListJson.getJSONObject(j);
                        ProductModel productModel = new ProductModel();
                        productModel.setId(productJson.getInt("id"));
                        productModel.setName(productJson.getString("name"));
                        productModel.setDescription(productJson.getString("description"));
                        productModel.setPrice(productJson.getDouble("price"));
                        productModel.setQuantity(productJson.getInt("quantity"));
                        productList.add(productModel);
                    }
                    orderModel.setProductList(productList);

                    orderList.add(orderModel);
                }
                ApplicationDataManager.saveOrders(context, orderList);

                return orderList;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Fixado para retornar a fim de que a aplicação funcione sem o servidor, apenas com dados estáticos
        return this.generateDefaultList(context);
//        return null;
    }

    private List<OrderModel> generateDefaultList(Context context) {
        List<OrderModel> orderModelList = new ArrayList<OrderModel>();
        for (Integer index = 0; index < 5; index ++) {
            OrderModel orderModel = new OrderModel();
            orderModel.setId(index.longValue());
            orderModel.setValue(10.0);
            orderModel.setRegisterDate(new Date());
            UserServiceWS userServiceWS = new UserServiceWS();
            orderModel.setUserModel(userServiceWS.generateUser(context));

            ProductServiceWS productServiceWS = new ProductServiceWS();
            orderModel.setProductList(productServiceWS.getDefaultProductList());
            orderModelList.add(orderModel);
        }
        return orderModelList;
    }
}

