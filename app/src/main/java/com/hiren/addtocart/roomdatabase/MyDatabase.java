package com.hiren.addtocart.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={Cart.class},version = 2)
public abstract class MyDatabase extends RoomDatabase {
    public abstract CartDao cartDao();
}
