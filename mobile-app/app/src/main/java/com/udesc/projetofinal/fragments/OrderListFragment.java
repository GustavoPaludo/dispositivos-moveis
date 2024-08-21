package com.udesc.projetofinal.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udesc.projetofinal.R;
import com.udesc.projetofinal.data.ApplicationDataManager;
import com.udesc.projetofinal.models.OrderModel;
import com.udesc.projetofinal.servicews.OrderServiceWS;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class OrderListFragment extends Fragment {

    private RecyclerView recyclerViewOrder;
    private OrderAdapter orderAdapter;
    private TextView noItemsTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);

        recyclerViewOrder = view.findViewById(R.id.recyclerViewOrder);
        recyclerViewOrder.setLayoutManager(new LinearLayoutManager(getContext()));

        noItemsTextView = view.findViewById(R.id.noItems);

        loadOrders();

        return view;
    }

    private void loadOrders() {
        new FetchOrdersTask().execute();
    }

    private class FetchOrdersTask extends AsyncTask<Void, Void, List<OrderModel>> {
        @Override
        protected List<OrderModel> doInBackground(Void... voids) {
            OrderServiceWS orderServiceWS = new OrderServiceWS();
            Long userId = ApplicationDataManager.getUser(getContext()).getId();
            return orderServiceWS.getOrders(getContext(), userId);
        }

        @Override
        protected void onPostExecute(List<OrderModel> orderList) {
            if (orderList == null || orderList.isEmpty()) {
                noItemsTextView.setVisibility(View.VISIBLE);
                recyclerViewOrder.setVisibility(View.GONE);
            } else {
                noItemsTextView.setVisibility(View.GONE);
                recyclerViewOrder.setVisibility(View.VISIBLE);
                orderAdapter = new OrderAdapter(orderList);
                recyclerViewOrder.setAdapter(orderAdapter);
            }
        }
    }

    private class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

        private List<OrderModel> orderList;

        public OrderAdapter(List<OrderModel> orderList) {
            this.orderList = orderList;
        }

        @NonNull
        @Override
        public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_order, parent, false);
            return new OrderViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
            OrderModel order = orderList.get(position);

            holder.textViewOrderId.setText("Pedido #" + order.getId());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            holder.textViewOrderDate.setText("Data: " + dateFormat.format(order.getRegisterDate()));
            holder.textViewOrderTotal.setText("Total: $" + order.getValue());

            holder.itemView.setOnClickListener(v -> {
                OrderDetailsFragment fragment = OrderDetailsFragment.newInstance(order);
                Bundle args = new Bundle();
                args.putSerializable("order", order);
                Navigation.findNavController(v).navigate(R.id.orderDetailsFragment, args);
            });
        }

        @Override
        public int getItemCount() {
            return orderList.size();
        }

        class OrderViewHolder extends RecyclerView.ViewHolder {
            TextView textViewOrderId, textViewOrderDate, textViewOrderTotal;

            public OrderViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewOrderId = itemView.findViewById(R.id.textViewOrderId);
                textViewOrderDate = itemView.findViewById(R.id.textViewOrderDate);
                textViewOrderTotal = itemView.findViewById(R.id.textViewOrderTotal);
            }
        }
    }
}
