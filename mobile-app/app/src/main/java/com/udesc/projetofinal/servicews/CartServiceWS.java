package com.udesc.projetofinal.servicews;

import android.content.Context;
import android.util.Log;

import com.udesc.projetofinal.AppConstants;
import com.udesc.projetofinal.data.ApplicationDataManager;
import com.udesc.projetofinal.models.ProductModel;

import java.util.List;

public class CartServiceWS {
    public Boolean finishPurchase(List<ProductModel> listProducts, Context context) {
        String url = AppConstants.serverUrl + "/cart-api/finish-purchase";

        if(ApplicationDataManager.getUser(context) != null) {
            url = url + "?userId=" + ApplicationDataManager.getUser(context).getId().toString();
        }

        HttpHandler httpHandler = new HttpHandler();
        try {
            String jsonResponse = httpHandler.makePostRequest(url, listProducts);
            if(jsonResponse.equals("OK")) {
                return true;
            } else {
                //Fixado para retornar true para que a aplicação funcione sem o servidor, apenas com dados estáticos
                return true;
//                return false;
            }
        } catch (Exception e) {
            Log.e("Error", "Erro ao finalizar compra: " + e.getMessage());

            //Fixado para retornar true para que a aplicação funcione sem o servidor, apenas com dados estáticos
//            return false;
            return true;
        }
    }

}
