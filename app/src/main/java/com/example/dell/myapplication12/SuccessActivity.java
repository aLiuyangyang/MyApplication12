package com.example.dell.myapplication12;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class SuccessActivity extends FragmentActivity {
     private ViewPager viewPager;
     private TabLayout tabLayout;
     private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        inif();
        viewPager.setAdapter(new MyTabBase(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        Intent intent=getIntent();
        name = intent.getStringExtra("name");
    }

    private void inif() {
        viewPager=findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.tab);
    }
    public String setName(){
        return name;
    }
}
