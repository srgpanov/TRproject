package com.example.trproject.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.trproject.data.models.CountryItem
import com.example.trproject.data.models.CountryList

@Dao
interface CountryDao {

    @Insert(onConflict =IGNORE )
    suspend fun insert(country:CountryItem):Long

    @Update
    abstract suspend fun update(country:CountryItem)

    @Transaction
    open suspend fun insertOrUpdate(country: CountryItem) {
        val id = insert(country)
        if (id == -1L) update(country)
    }

    @Transaction
    open suspend fun insertOrUpdate(countryList: List<CountryItem>) {
        for (country in countryList){
            insertOrUpdate(country)
        }
    }

    @Query("SELECT * FROM country")
    suspend fun getAll():List<CountryItem>
}