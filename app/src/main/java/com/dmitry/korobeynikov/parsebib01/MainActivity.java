package com.dmitry.korobeynikov.parsebib01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import name.ank.lab4.BibDatabase;
import name.ank.lab4.BibEntry;

public class MainActivity extends AppCompatActivity {

    List<BibEntry> bibs = new ArrayList<>();

    private EndlessScrollEventListener endlessScrollEventListener;

    private BibDatabase database;

    private int loaded = 0;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();

        RecyclerView recyclerView = findViewById(R.id.list);

        final MyAdapter[] adapter = {updateAdapter()};

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter[0]);
        recyclerView.setLayoutManager(layoutManager);


        endlessScrollEventListener = new EndlessScrollEventListener(layoutManager) {
            @Override
            public void onLoadMore(int pageNum, RecyclerView recyclerView) {
                init();

                adapter[0] = updateAdapter();
                recyclerView.setAdapter(adapter[0]);
            }
        };

        recyclerView.addOnScrollListener(endlessScrollEventListener);

    }

    private MyAdapter updateAdapter() {
        return new MyAdapter(this, bibs);
    }

    private void init() {
        try (InputStreamReader reader =
                     new InputStreamReader(getResources().openRawResource(R.raw.references));) {
            database = new BibDatabase(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int loadOneOff = 10;
        for (int i = loaded; i - loaded < loadOneOff && i < database.getSize(); i++) {
            bibs.add(database.getEntry(i));
        }
        loaded += loadOneOff;
    }
}

