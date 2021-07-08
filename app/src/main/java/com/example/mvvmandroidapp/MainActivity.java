package com.example.mvvmandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.mvvmandroidapp.adapters.RecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    // View Components
    private FloatingActionButton mFab;
    private RecyclerView mRecylerView;
    private RecyclerViewAdapter mRecylerAdapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");

        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: Preparing bitmaps");

        mImageUrls.add("https://i.natgeofe.com/n/6490d605-b11a-4919-963e-f1e6f3c0d4b6/sumatran-tiger-thumbnail-nationalgeographic_1456276.jpg");
        mNames.add("Tiger");

        mImageUrls.add("https://i.ytimg.com/vi/MPV2METPeJU/maxresdefault.jpg");
        mNames.add("Dog");

        mImageUrls.add("https://cdn.britannica.com/22/206222-131-E921E1FB/Domestic-feline-tabby-cat.jpg");
        mNames.add("Cat");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: Initialising");
        mRecylerView = findViewById(R.id.recycler_view);
        mRecylerAdapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        mRecylerView.setAdapter(mRecylerAdapter);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
    }

    
}