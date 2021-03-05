package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.movieapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new DBHelper(this);

        if (getIntent().getIntExtra("type", 0) == 1) {
            final int image = getIntent().getIntExtra("image", 0);
            String nameFood = getIntent().getStringExtra("name");
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            String desc = getIntent().getStringExtra("desc");

            // Set data for activity detail
            binding.detailImage.setImageResource(image);
            binding.detailName.setText(nameFood);
            binding.detailPrice.setText(String.format("%d", price));
            binding.detailDescription.setText(desc);

            // Insert data
            binding.insertBtn.setOnClickListener(v -> {
                boolean isInserted = db.insertOrder(binding.nameBox.getText().toString(),
                        binding.phoneBox.getText().toString(),
                        price,
                        image,
                        Integer.parseInt(binding.quantity.getText().toString()),
                        desc,
                        nameFood
                );

                if (isInserted) {
                    Toast.makeText(this, "Data Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = db.getOrdersById(id);

            binding.detailImage.setImageResource(cursor.getInt(4));
            binding.detailName.setText(cursor.getString(7));
            binding.detailPrice.setText(String.format("%d", cursor.getInt(3)));
            binding.detailDescription.setText(cursor.getString(6));
            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertBtn.setText("UPDATE");
            binding.insertBtn.setOnClickListener(v -> {
                boolean isUpdated = db.updateOrder(binding.nameBox.getText().toString(),
                        binding.phoneBox.getText().toString(),
                        Integer.parseInt(binding.detailPrice.getText().toString()),
                        cursor.getInt(4),
                        1,
                        binding.detailDescription.getText().toString(),
                        binding.detailName.getText().toString(),
                        id);
                if (isUpdated) {
                    Toast.makeText(this, "Update Successfully !!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Update Failed !!!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}