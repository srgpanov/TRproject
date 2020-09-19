package com.example.trproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.trproject.data.models.CountryItem


@Database(
    entities = [
        CountryItem::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverter::class)
abstract class CountryDataBase : RoomDatabase() {
    abstract fun newsDao(): CountryDao
}