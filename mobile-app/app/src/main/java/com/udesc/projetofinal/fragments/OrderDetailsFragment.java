package com.udesc.projetofinal.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.udesc.projetofinal.R;
import com.udesc.projetofinal.models.OrderModel;
import com.udesc.projetofinal.models.ProductModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderDetailsFragment extends Fragment {

    private static final String ARG_ORDER = "order";

    private OrderModel order;
    private RecyclerView recyclerViewOrderDetails;

    public static OrderDetailsFragment newInstance(OrderModel order) {
        OrderDetailsFragment fragment = new OrderDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ORDER, order);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            order = (OrderModel) getArguments().getSerializable(ARG_ORDER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_details, container, false);

        TextView textViewOrderId = view.findViewById(R.id.textViewOrderId);
        TextView textViewOrderDate = view.findViewById(R.id.textViewOrderDate);
        TextView textViewOrderTotal = view.findViewById(R.id.textViewOrderTotal);
        recyclerViewOrderDetails = view.findViewById(R.id.recyclerViewOrderDetails);

        if (order != null) {
            textViewOrderId.setText("Código do Pedido: " + order.getId().toString());
            textViewOrderDate.setText("Data do Pedido: " + formatDate(order.getRegisterDate()));
            textViewOrderTotal.setText("Valor Total: " + String.format("$ %.2f", order.getValue()));

            // Configurar o RecyclerView
            recyclerViewOrderDetails.setLayoutManager(new LinearLayoutManager(getContext()));
            OrderItemAdapter adapter = new OrderItemAdapter(order.getProductList());
            recyclerViewOrderDetails.setAdapter(adapter);
        }
        return view;
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return dateFormat.format(date);
    }

    private static class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {

        private List<ProductModel> productList;

        public OrderItemAdapter(List<ProductModel> productList) {
            this.productList = productList;
        }

        @NonNull
        @Override
        public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product_order, parent, false);
            return new OrderItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
            ProductModel product = productList.get(position);
            holder.textViewProductName.setText(product.getName());
            holder.textViewProductDescription.setText(product.getDescription());
            holder.textViewProductPrice.setText(String.format("$ %.2f", product.getPrice()));
            holder.textViewQuantityOrder.setText("Quantidade: " + product.getQuantity().toString());
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        static class OrderItemViewHolder extends RecyclerView.ViewHolder {
            TextView textViewProductName, textViewProductDescription, textViewProductPrice, textViewQuantityOrder;

            public OrderItemViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewProductName = itemView.findViewById(R.id.textViewProductName);
                textViewProductDescription = itemView.findViewById(R.id.textViewProductDescription);
                textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
                textViewQuantityOrder = itemView.findViewById(R.id.textViewQuantityOrder);
            }
        }
    }
}
