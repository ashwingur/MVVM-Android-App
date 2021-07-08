package com.example.mvvmandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mvvmandroidapp.adapters.RecyclerViewAdapter;
import com.example.mvvmandroidapp.model.Animal;
import com.example.mvvmandroidapp.viewmodel.MainActivityViewModel;
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

    // View Model
    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");

        mRecylerView = findViewById(R.id.recycler_view);
        mFab = findViewById(R.id.fab);
        mProgressBar = findViewById(R.id.progress_bar);

        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.init();
        mMainActivityViewModel.getAnimals().observe(this, new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> animals) {
                mRecylerAdapter.notifyDataSetChanged();
            }
        });
        mMainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    showProgressBar();
                } else {
                    hideProgressBar();
                    mRecylerView.smoothScrollToPosition(mMainActivityViewModel.getAnimals().getValue().size() - 1);
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityViewModel.addNewValue(new Animal("Crab", "https://www.sydneyfishmarket.com.au/Portals/0/EasyDNNnews/1263/img-Mud-Crab.jpg"));
            }
        });

        initRecyclerView();
    }

//    private void initImageBitmaps(){
//        Log.d(TAG, "initImageBitmaps: Preparing bitmaps");
//        mAnimals = new ArrayList<>();
//
//        mAnimals.add(new Animal("Tiger", "https://i.natgeofe.com/n/6490d605-b11a-4919-963e-f1e6f3c0d4b6/sumatran-tiger-thumbnail-nationalgeographic_1456276.jpg"));
//        mAnimals.add(new Animal("Dog","https://i.ytimg.com/vi/MPV2METPeJU/maxresdefault.jpg"));
//        mAnimals.add(new Animal("Cat","https://cdn.britannica.com/22/206222-131-E921E1FB/Domestic-feline-tabby-cat.jpg"));
//        mAnimals.add(new Animal("Elephant","https://c402277.ssl.cf1.rackcdn.com/photos/14206/images/hero_small/WW187785.jpg?1576774644"));
//        mAnimals.add(new Animal("Giraffe","https://www.traveller.com.au/content/dam/images/h/1/p/q/1/k/image.related.articleLeadwide.620x349.h1pq27.png/1596176460724.jpg"));
//        mAnimals.add(new Animal("Zebra","https://cdn.mos.cms.futurecdn.net/HjFE8NKWuCmgfHCcndJ3rK-1200-80.jpg"));
//        mAnimals.add(new Animal("Fish","https://www.visitsealife.com/sydney/media/yfsfsugn/clownfish.jpg"));
//
//        initRecyclerView();
//    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: Initialising");
        mRecylerAdapter = new RecyclerViewAdapter(this, mMainActivityViewModel.getAnimals().getValue());
        mRecylerView.setAdapter(mRecylerAdapter);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showProgressBar() { mProgressBar.setVisibility(View.VISIBLE);}
    private void hideProgressBar() { mProgressBar.setVisibility(View.GONE);}
}