package com.example.movieapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.movieapp.Adapters.MainAdapter;
import com.example.movieapp.Models.MainModels;
import com.example.movieapp.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainAdapter listAdapter;
    ArrayList<MainModels>  list;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();
        list.add(new MainModels(R.drawable.burger, "Burger", "5", "Chicken Burger with Extra cheese"));
        list.add(new MainModels(R.drawable.pizza, "Pizza", "10", "The offer to download the coupons ends Thursday May 28"));
        list.add(new MainModels(R.drawable.por, "Portobello Mushroom", "12", "Meaty portobello mushrooms make for the perfect vegetarian burger!"));
        list.add(new MainModels(R.drawable.pizza_burger, "Pizza Burger", "10", "Burger 0'clock is available to satiate to hunger"));
        list.add(new MainModels(R.drawable.burger, "Burger", "5", "Chicken Burger with Extra cheese"));

        listAdapter = new MainAdapter(this, list);
        binding.recyclerView.setAdapter(listAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.orders:
                startActivity(new Intent(this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}