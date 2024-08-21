package com.udesc.projetofinal.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.udesc.projetofinal.R;
import com.udesc.projetofinal.models.ProductModel;
import com.udesc.projetofinal.data.ApplicationDataManager;
import com.udesc.projetofinal.servicews.ProductServiceWS;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    private RecyclerView recyclerViewProducts;
    private List<ProductModel> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        new FetchProductsTask().execute();
        return view;
    }

    private class FetchProductsTask extends AsyncTask<Void, Void, List<ProductModel>> {
        @Override
        protected List<ProductModel> doInBackground(Void... voids) {
            ProductServiceWS productServiceWS = new ProductServiceWS();
            return productServiceWS.getProducts(getContext());
        }

        @Override
        protected void onPostExecute(List<ProductModel> products) {
            productList = filterProductList(products);
            ProductAdapter adapter = new ProductAdapter(productList);
            recyclerViewProducts.setAdapter(adapter);
        }

        private List<ProductModel> filterProductList(List<ProductModel> products) {
            List<ProductModel> filteredList = new ArrayList<>();
            for (ProductModel product : products) {
                if (product.getQuantity() > 0) {
                    filteredList.add(product);
                }
            }
            return filteredList;
        }
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

        private List<ProductModel> productList;

        public ProductAdapter(List<ProductModel> productList) {
            this.productList = productList;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            ProductModel product = productList.get(position);
            holder.textViewProductName.setText(product.getName());
            holder.textViewProductDescription.setText(product.getDescription());
            holder.textViewProductPrice.setText(String.format("$ %.2f", product.getPrice()));
            holder.textViewProductQuantity.setText(product.getQuantity().toString());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductDetailsFragment fragment = ProductDetailsFragment.newInstance(product);
                    Bundle args = new Bundle();
                    args.putSerializable("product", product);
                    Navigation.findNavController(v).navigate(R.id.productDetailsFragment, args);
                }
            });

            holder.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addToCart(product);
                }
            });
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        class ProductViewHolder extends RecyclerView.ViewHolder {
            TextView textViewProductName, textViewProductDescription, textViewProductPrice, textViewProductQuantity;
            Button buttonAddToCart;

            public ProductViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewProductName = itemView.findViewById(R.id.textViewProductName);
                textViewProductDescription = itemView.findViewById(R.id.textViewProductDescription);
                textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
                buttonAddToCart = itemView.findViewById(R.id.buttonAddToCart);
                textViewProductQuantity = itemView.findViewById(R.id.textViewProductQuantity);
            }
        }
    }

    private void addToCart(ProductModel product) {
        ApplicationDataManager.addToCart(getContext(), product);
        Toast.makeText(getContext(), "Item adicionado com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
