package com.example.movieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.movieapp.Models.OrdersModels;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "mydatabase.db";
    final static int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table orders (id INTEGER primary key autoincrement," +
                " name TEXT," +
                " phone TEXT," +
                " price int," +
                " image int," +
                " quantity int," +
                " description TEXT," +
                " foodName TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists orders");
        onCreate(db);
    }

    public boolean insertOrder(String name, String phone, int price, int image, int quantity, String description, String foodName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        /*
        id = 0
        name = 1
        phone = 2
        price = 3
        image = 4
        quantity = 5
        desc = 6
        foodName = 7
         */
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("price", price);
        contentValues.put("image", image);
        contentValues.put("quantity", quantity);
        contentValues.put("description", description);
        contentValues.put("foodName", foodName);
        long result = db.insert("orders", null, contentValues);
        if(result == -1 ) {
            return false;
        } else {
            return true;
        }
    };

    public ArrayList<OrdersModels> getOrders() {
        ArrayList<OrdersModels> orders = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select id, foodName, image, price from orders", null);
        if(cursor.moveToFirst()) {
            while(cursor.moveToNext()) {
                OrdersModels model = new OrdersModels();
                model.setOrderNumber(cursor.getInt(0) + "");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3) + "");
                orders.add(model);
            }
        }
        cursor.close();
        db.close();
        return orders;
    };

    public Cursor getOrdersById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from orders where id =" + id, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    };

    public boolean updateOrder(String name, String phone, int price, int image, int quantity, String description, String foodName, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        /*
        id = 0
        name = 1
        phone = 2
        price = 3
        image = 4
        quantity = 5
        desc = 6
        foodName = 7
         */
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("price", price);
        contentValues.put("image", image);
        contentValues.put("quantity", quantity);
        contentValues.put("description", description);
        contentValues.put("foodName", foodName);
        long row = db.update("orders", contentValues, "id="+ id, null);
        if(row <=0 ) {
            return false;
        } else {
            return true;
        }
    };

    public int deleteOrder(String id) {
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete("orders", "id=" + id, null);
    }
}
