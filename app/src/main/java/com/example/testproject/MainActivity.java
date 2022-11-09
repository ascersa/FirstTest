package com.example.testproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.testproject.databinding.ActivityMainBinding;
import com.example.testproject.java.JavaActivity;
import com.example.testproject.viewmodel.BindDataActivity;
import com.example.testproject.viewmodel.TestViewModel;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvOne.setOnClickListener(v -> {
//            startActivity(new Intent(this, BindDataActivity.class));
            startActivity(new Intent(this, JavaActivity.class));
        });

        binding.calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            testReference();
        });

//        Log.d("xjf", "MainActivity: " + Glide.with(this));
//        Log.d("xjf", "MainActivity: " + getSupportFragmentManager());

//        Log.d("xjf", "MainActivity: " + Glide.get(this).getRequestManagerRetriever());

    }

    private void initView() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, InstantiationException {

        Class<?> c = Class.forName("android.widget.TextView");

        Constructor<?> constructor = c.getConstructor(Context.class);
        Log.d("xjf", "initView: getConstructor");

        Object object = constructor.newInstance(MainActivity.this);
        Log.d("xjf", "initView: newInstance");

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Method method1 = c.getMethod("setLayoutParams", ViewGroup.LayoutParams.class);
        method1.invoke(object, layoutParams);
        Log.d("xjf", "initView: getMethod");

        Method method2 = c.getMethod("setText", CharSequence.class);
        method2.invoke(object, "R.string.app_name");
        Log.d("xjf", "initView: getMethod2");

//        binding.getRoot().addView((View) object);
        Log.d("xjf", "initView: addView");

    }

    private void testReference() {
        // 阶段 1：
        // 创建对象
        String strongRef = new String("abc");
        // 1、创建引用队列
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        // 2、创建引用对象，并关联引用队列
        WeakReference<String> weakRef = new WeakReference<>(strongRef, referenceQueue);
        Log.d("xjf", "run weakRef: " + weakRef);
        // 3、断开强引用
        strongRef = null;

        System.gc();

        // 阶段 2：
        // 延时 5000 是为了提高 "abc" 被回收的概率
        binding.tvOne.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println(weakRef.get()); // 输出 null
                // 观察引用队列
                Reference<? extends String> ref = referenceQueue.poll();
                if (null != ref) {
                    Log.d("xjf", "run ref: " + ref);
                    // 虽然可以获取到 Reference 对象，但无法获取到引用原本指向的对象
                    System.out.println(ref.get()); // 输出 null
                }
            }
        }, 5000);
    }
}