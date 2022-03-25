package com.example.eproject.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eproject.R;
import com.example.eproject.project.ownerinterface.MainActivityOwner;
import com.example.eproject.project.user.Seller;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OwnerLogin extends AppCompatActivity {

    EditText InputUsername,InputPass;
    private Button ownersignup,ownerlogin;
    private String parentDbName = "Seller";
    private String category = "Seller Details";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_owner_login);

        InputUsername = (EditText) findViewById(R.id.Username1);
        InputPass = (EditText)findViewById(R.id.Pass1);
        ownerlogin = (Button)findViewById(R.id.OwnerLoginEnter);
        ownersignup = (Button) findViewById(R.id.OwnerSignUp);

        ownerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownerLogin();
            }
        });

        ownersignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerSignUp();
            }
        });
    }

    private void ownerLogin() {

        String username = InputUsername.getText().toString();
        String Pass = InputPass.getText().toString();

        if (TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "Please Enter your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Pass))
        {
            Toast.makeText(this, "Please Enter your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            AllowAccessToAccount(username, Pass);
        }
    }

    private void AllowAccessToAccount(final String username, final String Pass){

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentDbName).child(username).exists()){

                    Seller sellerData = snapshot.child(parentDbName).child(username).child(category).getValue(com.example.eproject.project.user.Seller.class);

                    if (sellerData.getUsername().equals(username)){

                        if (sellerData.getPass().equals(Pass)){

                            if(parentDbName.equals("Seller")){

                                Toast.makeText(OwnerLogin.this, "Welcome Admin, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(OwnerLogin.this, MainActivityOwner.class);
                                GlobalOnline.currentOnline = sellerData;
                                progressDialog = new ProgressDialog(OwnerLogin.this);
                                progressDialog.show();
                                progressDialog.setContentView(R.layout.loading_animation);
                                progressDialog.setCancelable(true);
                                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                //intent.putExtra("UserName",username);
                                startActivity(intent);
                            }
                        }
                        else{
                            Toast.makeText(OwnerLogin.this,"Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(OwnerLogin.this, "Account with this " + username + " number do not exists.", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openOwnerSignUp(){
        Intent intent = new Intent(this, OwnerSignUp.class);
        startActivity(intent);
    }
}