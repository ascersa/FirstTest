package com.example.testproject.java;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Synthetic;
import com.example.testproject.databinding.ActivityJavaBinding;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class JavaActivity extends AppCompatActivity {

    private final String TAG = "xjf";

    private ActivityJavaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int b;
        b = 'A';

        byte a;
        a = 0b1111110;

        Log.d("xjf", "onCreate: " + b + " " + a);

        Glide.with(this).load("http://120.77.247.226:8089/u/2256/10142256/202210/t/af8c520f59d84b1091545dd670e2c10d.jpg").into(binding.iv);

        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        Log.d("xjf", "onCreate: " + activityManager.getMemoryClass());


        LinkedEntry<String, String> entry = new LinkedEntry<>("key1");
        LinkedEntry<String, String> entry2 = new LinkedEntry<>("key2");
        LinkedEntry<String, String> entry3 = new LinkedEntry<>("key3");

        makeTail(entry);
        makeTail(entry2);
        makeTail(entry3);


        binding.tvCilck.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: " + binding.etTest.getText().toString().trim().length());
            Log.d(TAG, "onCreate: " + TextUtils.isEmpty(binding.etTest.getText().toString().trim()));
        });

    }

    private final LinkedEntry<String, String> head = new LinkedEntry<>("head");

    private void makeTail(LinkedEntry<String, String> entry) {
        removeEntry(entry);
        entry.prev = head.prev;
        entry.next = head;
        updateEntry(entry);
    }

    private static <K, V> void updateEntry(LinkedEntry<K, V> entry) {
        entry.next.prev = entry;
        entry.prev.next = entry;
    }

    private static <K, V> void removeEntry(LinkedEntry<K, V> entry) {
//        entry.prev.next = entry.next;
//        entry.next.prev = entry.prev;
    }

    static class LinkedEntry<K, V> {

        @Synthetic
        final K key;

        LinkedEntry<K, V> prev;
        LinkedEntry<K, V> next;

        LinkedEntry() {
            this(null);
        }

        LinkedEntry(K key) {
            next = prev = this;
            this.key = key;
        }

    }
}