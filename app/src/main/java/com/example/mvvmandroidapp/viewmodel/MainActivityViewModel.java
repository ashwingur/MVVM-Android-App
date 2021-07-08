package com.example.mvvmandroidapp.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmandroidapp.model.Animal;
import com.example.mvvmandroidapp.repositories.AnimalRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Animal>> mAnimal;
    private AnimalRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if (mAnimal != null){
            return;
        }
        mRepo = AnimalRepository.getInstance();
        mAnimal = mRepo.getAnimals();
    }

    public void addNewValue(final Animal animal){
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void >(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<Animal> currentAnimals = mAnimal.getValue();
                currentAnimals.add(animal);
                mAnimal.postValue(currentAnimals);
                mIsUpdating.setValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<List<Animal>> getAnimals(){
        return mAnimal;
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
