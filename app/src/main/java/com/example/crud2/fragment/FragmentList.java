package com.example.crud2.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.crud2.EditActivity;
import com.example.crud2.R;
import com.example.crud2.adapter.RecyclerViewAdapter;
import com.example.crud2.dal.SQLiteHelper;
import com.example.crud2.model.Book;

import java.util.List;

public class FragmentList extends Fragment implements RecyclerViewAdapter.ItemListener {

    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter();
        db = new SQLiteHelper(getContext());
        List<Book> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void ItemClick(View view, int position) {
        Book book = adapter.getBook(position);
        System.out.println(book.getTen());
        Intent intent = new Intent(getActivity(), EditActivity.class);
        intent.putExtra("book", book);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Book> list = db.getAll();
        adapter.setList(list);
    }
}