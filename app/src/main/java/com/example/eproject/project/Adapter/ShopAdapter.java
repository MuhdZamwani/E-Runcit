//package com.example.eproject.project.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.eproject.R;
//import com.example.eproject.project.user.Products;
//
//import java.util.ArrayList;
//
//public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>{
//
//    Context context;
//    ArrayList<Products> productsList;
//
//    public ShopAdapter(Context context, ArrayList<Products> productsList) {
//        this.context = context;
//        this.productsList = productsList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_shop_product, parent, false);
//
//        return new ViewHolder(inflate);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        Products pro = productsList.get(position);
//        holder.productname.setText(pro.getProductName());
//        holder.productprice.setText(pro.getProductPrice());
//        holder.productdesc.setText(pro.getProductDesc());
//
//        Glide.with(holder.productpic.getContext()).load(pro.getImageUrl()).into(holder.productpic);
//    }
//
//    @Override
//    public int getItemCount() {
//        return productsList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView productpic;
//        TextView productname, productprice, productdesc;
//        Button btnDelete, btnEdit;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            productname = itemView.findViewById(R.id.disnamepro);
//            productprice = itemView.findViewById(R.id.dispripro);
//            productdesc = itemView.findViewById(R.id.itemproductdescription);
//            productpic = itemView.findViewById(R.id.dispicpro);
//
//            btnDelete = itemView.findViewById(R.id.removeproduct);
//            btnEdit = itemView.findViewById(R.id.editProduct);
//        }
//    }
//}
