package com.example.testproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testproject.databinding.ActivityMainBinding;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int scrollPosition = 0;
    private int itemHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.v.setOnClickListener(v -> Toast.makeText(this, "xxx", Toast.LENGTH_LONG));

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleView.setAdapter(new TestAdapter());
        binding.recycleView.postDelayed(() -> {
            itemHeight = binding.recycleView.getHeight();
            loopScroll();
        }, 3000);

        binding.tvOne.setOnClickListener(v -> {

        });

        binding.calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {

        });

    }

    private void loopScroll() {
        scrollPosition++;
        if (scrollPosition > 9) {
            scrollPosition = 0;
            binding.recycleView.smoothScrollToPosition(0);
        } else {
            binding.recycleView.smoothScrollBy(0, itemHeight, null, 2000);
        }
        binding.recycleView.postDelayed(this::loopScroll, 3000);
    }

    private void initView() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, InstantiationException {

        Class<?> c = Class.forName("android.widget.TextView");

        Constructor<?> constructor = c.getConstructor(Context.class);
        Object object = constructor.newInstance(MainActivity.this);
        Log.d("xjf", "initView: getConstructor");

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Method method1 = c.getMethod("setLayoutParams", ViewGroup.LayoutParams.class);
        method1.invoke(object, layoutParams);
        Log.d("xjf", "initView: getMethod");


        Method method2 = c.getMethod("setText", CharSequence.class);
        method2.invoke(object, "R.string.app_name");
        Log.d("xjf", "initView: getMethod2");

        binding.getRoot().addView((View) object);
        Log.d("xjf", "initView: newInstance");

//        for (int i = 0; i < c.getDeclaredMethods().length; i++) {
//            Log.d("xjf", "initView: " + c.getDeclaredMethods()[i]);
//        }
    }

    public static class TestHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public TestHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv);
        }
    }

    public static class TestAdapter extends RecyclerView.Adapter<TestHolder> {

        @NonNull
        @Override
        public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TestHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv, parent, false));
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull TestHolder holder, int position) {
            holder.textView.setText(position + " ");
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }
}