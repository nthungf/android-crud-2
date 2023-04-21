package com.example.crud2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.crud2.R;
import com.example.crud2.model.Book;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.HomeViewHolder> {

    private List<Book> list;

    private ItemListener itemListener;

    public RecyclerViewAdapter() {
        this.list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<Book> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Book Book(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Book item = list.get(position);
        holder.tvTen.setText(item.getTen());
        holder.tvTacGia.setText(item.getTacGia());
        holder.tvPhamVi.setText(item.getPhamVi());
        holder.tvDanhGia.setText(item.getDanhGia() + "/5");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Book getBook(int position) {
        return list.get(position);
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTen, tvTacGia, tvPhamVi, tvDanhGia;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            tvTen = view.findViewById(R.id.tvTen);
            tvTacGia = view.findViewById(R.id.tvTacGia);
            tvPhamVi = view.findViewById(R.id.tvPhamVi);
            tvDanhGia = view.findViewById(R.id.tvDanhGia);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemListener != null) {
                itemListener.ItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface ItemListener {
        void ItemClick(View view, int position);
    }
}
