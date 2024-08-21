package com.udesc.projetofinal.servicews;

import android.content.Context;
import android.util.Log;

import com.udesc.projetofinal.AppConstants;
import com.udesc.projetofinal.data.ApplicationDataManager;
import com.udesc.projetofinal.models.ProductModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceWS {
    public List<ProductModel> getProducts(Context context) {
        ApplicationDataManager.clearProductList(context);

        List<ProductModel> productList = new ArrayList<>();
        String url = AppConstants.serverUrl + "/product-api/get-products";
        HttpHandler httpHandler = new HttpHandler();
        String jsonStr = httpHandler.makeServiceCall(url);
        Log.d("RESPONSE", "GET Response Code: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonStr);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    ProductModel productModel = new ProductModel();
                    productModel.setId(jsonObj.getInt("id"));
                    productModel.setName(jsonObj.getString("name"));
                    productModel.setDescription(jsonObj.getString("description"));
                    productModel.setPrice(jsonObj.getDouble("price"));
                    productModel.setQuantity(jsonObj.getInt("quantity"));

                    productList.add(productModel);
                }
                ApplicationDataManager.saveProducts(context, productList);

                return productList;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Fixado para retornar a fim de que a aplicação funcione sem o servidor, apenas com dados estáticos
        return this.getDefaultProductList();
//        return null;
    }

    public List<ProductModel> getDefaultProductList() {
        ProductModel productModel1 = new ProductModel();
        productModel1.setId(1);
        productModel1.setName("Arroz 500g");
        productModel1.setDescription("Arroz integral");
        productModel1.setPrice(10.0);
        productModel1.setQuantity(5);

        ProductModel productModel2 = new ProductModel();
        productModel2.setId(2);
        productModel2.setName("Copo 200ml");
        productModel2.setDescription("Vidro detalhado");
        productModel2.setPrice(20.0);
        productModel2.setQuantity(10);

        ProductModel productModel3 = new ProductModel();
        productModel3.setId(3);
        productModel3.setName("Monitor 4k");
        productModel3.setDescription("32 polegadas");
        productModel3.setPrice(2000.0);
        productModel3.setQuantity(3);

        List<ProductModel> productList = new ArrayList<>();
        productList.add(productModel1);
        productList.add(productModel2);
        productList.add(productModel3);
        productList.add(productModel3);
        productList.add(productModel3);
        productList.add(productModel3);

        return productList;
    }
}

