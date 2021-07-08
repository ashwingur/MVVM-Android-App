package com.example.mvvmandroidapp.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmandroidapp.model.Animal;

import java.util.ArrayList;
import java.util.List;

/*
 * Singleton pattern
 * (So we wouldnt have a bunch of open connections to a webservice, api, caches etc)
 */
public class AnimalRepository {

    private static AnimalRepository instance;
    private ArrayList<Animal> dataSet = new ArrayList<>();

    public static AnimalRepository getInstance(){
        if (instance == null){
            instance = new AnimalRepository();
        }
        return instance;
    }

    // Pretend to get data from a web service or online source
    public MutableLiveData<List<Animal>> getAnimals(){
        setAnimals();

        MutableLiveData<List<Animal>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setAnimals(){
        dataSet.add(new Animal("Tiger", "https://i.natgeofe.com/n/6490d605-b11a-4919-963e-f1e6f3c0d4b6/sumatran-tiger-thumbnail-nationalgeographic_1456276.jpg"));
        dataSet.add(new Animal("Dog","https://i.ytimg.com/vi/MPV2METPeJU/maxresdefault.jpg"));
        dataSet.add(new Animal("Cat","https://cdn.britannica.com/22/206222-131-E921E1FB/Domestic-feline-tabby-cat.jpg"));
        dataSet.add(new Animal("Elephant","https://c402277.ssl.cf1.rackcdn.com/photos/14206/images/hero_small/WW187785.jpg?1576774644"));
        dataSet.add(new Animal("Giraffe","https://www.traveller.com.au/content/dam/images/h/1/p/q/1/k/image.related.articleLeadwide.620x349.h1pq27.png/1596176460724.jpg"));
        dataSet.add(new Animal("Zebra","https://cdn.mos.cms.futurecdn.net/HjFE8NKWuCmgfHCcndJ3rK-1200-80.jpg"));
        dataSet.add(new Animal("Fish","https://www.visitsealife.com/sydney/media/yfsfsugn/clownfish.jpg"));
    }
}
