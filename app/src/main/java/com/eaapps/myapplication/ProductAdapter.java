package com.eaapps.myapplication;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eaapps.myapplication.databinding.ItemProductsBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    List<Products> listProducts;
    List<Products> listTemp;

    public ProductAdapter(List<Products> listProducts) {
        this.listProducts = new ArrayList<>(listProducts);
        this.listTemp = new ArrayList<>(listProducts);
    }

    void collapse() {
        if (listProducts != null && listTemp != null && listProducts.size() > 2) {
             listProducts.removeAll(listTemp.subList(2, listTemp.size()));
        }
    }

    void extendable() {
        if (listTemp.size() > 2)
         listProducts.addAll(listTemp.subList(2, listTemp.size()));
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(ItemProductsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private final ItemProductsBinding productsBinding;

        public ProductViewHolder(@NonNull ItemProductsBinding itemProductsBinding) {
            super(itemProductsBinding.getRoot());
            this.productsBinding = itemProductsBinding;
        }

        public void bind(int position) {
            productsBinding.setProducts(listProducts.get(position));
        }

    }
}
