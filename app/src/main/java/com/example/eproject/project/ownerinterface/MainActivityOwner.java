package com.example.eproject.project.ownerinterface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eproject.R;
import com.example.eproject.project.Adapter.DisplayProductAdapter;
import com.example.eproject.project.GlobalOnline;
import com.example.eproject.project.user.Products;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class MainActivityOwner extends AppCompatActivity {
    
    private ImageButton ownerprofilebtn, ownermanual, ownerorder, ownershop;
    private EditText DisProSearch;
    private TextView ShopName;

    private RecyclerView recyclerViewShopProductList;
    private DisplayProductAdapter adaptershop;

    private DatabaseReference SellerRef, reff, reff2;
//    private Query reff2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_main_owner);

        SellerRef = FirebaseDatabase.getInstance().getReference().child("Seller").child(GlobalOnline.currentOnline.getUsername()).child("Seller Details").child("ShopName");

        ShopName = findViewById(R.id.ShopNameText);
        SellerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sn =snapshot.getValue(String.class);
                ShopName.setText(sn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ownerprofilebtn = (ImageButton) findViewById(R.id.ProfileBtn);
        ownerprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerProfileInterface();
            }
        });
//
        ownerorder = (ImageButton) findViewById(R.id.OrderBtn);
        ownerorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerOrderInterface();
            }
        });
//
        ownershop = (ImageButton) findViewById(R.id.ShopBtn);
        ownershop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerShopInterface();
            }
        });

        ownermanual = (ImageButton) findViewById(R.id.manualBtn);
        ownermanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerManualInterface();
            }
        });

        DisProSearch = findViewById(R.id.ShopInterfaceSearchBar);
        DisProSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String searchText = DisProSearch.getText().toString();
                if(searchText != null){
                    SearchDisPro(searchText);
                }
                else if(searchText == null || searchText == ""){
                    recyclerViewShopProduct();
                }
                return false;
            }
        });

        recyclerViewShopProduct();
    }

    private void SearchDisPro(String str) {
        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Products")

                        .orderByChild("ProductName")
                        .startAt(str)
                        .endAt(str + "-"),Products.class)
                .build();

        adaptershop = new DisplayProductAdapter(options);
        adaptershop.startListening();
        recyclerViewShopProductList.setAdapter(adaptershop);

        closekey();
    }

    private void closekey() {
        View view = this.getCurrentFocus();
        if(view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(DisProSearch.getWindowToken(), 0);
        }
    }

    public void openOwnerProfileInterface(){
        Intent intent = new Intent(this, OwnerProfileInterface.class);
        startActivity(intent);
    }
//
    public void openOwnerOrderInterface(){
        Intent intent = new Intent(this, OwnerOrderInterface.class);
        startActivity(intent);
    }
//
    public void openOwnerShopInterface(){
        Intent intent = new Intent(this, OwnerShopInterface.class);
        startActivity(intent);
    }

    public void openOwnerManualInterface(){
        Intent intent = new Intent(this, OwnerManualInterface.class);
        startActivity(intent);
    }

    private void recyclerViewShopProduct() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewShopProductList = findViewById(R.id.recyclerViewMain);
        recyclerViewShopProductList.setLayoutManager(linearLayoutManager);

        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>().
                setQuery(FirebaseDatabase.getInstance()
                        .getReference().child("Products").orderByChild("By").equalTo(GlobalOnline.currentOnline.getUsername()),Products.class)
                .build();

        adaptershop = new DisplayProductAdapter(options);
        recyclerViewShopProductList.setAdapter(adaptershop);
    }

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