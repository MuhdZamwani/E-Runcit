package com.example.eproject.project.ownerinterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eproject.R;

public class OwnerManualInterface extends AppCompatActivity {
    private ImageButton ownerhome;
    private ImageButton ownerprofilebtn;
    private ImageButton ownerorder;
    private ImageButton ownershop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_owner_manual_interface);

        ownerhome = (ImageButton) findViewById(R.id.homeBtn4);
        ownerhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerHomeInterface();
            }
        });

        ownerprofilebtn = (ImageButton) findViewById(R.id.ProfileBtn4);
        ownerprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerProfileInterface();
            }
        });

        ownerorder = (ImageButton) findViewById(R.id.OrderBtn4);
        ownerorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerOrderInterface();
            }
        });

        ownershop = (ImageButton) findViewById(R.id.ShopBtn4);
        ownershop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerShopInterface();
            }
        });
    }
    public void openOwnerHomeInterface(){
        Intent intent = new Intent(this, MainActivityOwner.class);
        startActivity(intent);
    }

    public void openOwnerProfileInterface(){
        Intent intent = new Intent(this, OwnerProfileInterface.class);
        startActivity(intent);
    }

    public void openOwnerOrderInterface(){
        Intent intent = new Intent(this, OwnerOrderInterface.class);
        startActivity(intent);
    }

    public void openOwnerShopInterface(){
        Intent intent = new Intent(this, OwnerShopInterface.class);
        startActivity(intent);
    }
}