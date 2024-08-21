package com.udesc.projetofinal.servicews;

import android.content.Context;
import android.util.Log;

import com.udesc.projetofinal.AppConstants;
import com.udesc.projetofinal.data.ApplicationDataManager;
import com.udesc.projetofinal.models.UserModel;
import org.json.JSONObject;

public class UserServiceWS {

    public UserModel doLogin(String email, String password, Context context) {
        UserModel userModel = null;
        String url = AppConstants.serverUrl + "/user-api/get-user?email=" + email + "&password=" + password;
        HttpHandler httpHandler = new HttpHandler();
        String jsonStr = null;

        try {
            jsonStr = httpHandler.makeServiceCall(url);
        } catch (Exception e) {
            Log.e("ERRO","Erro de conexão:" + e.getMessage());
        }

        Log.d("RESPONSE", "GET Response Code: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                userModel = new UserModel();
                userModel.setId(jsonObj.getLong("id"));
                userModel.setName(jsonObj.getString("name"));
                userModel.setLastName(jsonObj.getString("lastName"));
                userModel.setEmail(jsonObj.getString("email"));
                userModel.setPassword(jsonObj.getString("password"));
                ApplicationDataManager.saveUser(context, userModel);

                return userModel;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Fixado para retornar a fim de que a aplicação funcione sem o servidor, apenas com dados estáticos
        return this.generateUser(context);

//        return null;
    }

    public UserModel doRegister(UserModel userModel, Context context) {
        String url = AppConstants.serverUrl + "/user-api/register-user";
        HttpHandler httpHandler = new HttpHandler();

        try {
            String jsonResponse = null;

            try {
                jsonResponse = httpHandler.makePostRequest(url, userModel);
            } catch (Exception e) {
                Log.e("ERRO","Erro de conexão:" + e.getMessage());
            }

            if (jsonResponse != null) {
                JSONObject jsonObj = new JSONObject(jsonResponse);

                userModel = new UserModel();
                userModel.setId(jsonObj.getLong("id"));
                userModel.setName(jsonObj.getString("name"));
                userModel.setLastName(jsonObj.getString("lastName"));
                userModel.setEmail(jsonObj.getString("email"));
                userModel.setPassword(jsonObj.getString("password"));
                ApplicationDataManager.saveUser(context, userModel);

                return userModel;
            } else {
                Log.e("doRegister", "Resposta do servidor nula");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("doRegister", "Erro ao fazer a chamada POST", e);
        }

        //Fixado para retornar a fim de que a aplicação funcione sem o servidor, apenas com dados estáticos
        return this.generateUser(context);

//        return null;
    }

    public UserModel generateUser(Context context) {
        UserModel userModel = new UserModel();
        userModel.setEmail("ana@gmail.com");
        userModel.setName("Ana");
        userModel.setLastName("Clara");
        userModel.setId(12l);
        userModel.setPassword("senha123");

        ApplicationDataManager.saveUser(context, userModel);
        return userModel;
    }
}

