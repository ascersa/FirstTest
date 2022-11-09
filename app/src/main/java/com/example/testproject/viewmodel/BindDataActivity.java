package com.example.testproject.viewmodel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Options;
import com.example.testproject.R;
import com.example.testproject.databinding.ActivityBindDataBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BindDataActivity extends AppCompatActivity {

    private ActivityBindDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBindDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.setTestviewmodel(new TestViewModel());

        List<String> modelList = new ArrayList<>();
        modelList.add("111");
        modelList.add("222");
        modelList.add("333");
        binding.setList(modelList);
        binding.setIndex(0);

        Map<String, String> mapModel = new HashMap<>();
        mapModel.put("aaa", "AAA");
        mapModel.put("bbb", "BBB");
        binding.setMap(mapModel);
        binding.setKey("bbb");


//        Log.d("xjf", "BindDataActivity: " + Glide.with(this));
//        Log.d("xjf", "BindDataActivity: " + getSupportFragmentManager());

//        Log.d("xjf", "BindDataActivity: " + Glide.get(this).getRequestManagerRetriever());


    }

}