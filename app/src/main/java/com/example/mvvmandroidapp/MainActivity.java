package com.example.mvvmandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.mvvmandroidapp.adapters.RecyclerViewAdapter;
import com.example.mvvmandroidapp.model.Animal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<Animal> mAnimals;

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
        mAnimals = new ArrayList<>();

        mAnimals.add(new Animal("Tiger", "https://i.natgeofe.com/n/6490d605-b11a-4919-963e-f1e6f3c0d4b6/sumatran-tiger-thumbnail-nationalgeographic_1456276.jpg"));
        mAnimals.add(new Animal("Dog","https://i.ytimg.com/vi/MPV2METPeJU/maxresdefault.jpg"));
        mAnimals.add(new Animal("Cat","https://cdn.britannica.com/22/206222-131-E921E1FB/Domestic-feline-tabby-cat.jpg"));
        mAnimals.add(new Animal("Elephant","https://c402277.ssl.cf1.rackcdn.com/photos/14206/images/hero_small/WW187785.jpg?1576774644"));
        mAnimals.add(new Animal("Giraffe","https://www.traveller.com.au/content/dam/images/h/1/p/q/1/k/image.related.articleLeadwide.620x349.h1pq27.png/1596176460724.jpg"));
        mAnimals.add(new Animal("Zebra","https://cdn.mos.cms.futurecdn.net/HjFE8NKWuCmgfHCcndJ3rK-1200-80.jpg"));
        mAnimals.add(new Animal("Fish","https://www.visitsealife.com/sydney/media/yfsfsugn/clownfish.jpg"));

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: Initialising");
        mRecylerView = findViewById(R.id.recycler_view);
        mRecylerAdapter = new RecyclerViewAdapter(this, mAnimals);
        mRecylerView.setAdapter(mRecylerAdapter);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
    }

    
}