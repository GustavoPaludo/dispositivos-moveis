package com.udesc.projetofinal.fragments;

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

import com.udesc.projetofinal.R;
import com.udesc.projetofinal.data.ApplicationDataManager;
import com.udesc.projetofinal.models.ProductModel;

public class ProductDetailsFragment extends Fragment {

    private static final String ARG_PRODUCT = "product";

    private ProductModel product;

    public static ProductDetailsFragment newInstance(ProductModel product) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = (ProductModel) getArguments().getSerializable(ARG_PRODUCT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        TextView textViewProductName = view.findViewById(R.id.textViewProductNameDetails);
        TextView textViewProductDescription = view.findViewById(R.id.textViewProductDescriptionDetails);
        TextView textViewProductPrice = view.findViewById(R.id.textViewProductPriceDetails);
        TextView textViewProductQuantity = view.findViewById(R.id.quantityDetails);

        Button addToCartButton = view.findViewById(R.id.buttonAddCartDetails);

        if (product != null) {
            textViewProductName.setText(product.getName());
            textViewProductDescription.setText(product.getDescription());
            textViewProductPrice.setText(String.format("$ %.2f", product.getPrice()));
            textViewProductQuantity.setText(product.getQuantity().toString());
        }

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(product);
            }
        });

        return view;
    }

    private void addToCart(ProductModel product) {
        ApplicationDataManager.addToCart(getContext(), product);
        Toast.makeText(getContext(), "Item adicionado com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
