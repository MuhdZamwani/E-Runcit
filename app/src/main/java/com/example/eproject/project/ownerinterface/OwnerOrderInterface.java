package com.example.eproject.project.ownerinterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eproject.R;
import com.example.eproject.project.Adapter.OrderAdapter;
import com.example.eproject.project.Adapter.ShopProductAdapter;
import com.example.eproject.project.GlobalOnline;
import com.example.eproject.project.user.Order;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

public class OwnerOrderInterface extends AppCompatActivity {

    private ImageButton ownerhome;
    private ImageButton ownerprofilebtn;
    private ImageButton ownermanual;
    private ImageButton ownershop;

    private RecyclerView recyclerViewShopOrder;
    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_owner_order_interface);

        ownerhome = (ImageButton) findViewById(R.id.homeBtn2);
        ownerhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerHomeInterface();
            }
        });
//
        ownerprofilebtn = (ImageButton) findViewById(R.id.ProfileBtn2);
        ownerprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerProfileInterface();
            }
        });

        ownermanual = (ImageButton) findViewById(R.id.manualBtn2);
        ownermanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerManualInterface();
            }
        });
//
        ownershop = (ImageButton) findViewById(R.id.ShopBtn2);
        ownershop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerShopInterface();
            }
        });

        recyclerViewShopProduct();
    }

    public void openOwnerHomeInterface(){
        Intent intent = new Intent(this, MainActivityOwner.class);
        startActivity(intent);
    }
//
    public void openOwnerProfileInterface(){
        Intent intent = new Intent(this, OwnerProfileInterface.class);
        startActivity(intent);
    }

    public void openOwnerManualInterface(){
        Intent intent = new Intent(this, OwnerManualInterface.class);
        startActivity(intent);
    }
//
    public void openOwnerShopInterface(){
        Intent intent = new Intent(this, OwnerShopInterface.class);
        startActivity(intent);
    }

    private void recyclerViewShopProduct() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewShopOrder = findViewById(R.id.recyclerViewMain);
        recyclerViewShopOrder.setLayoutManager(linearLayoutManager);

        FirebaseRecyclerOptions<Order> options = new FirebaseRecyclerOptions.Builder<Order>().
                setQuery(FirebaseDatabase.getInstance()
                        .getReference().child("Order").orderByChild("By").equalTo(GlobalOnline.currentOnline.getUsername()), Order.class)
                .build();
        adapter = new OrderAdapter(options);
        recyclerViewShopOrder.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}