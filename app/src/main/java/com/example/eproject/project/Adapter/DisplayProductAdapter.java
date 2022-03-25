package com.example.eproject.project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eproject.R;
import com.example.eproject.project.user.Products;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class DisplayProductAdapter extends FirebaseRecyclerAdapter<Products,DisplayProductAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DisplayProductAdapter(@NonNull FirebaseRecyclerOptions<Products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Products products) {

        Glide.with(holder.DisPicPro.getContext()).load(products.getImageUrl()).into(holder.DisPicPro);

        holder.DisNamePro.setText(products.getProductName());
        holder.DisPriPro.setText(products.getProductPrice());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_display_product, parent, false);
        return new ViewHolder(inflate);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView DisPicPro;
        TextView DisNamePro, DisPriPro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DisPicPro = itemView.findViewById(R.id.dispicpro);
            DisNamePro = itemView.findViewById(R.id.disnamepro);
            DisPriPro = itemView.findViewById(R.id.dispripro);
        }
    }
}
