package com.example.crud2.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.example.crud2.R;
import com.example.crud2.adapter.RecyclerViewAdapter;
import com.example.crud2.dal.SQLiteHelper;
import com.example.crud2.model.Book;

import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener {

    private SearchView searchView;
    private Button btSearch, btStat;
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    private RecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        adapter = new RecyclerViewAdapter();
        db = new SQLiteHelper(getContext());
        List<Book> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Book> list1 = db.searchByKey(newText);
                adapter.setList(list1);
                return true;
            }
        });
        btSearch.setOnClickListener(this);
        btStat.setOnClickListener(this);
    }

    private void initViews(View v) {
        recyclerView = v.findViewById(R.id.recyclerView);
        btSearch = v.findViewById(R.id.btSearch);
        btStat = v.findViewById(R.id.btStat);
        searchView = v.findViewById(R.id.search);


    }

    @Override
    public void onClick(View v) {
        if (v == btStat) {
            List<Book> list1 = db.getStat();
            adapter.setList(list1);
        }
    }
}