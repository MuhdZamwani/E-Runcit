package com.example.eproject.project.ownerinterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eproject.R;
import com.example.eproject.project.GlobalOnline;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OwnerProfileInterface extends AppCompatActivity {

    private TextView DisName, DisEmail, DisShopName, DisPhoneNo, DisAd;
    private ImageButton ownerhome, ownermanual, ownerorder, ownershop;
    private ImageView EditProfile;

    private DatabaseReference UserName, Eemail, SshopName, PphoneNo, Aadd, seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_owner_profile_interface);

//        updateProf = findViewById(R.id.ownerprofedit);
//
//        updateProf.setOnClickListener(new View.OnClickListener(){
//
//            public void onClick(View v){
//                BottomSheetDialog bottomDialog = new BottomSheetDialog(OwnerProfileInterface.this);
//                bottomDialog.setContentView(R.layout.profile_update_popout);
//                bottomDialog.setCanceledOnTouchOutside(false);
//            }
//
//        });

        UserName = FirebaseDatabase.getInstance().getReference().child("Seller").child(GlobalOnline.currentOnline.getUsername()).child("Seller Details").child("Username");

        DisName = findViewById(R.id.textView12);
        UserName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sn =snapshot.getValue(String.class);
                DisName.setText(sn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Eemail = FirebaseDatabase.getInstance().getReference().child("Seller").child(GlobalOnline.currentOnline.getUsername()).child("Seller Details").child("Email");

        DisEmail = findViewById(R.id.textView10);
        Eemail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sn =snapshot.getValue(String.class);
                DisEmail.setText(sn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SshopName = FirebaseDatabase.getInstance().getReference().child("Seller").child(GlobalOnline.currentOnline.getUsername()).child("Seller Details").child("ShopName");

        DisShopName = findViewById(R.id.textView4);
        SshopName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sn =snapshot.getValue(String.class);
                DisShopName.setText(sn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        PphoneNo = FirebaseDatabase.getInstance().getReference().child("Seller").child(GlobalOnline.currentOnline.getUsername()).child("Seller Details").child("PhoneNo");

        DisPhoneNo = findViewById(R.id.textView6);
        PphoneNo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sn =snapshot.getValue(String.class);
                DisPhoneNo.setText(sn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Aadd = FirebaseDatabase.getInstance().getReference().child("Seller").child(GlobalOnline.currentOnline.getUsername()).child("Seller Details").child("Address");

        DisAd = findViewById(R.id.textView8);
        Aadd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sn =snapshot.getValue(String.class);
                DisAd.setText(sn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        myDialog = new Dialog(this);

//        EditProfile = findViewById(R.id.ownerprofedit);
//        EditProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final DialogPlus dialogPlus = DialogPlus.newDialog(OwnerProfileInterface.this)
//                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.profile_update_popout))
//                        .setExpanded(true,2550)
//                        .create();
//
//                EditText tUsername = findViewById(R.id.editUsername);
//                EditText tEmail = findViewById(R.id.editEmail);
//                EditText tShopname = findViewById(R.id.editShopName);
//                EditText tPhoneno = findViewById(R.id.editMobileNo);
//                EditText tAddress = findViewById(R.id.editAddress);
//                EditText tPass = findViewById(R.id.editPassword);
//                EditText tConpass = findViewById(R.id.editConfirmPassword);
//
//                Button btnUp = findViewById(R.id.updateProfBtn);
//
//                seller = FirebaseDatabase.getInstance().getReference().child("Seller").child(GlobalOnline.username).child("Seller Details");
//                seller.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        String u = snapshot.child("Seller").child(GlobalOnline.username).child("Seller Details").child("Username").getValue(String.class);
//                        tUsername.setText(u);
//
////                        String username = tUsername.getText().toString();
////                        String email = tEmail.getText().toString();
////                        String shopname = tShopname.getText().toString();
////                        String phoneno = tPhoneno.getText().toString();
////                        String address = tAddress.getText().toString();
////                        String pass = tPass.getText().toString();
////                        String conpass = tConpass.getText().toString();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

//                btnUp.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String username = tUsername.getText().toString();
//                        String email = tEmail.getText().toString();
//                        String shopname = tShopname.getText().toString();
//                        String phoneno = tPhoneno.getText().toString();
//                        String address = tAddress.getText().toString();
//                        String pass = tPass.getText().toString();
//                        String conpass = tConpass.getText().toString();
//
//
//                    }
//                });
//                dialogPlus.show();
//            }
//        });

        ownerhome = findViewById(R.id.homeBtn1);
        ownerhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerHomeInterface();
            }
        });

        ownermanual = (ImageButton) findViewById(R.id.manualBtn1);
        ownermanual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerManualeInterface();
            }
        });
//
        ownerorder = (ImageButton) findViewById(R.id.OrderBtn1);
        ownerorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerOrderInterface();
            }
        });
//
        ownershop = (ImageButton) findViewById(R.id.ShopBtn1);
        ownershop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOwnerShopInterface();
            }
        });

    }

    //Pop Out On Click
//    public void ShowPopupUsername(View v){
//        ImageView OwnerEditUsername;
//        EditText EditUserName;
//        Button btnEditUserName;
//
//        myDialog.setContentView(R.layout.username_popout);
//        OwnerEditUsername = myDialog.findViewById(R.id.ownerprofUsernameedit);
//
//        EditUserName = findViewById(R.id.editUsernamePopout);
//        myDialog.show();
//
//        btnEditUserName = findViewById(R.id.editUsernamePopoutBtn);
//        btnEditUserName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String str = EditUserName.getText().toString();
//                editUsername(str);

//                if (str != null){
//                    Map<String, Object> EditData = new HashMap<>();
//                    EditData.put("UserName", str);
//                    FirebaseDatabase.getInstance().getReference().child("Seller")
//                            .child(GlobalOnline.username)
//                            .child("Seller Details")
//                            .child("UserName").updateChildren(EditData)
//                    .addOnSuccessListener(new OnSuccessListener() {
//                        @Override
//                        public void onSuccess(Object o) {
//                            Toast.makeText(OwnerProfileInterface.this, "Edit Username Successful", Toast.LENGTH_SHORT).show();
//                            myDialog.dismiss();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(OwnerProfileInterface.this, "Edit Username Error", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }
//        });
//}

//    private void editUsername(String str) {

//        if (str != null){
//            Map<String, Object> EditData = new HashMap<>();
//            EditData.put("UserName", str);
//            FirebaseDatabase.getInstance().getReference().child("Seller")
//                    .child(GlobalOnline.username)
//                    .child("Seller Details")
//                    .child("UserName").updateChildren(EditData)
//                    .addOnSuccessListener(new OnSuccessListener() {
//                        @Override
//                        public void onSuccess(Object o) {
//                            Toast.makeText(OwnerProfileInterface.this, "Edit Username Successful", Toast.LENGTH_SHORT).show();
//                            myDialog.dismiss();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(OwnerProfileInterface.this, "Edit Username Error", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//    }

//    public void ShowPopupEmail(View v){
//        ImageView OwnerEditEmail;
//        EditText EditEmail;
//        Button btnEditEmail;
//
//        myDialog.setContentView(R.layout.email_popout);
//        OwnerEditEmail = myDialog.findViewById(R.id.ownerprofemailedit);
//        EditEmail = findViewById(R.id.editEmailPopout);
//
//        btnEditEmail = findViewById(R.id.editEmailPopoutBtn);
//        btnEditEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str = EditEmail.getText().toString();
//                editEmail(str);
//            }
//        });
//        myDialog.show();
//
//    }
//
//    private void editEmail(String str) {
//        if (str != null){
//            if (Patterns.EMAIL_ADDRESS.matcher(str).matches()){
//                Map<String, Object> EditData = new HashMap<>();
//                EditData.put("Email", str);
//                FirebaseDatabase.getInstance().getReference().child("Seller")
//                        .child(GlobalOnline.username)
//                        .child("Seller Details")
//                        .child("Email").updateChildren(EditData)
//                        .addOnSuccessListener(new OnSuccessListener() {
//                            @Override
//                            public void onSuccess(Object o) {
//                                Toast.makeText(OwnerProfileInterface.this, "Edit Email Successful", Toast.LENGTH_SHORT).show();
//                                myDialog.dismiss();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(OwnerProfileInterface.this, "Edit Email Error", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }else{
//                Toast.makeText(OwnerProfileInterface.this, "Invalid Email Format", Toast.LENGTH_SHORT).show();
//            }
//        }
//        else if (str == null){
//            Toast.makeText(OwnerProfileInterface.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void ShowPopupProfile(View v){
//        ImageView OwnerEditProfile;
//
//        myDialog.setContentView(R.layout.profile_update_popout);
//        OwnerEditProfile = (ImageView) myDialog.findViewById(R.id.ownerprofedit);
//        myDialog.show();
//
//    }
//
//    public void ShowPopupMobileNo(View v){
//        ImageView OwnerEditMobileNo;
//
//        myDialog.setContentView(R.layout.mobile_number_popout);
//        OwnerEditMobileNo = (ImageView) myDialog.findViewById(R.id.ownerprofnumberedit);
//        myDialog.show();
//
//    }
//
//    public void ShowPopupAddress(View v){
//        ImageView OwnerEditAddress;
//
//        myDialog.setContentView(R.layout.address_popout);
//        OwnerEditAddress = (ImageView) myDialog.findViewById(R.id.ownerprofaddressedit);
//        myDialog.show();
//
//    }
    //End

    public void openOwnerHomeInterface(){
        Intent intent = new Intent(this, MainActivityOwner.class);
        startActivity(intent);
    }

    public void openOwnerManualeInterface(){
        Intent intent = new Intent(this, OwnerManualInterface.class);
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