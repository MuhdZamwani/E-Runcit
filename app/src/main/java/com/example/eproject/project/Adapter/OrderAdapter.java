package com.example.eproject.project.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eproject.R;
import com.example.eproject.project.user.Order;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OrderAdapter extends FirebaseRecyclerAdapter<Order, OrderAdapter.ViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrderAdapter(@NonNull FirebaseRecyclerOptions<Order> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Order order) {
        Glide.with(holder.picPro.getContext()).load(order.getImageUrl()).into(holder.picPro);
        holder.titlePro.setText(order.getProductName());
        holder.priPro.setText(String.valueOf(order.getTotalPrice()));
        holder.shopName.setText(order.getShopName());
        holder.shopPhone.setText(order.getShopPhoneNo());
        holder.shopAdd.setText(order.getShopAdd());
        holder.quantity.setText(String.valueOf(order.getQuantity()));
        holder.status.setText(order.getStatus());

        holder.btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.picPro.getContext());
                builder.setTitle("Are You Sure?");
                builder.setMessage("Your Delivery May Not Arrived yet");

                builder.setPositiveButton("Deliver Out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = "Order Deliver Out";
                        HashMap<String, Object> OrderData = new HashMap<>();
                        OrderData.put("Status", s);

                        FirebaseDatabase.getInstance().getReference().child("Order").child(getRef(position).getKey()).updateChildren(OrderData)
                                .addOnSuccessListener(new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        //Toast.makeText(holder.picPro.getContext(), "Thanks For Your Purchase", Toast.LENGTH_SHORT).show();
                                        holder.btnReceive.setVisibility(View.INVISIBLE);
                                    }
                                });

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(holder.productname.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_order_adapter, parent, false);
        return new ViewHolder(inflate);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picPro;
        TextView titlePro, priPro, shopName, shopPhone, shopAdd, status, quantity, btnReceive;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            picPro = itemView.findViewById(R.id.pic_order_adapter);
            titlePro = itemView.findViewById(R.id.or_title_item);
            priPro = itemView.findViewById(R.id.or_price_item);
            shopName = itemView.findViewById(R.id.or_CusName_item);
            shopPhone = itemView.findViewById(R.id.ct_pNum_item);
            shopAdd = itemView.findViewById(R.id.ct_address_item);
            status = itemView.findViewById(R.id.ct_status_item);
            quantity = itemView.findViewById(R.id.qt_item);
            btnReceive = itemView.findViewById(R.id.ct_Receive_item);
        }
    }
}
