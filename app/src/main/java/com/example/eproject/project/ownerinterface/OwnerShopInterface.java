package com.example.eproject.project.ownerinterface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eproject.R;
import com.example.eproject.project.Adapter.ShopProductAdapter;
import com.example.eproject.project.GlobalOnline;
import com.example.eproject.project.user.Products;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OwnerShopInterface extends AppCompatActivity {

    private DatabaseReference  SellerRef, ProductsRef;


    private ImageButton ownerhome, ownermanual, ownerprofilebtn, ownerorder;
    private EditText ShopProSearch;
    private TextView OwnerShopName;
    private Button productnew;

    private RecyclerView recyclerViewShopProductList;
    private ShopProductAdapter adaptershop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_owner_shop_interface);


        SellerRef = FirebaseDatabase.getInstance().getReference().child("Seller").child(GlobalOnline.currentOnline.getUsername()).child("Seller Details").child("ShopName");

        OwnerShopName = findViewById(R.id.ShopNameText);
        SellerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sn = snapshot.getValue(String.class);
                OwnerShopName.setText(sn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ownerhome = (ImageButton) findViewById(R.id.homeBtn3);
        ownerhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerHomeInterface();
            }
        });

        ownerprofilebtn = (ImageButton) findViewById(R.id.ProfileBtn3);
        ownerprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerProfileInterface();
            }
        });

        ownermanual = (ImageButton) findViewById(R.id.manualBtn3);
        ownermanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerManualInterface();
            }
        });

        ownerorder = (ImageButton) findViewById(R.id.OrderBtn3);
        ownerorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerOrderInterface();
            }
        });

        productnew = (Button) findViewById(R.id.addproduct);
        productnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerAddProductInterface();
            }
        });

        ShopProSearch = findViewById(R.id.ShopInterfaceSearchBar2);
        ShopProSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                String SearchText = ShopProSearch.getText().toString();
                SearchShopPro(SearchText);
                return false;
            }
        });

        recyclerViewShopCustom();
    }

    private void SearchShopPro(String str) {
//        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Products")//.child(String.valueOf("By" == GlobalOnline.username))
                        .orderByChild("ProductName")
                        .startAt(str)
                        .endAt(str + "-"),Products.class)
                .build();
//        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
//                .setQuery(ProductsRef, new SnapshotParser<Products>() {
//                    @NonNull
//                    @Override
//                    public Products parseSnapshot(@NonNull DataSnapshot snapshot) {
//                        return new Products(snapshot.child("By").getValue().toString(),
//                                snapshot.child("By").getValue().toString());
//                    }
//                })
//                .build();

        adaptershop = new ShopProductAdapter(options);
        adaptershop.startListening();
        recyclerViewShopProductList.setAdapter(adaptershop);

        closekey();
    }

    private void closekey() {
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(ShopProSearch.getWindowToken(), 0);
        }
    }

    public void openOwnerHomeInterface(){
        Intent intent = new Intent(this, MainActivityOwner.class);
        startActivity(intent);
    }

    public void openOwnerProfileInterface(){
        Intent intent = new Intent(this, OwnerProfileInterface.class);
        startActivity(intent);
    }

    public void openOwnerManualInterface(){
        Intent intent = new Intent(this, OwnerManualInterface.class);
        startActivity(intent);
    }

    public void openOwnerOrderInterface(){
        Intent intent = new Intent(this, OwnerOrderInterface.class);
        startActivity(intent);
    }

    public void openOwnerAddProductInterface(){
        Intent intent = new Intent(this, AddNewProduct.class);
//        intent.putExtra("UserName",username);
        startActivity(intent);
    }


    private void recyclerViewShopCustom() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewShopProductList = findViewById(R.id.recyclerViewShop);
        recyclerViewShopProductList.setLayoutManager(linearLayoutManager);

        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
               .setQuery(FirebaseDatabase.getInstance().getReference()
                       .child("Products")
                       .orderByChild("By")
                       .equalTo(GlobalOnline.currentOnline.getUsername()),Products.class).build();

        adaptershop = new ShopProductAdapter(options);
        recyclerViewShopProductList.setAdapter(adaptershop);

//        Query query = FirebaseDatabase.getInstance().getReference("Products").orderByChild("By").equalTo(GlobalOnline.username);
//        query.addListenerForSingleValueEvent(valueEventListener);
    }

//    ValueEventListener valueEventListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot snapshot) {
//            productsList.clear();
//            if (snapshot.exists()) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Products pro = snapshot.getValue(Products.class);
//                    productsList.add(pro);
//                }
//                adaptershop.notifyDataSetChanged();
//            }
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//    };

    @Override
    protected void onStart() {
        super.onStart();
        adaptershop.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adaptershop.stopListening();
    }
}