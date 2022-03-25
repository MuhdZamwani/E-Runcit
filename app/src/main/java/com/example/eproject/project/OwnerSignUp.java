package com.example.eproject.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class OwnerSignUp extends AppCompatActivity {

    private Button owneralreadysignup;
    private Button signUp;
    private EditText InputShopName, InputUsername, InputEmail, InputPhone, InputAddress, InputPass, InputConPass;
    private String parentDbName = "Seller";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_owner_sign_up);

        signUp = (Button) findViewById(R.id.OwnerFirstSignUp);
        owneralreadysignup = (Button) findViewById(R.id.OwnerAlreadySignUp);
        InputShopName = (EditText) findViewById(R.id.ShopName);
        InputUsername = (EditText) findViewById(R.id.Username);
        InputEmail = (EditText) findViewById(R.id.Email);
        InputPhone = (EditText) findViewById(R.id.PhoneNo);
        InputAddress = (EditText) findViewById(R.id.Address);
        InputPass = (EditText) findViewById(R.id.Pass);
        InputConPass = (EditText) findViewById(R.id.ConPass);

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                firstSignUp();
            }
        });

        owneralreadysignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerAlreadySignUp();
            }
        });
    }

    public boolean firstSignUp(){
        String ShopName = InputShopName.getText().toString();
        String UserName = InputUsername.getText().toString();
        String Email = InputEmail.getText().toString();
        String PhoneNo = InputPhone.getText().toString();
        String Address = InputAddress.getText().toString();
        String Password = InputPass.getText().toString();
        String ConPass = InputConPass.getText().toString();

        if (TextUtils.isEmpty(ShopName))
        {
            //Toast.makeText(this, "Please write Shop Name", Toast.LENGTH_SHORT).show();
            showError(InputShopName,"Please Enter Shop Name");
            return false;
        }
        else if (TextUtils.isEmpty(UserName))
        {
            //Toast.makeText(this, "Please write Username...", Toast.LENGTH_SHORT).show();
            showError(InputUsername,"Please Enter Username");
            return false;
        }
        else if (TextUtils.isEmpty(Email))
        {
            //Toast.makeText(this, "Please write Owner Email...", Toast.LENGTH_SHORT).show();
            showError(InputEmail,"Please write Owner Email");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            //Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            showError(InputEmail,"Invalid Email");
            return false;
        }
        else if (TextUtils.isEmpty(PhoneNo))
        {
            //Toast.makeText(this, "Please write Phone Number...", Toast.LENGTH_SHORT).show();
            showError(InputPhone,"Please write Phone Number");
            return false;
        }
        else if (TextUtils.isEmpty(Address))
        {
            //Toast.makeText(this, "Please write Shop Address...", Toast.LENGTH_SHORT).show();
            showError(InputAddress,"Please write Shop Address");
            return false;
        }
        else if (TextUtils.isEmpty(Password))
        {
            //Toast.makeText(this, "Please Enter Password...", Toast.LENGTH_SHORT).show();
            showError(InputPass,"Please Enter Password");
            return false;

        }
        else if(Password.length() <6){
            //Toast.makeText(this, "Password must contains 9 character...", Toast.LENGTH_SHORT).show();
            showError(InputPass,"Password min 6 character");
            InputPass.setTransformationMethod(null);
            return false;
        }
        else if (TextUtils.isEmpty(ConPass))
        {
            //Toast.makeText(this, "Please Enter Same Password ...", Toast.LENGTH_SHORT).show();
            showError(InputConPass,"Please Enter Same Password");
            return false;
        }
        else if (!Password.equals(ConPass))
        {
            //Toast.makeText(this, "Password not matching", Toast.LENGTH_SHORT).show();
            showError(InputConPass,"Password not matching");
            InputPass.setTransformationMethod(null);
            InputConPass.setTransformationMethod(null);
            return false;
        }
        else
        {
            AllowAccessToAccount(ShopName, UserName, Email, PhoneNo, Address, ConPass);
        }
        return false;
    }

    private void showError(EditText input, String s){
        input.setError(s);
        input.requestFocus();
    }

    public void AllowAccessToAccount(final String ShopName,
                                     final String UserName,
                                     final String Email,
                                     final String PhoneNo,
                                     final String Address,
                                     final String Pass){
        final DatabaseReference reff;
        reff = FirebaseDatabase.getInstance().getReference();
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Seller").child(UserName).exists())) {
                    HashMap<String, Object> sellerData = new HashMap<>();

                    sellerData.put("ShopName", ShopName);
                    sellerData.put("Username", UserName);
                    sellerData.put("Email", Email);
                    sellerData.put("PhoneNo", PhoneNo);
                    sellerData.put("Address", Address);
                    sellerData.put("Pass", Pass);
                    //reff.push().setValue(sellerData);
                    reff.child("Seller").child(UserName).child("Seller Details").updateChildren(sellerData).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(OwnerSignUp.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(OwnerSignUp.this, OwnerLogin.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(OwnerSignUp.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(OwnerSignUp.this, "Username already exists!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


            public void openOwnerAlreadySignUp() {
                Intent intent = new Intent(this, OwnerLogin.class);
                startActivity(intent);
            }
    }