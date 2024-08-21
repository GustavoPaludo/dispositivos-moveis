package com.udesc.projetofinal.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udesc.projetofinal.R;
import com.udesc.projetofinal.models.ProductModel;
import com.udesc.projetofinal.data.ApplicationDataManager;
import com.udesc.projetofinal.servicews.CartServiceWS;

import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView recyclerViewCart;
    private TextView textViewWelcome;
    private TextView textViewTotalValue;
    private Button buttonCheckout;
    private List<ProductModel> cartProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerViewCart = view.findViewById(R.id.recyclerViewCart);
        textViewWelcome = view.findViewById(R.id.textViewWelcome);
        textViewTotalValue = view.findViewById(R.id.textViewTotalValue);
        buttonCheckout = view.findViewById(R.id.buttonCheckout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishPurchase();
            }
        });

        loadCartProducts();
        displayCartProducts();
    }

    private void loadCartProducts() {
        cartProducts = ApplicationDataManager.getCart(getContext());
    }

    private void displayCartProducts() {
        CartProductAdapter adapter = new CartProductAdapter(cartProducts);
        recyclerViewCart.setAdapter(adapter);
        double totalValue = calculateTotalValue(cartProducts);
        textViewTotalValue.setText(String.format("Valor Total: R$ %.2f", totalValue));
    }

    private double calculateTotalValue(List<ProductModel> products) {
        double totalValue = 0;
        for (ProductModel product : products) {
            totalValue += product.getPrice();
        }
        return totalValue;
    }

    private void finishPurchase() {
        new FinishPurchaseTask().execute(cartProducts);
    }

    private class FinishPurchaseTask extends AsyncTask<List<ProductModel>, Void, Boolean> {
        @Override
        protected Boolean doInBackground(List<ProductModel>... lists) {
            List<ProductModel> cartProducts = lists[0];
            CartServiceWS cartServiceWS = new CartServiceWS();
            return cartServiceWS.finishPurchase(cartProducts, getContext());
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(getContext(), "Compra finalizada com sucesso!", Toast.LENGTH_SHORT).show();
                ApplicationDataManager.clearCart(getContext());
                loadCartProducts();
                displayCartProducts();
            } else {
                Toast.makeText(getContext(), "Erro ao finalizar a compra. Verifique se o item selecionado possui estoque.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder> {

        private List<ProductModel> cartProducts;

        public CartProductAdapter(List<ProductModel> cartProducts) {
            this.cartProducts = cartProducts;
        }

        @NonNull
        @Override
        public CartProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_product, parent, false);
            return new CartProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CartProductViewHolder holder, int position) {
            ProductModel product = cartProducts.get(position);
            holder.textViewProductName.setText(product.getName());
            holder.textViewProductDescription.setText(product.getDescription());
            holder.textViewProductPrice.setText(String.format("R$ %.2f", product.getPrice()));

            holder.buttonRemoveFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeFromCart(product);
                }
            });
        }

        @Override
        public int getItemCount() {
            return cartProducts.size();
        }

        class CartProductViewHolder extends RecyclerView.ViewHolder {
            TextView textViewProductName;
            TextView textViewProductDescription;
            TextView textViewProductPrice;
            Button buttonRemoveFromCart;

            public CartProductViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewProductName = itemView.findViewById(R.id.textViewProductName);
                textViewProductDescription = itemView.findViewById(R.id.textViewProductDescription);
                textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
                buttonRemoveFromCart = itemView.findViewById(R.id.buttonRemoveFromCart);
            }
        }
    }

    private void removeFromCart(ProductModel product) {
        ApplicationDataManager.removeFromCart(getContext(), product);
        loadCartProducts();
        displayCartProducts();
    }
}
