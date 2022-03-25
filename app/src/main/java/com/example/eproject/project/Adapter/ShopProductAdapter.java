package com.example.eproject.project.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.eproject.R;
import com.example.eproject.project.user.Products;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.HashMap;
import java.util.Map;

public class ShopProductAdapter extends FirebaseRecyclerAdapter<Products,ShopProductAdapter.ViewHolder> {

    public ShopProductAdapter(FirebaseRecyclerOptions<Products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Products products) {
        holder.productname.setText(products.getProductName());
        holder.productprice.setText(products.getProductPrice());
        holder.productdesc.setText(products.getProductDesc());

        Glide.with(holder.productpic.getContext()).load(products.getImageUrl()).into(holder.productpic);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.productpic.getContext())
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.update_popout))
                        .setExpanded(true,2150)
                        .create();

                View view = dialogPlus.getHolderView();
                ArrayAdapter<CharSequence> adapter;

                ImageView EditPicPro = view.findViewById(R.id.edititemproduct);
                EditText EditNamePro = view.findViewById(R.id.editProductName);
                EditText EditPricePro = view.findViewById(R.id.editProductPrice);
                EditText EditDescPro = view.findViewById(R.id.editProductDescription);
                Spinner EditSelPro = view.findViewById(R.id.editCategoryDD);

                Button EditPro = view.findViewById(R.id.editproductbtn);

                adapter = ArrayAdapter.createFromResource( holder.productprice.getContext(),R.array.AddProductDD, R.layout.spinner_layout);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                EditSelPro.setAdapter(adapter);

                Glide.with(EditPicPro.getContext()).load(products.getImageUrl()).into(EditPicPro);
                EditNamePro.setText(products.getProductName());
                EditPricePro.setText(products.getProductPrice());
                EditDescPro.setText(products.getProductDesc());

                EditSelPro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                EditPro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selPro = EditSelPro.getSelectedItem().toString();

                        if(selPro.equals("Category"))
                        {
                            Toast.makeText(holder.productprice.getContext(), "Please Select The Category", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Map<String, Object> EditData = new HashMap<>();
                            EditData.put("ProductName",EditNamePro.getText().toString());
                            EditData.put("ProductPrice",EditPricePro.getText().toString());
                            EditData.put("ProductDesc",EditDescPro.getText().toString());
                            EditData.put("Category", selPro);

                            FirebaseDatabase.getInstance().getReference().child("Products")
                                    .child(getRef(position).getKey()).updateChildren(EditData)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(holder.productprice.getContext(), "Edit product Successful", Toast.LENGTH_SHORT).show();
                                            dialogPlus.dismiss();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(holder.productprice.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
                dialogPlus.show();
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.productname.getContext());
                builder.setTitle("Are You Sure?");
                builder.setMessage("Deleted data can't be Undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Products")
                                .child(getRef(position).getKey()).removeValue();

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
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_shop_product, parent, false);

        return new ViewHolder(inflate);
    }

    @NonNull
    @Override
    public Products getItem(int position) {
        return super.getItem(position);
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView productpic;
        TextView productname, productprice, productdesc;
        Button btnDelete, btnEdit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productname = itemView.findViewById(R.id.disnamepro);
            productprice = itemView.findViewById(R.id.dispripro);
            productdesc = itemView.findViewById(R.id.itemproductdescription);
            productpic = itemView.findViewById(R.id.dispicpro);

            btnDelete = itemView.findViewById(R.id.removeproduct);
            btnEdit = itemView.findViewById(R.id.editProduct);
        }
    }

}