package com.example.pdmparcial2.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.pdmparcial2.model.New;

import java.util.List;

public class NewViewModel extends AndroidViewModel{

    private NewsRepository repository;
    private LiveData<List<New>> news;

    public NewViewModel(Application application){
        super(application);
        repository = new NewsRepository(application);
        this.news = repository.getAllNews();
    }

    public LiveData<List<New>> getAllNews(){
        return news;
    }

    public void insertNew(New mNew){
        repository.insertNew(mNew);
    }

    public void refresh(){
        repository.refresh();
    }
}