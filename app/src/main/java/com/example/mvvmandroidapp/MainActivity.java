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

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: Initialising");
        mRecylerAdapter = new RecyclerViewAdapter(this, mMainActivityViewModel.getAnimals().getValue());
        mRecylerView.setAdapter(mRecylerAdapter);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showProgressBar() { mProgressBar.setVisibility(View.VISIBLE);}
    private void hideProgressBar() { mProgressBar.setVisibility(View.GONE);}
}