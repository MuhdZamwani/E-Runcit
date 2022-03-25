package com.example.eproject.project.ownerinterface;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eproject.R;
import com.example.eproject.project.GlobalOnline;
import com.example.eproject.project.user.Seller;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;

public class AddNewProduct extends AppCompatActivity {

    private TextInputEditText InputProductName, InputProductPrice, InputProductDesc;
    private ImageView InputProductImage;
    private String selectedProduct;
    private Button productnew;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private String parentDbName = "Seller";

    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String downloadImageUrl;

    private Seller sel;
    final String randomKey = UUID.randomUUID().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line Hide Actionbar

        setContentView(R.layout.activity_add_new_product);

        InputProductName = (TextInputEditText)findViewById(R.id.addProductName);
        InputProductPrice = (TextInputEditText)findViewById(R.id.addProductPrice);
        InputProductDesc = (TextInputEditText)findViewById(R.id.addDescProduct);
        InputProductImage =(ImageView)findViewById(R.id.addnewitemproduct);

        //DropDown Coding
        spinner = (Spinner) findViewById(R.id.AddCategoryDD);
        adapter = ArrayAdapter.createFromResource(this, R.array.AddProductDD, R.layout.spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedProduct = spinner.getSelectedItem().toString();

//                if (selectedProduct.equals("Category")){
//                    Toast.makeText(AddNewProduct.this, "Please Select The Category", Toast.LENGTH_SHORT).show();
//
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //OPEN GALLERY
        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        //INTENT
        productnew = (Button) findViewById(R.id.addnewproductbtn);
        productnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openOwnerAddProductInterface();

                String ProductName = InputProductName.getText().toString();
                String ProductPrice = InputProductPrice.getText().toString();
                String ProductDesc = InputProductDesc.getText().toString();
                String selectedProduct = spinner.getSelectedItem().toString();

                if(ImageUri == null)
                {
                    Toast.makeText(AddNewProduct.this, "Please Select Product Image", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(ProductName))
                {
                    //Toast.makeText(this, "Please write Shop Name", Toast.LENGTH_SHORT).show();
                    showError(InputProductName,"Product Name Required");
                }
                else if (TextUtils.isEmpty(ProductPrice))
                {
                    //Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    showError(InputProductPrice,"Product Price Required");
                }
                else if(selectedProduct.equals("Category"))
                {
                    Toast.makeText(AddNewProduct.this, "Please Select The Category", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    addNewProduct(ProductName, ProductPrice, ProductDesc, selectedProduct);
                }
            }

            private void showError(EditText input, String s){
                input.setError(s);
                input.requestFocus();
            }

            private void addNewProduct(final String productName,
                                       final String productPrice,
                                       final String productDesc,
                                       final String selectedProduct) {
                final DatabaseReference Rootref2;

                Rootref2 = FirebaseDatabase.getInstance().getReference();
                Rootref2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String Shop = snapshot.child("Seller").child(GlobalOnline.currentOnline.getUsername()).child("Seller Details").child("ShopName").getValue().toString();
                        String add = snapshot.child("Seller").child(GlobalOnline.currentOnline.getUsername()).child("Seller Details").child("Address").getValue().toString();
                        String no = snapshot.child("Seller").child(GlobalOnline.currentOnline.getUsername()).child("Seller Details").child("PhoneNo").getValue().toString();

                        HashMap<String, Object> ProData = new HashMap<>();
                        ProData.put("ProductName",productName);
                        ProData.put("ProductPrice",productPrice);
                        ProData.put("ProductDesc",productDesc);
                        ProData.put("ImageUrl",downloadImageUrl);
                        ProData.put("Category", selectedProduct);
                        ProData.put("By", GlobalOnline.currentOnline.getUsername());
                        ProData.put("ShopName",Shop);
                        ProData.put("ShopAdd",add);
                        ProData.put("OwnerPhoneNo",no);
                        Rootref2.child("Products").child(randomKey).updateChildren(ProData).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(AddNewProduct.this, "Product is added successfully..", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddNewProduct.this, OwnerShopInterface.class);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode == GalleryPick && resultCode ==RESULT_OK && data!=null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {

        final String randomKey = UUID.randomUUID().toString();

        final StorageReference riversRef = storageReference.child("images/" + randomKey);
        final UploadTask uploadTask = riversRef.putFile(ImageUri);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(AddNewProduct.this, "Image Uploaded", Toast.LENGTH_LONG).show();
                        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful())
                            {
                                throw task.getException();
                            }
                                downloadImageUrl = riversRef.getDownloadUrl().toString();
                                return riversRef.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if(task.isSuccessful())
                                {
                                    downloadImageUrl = task.getResult().toString();

                                    Toast.makeText(AddNewProduct.this, "", Toast.LENGTH_SHORT).show();                                }
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(AddNewProduct.this, "Failed To Upload", Toast.LENGTH_LONG).show();
                    }
                });
    }
}



