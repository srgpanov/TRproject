package com.example.trproject.data.local

import androidx.room.TypeConverter
import com.example.trproject.data.models.Currency
import com.example.trproject.data.models.Language
import com.example.trproject.data.models.RegionalBloc
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object RoomConverter {
    @TypeConverter
    @JvmStatic
    fun fromString(stringListString: String?): List<String>? {
        val listType = object : TypeToken<ArrayList<String>?>() {}.type
        return Gson().fromJson(stringListString, listType)
    }

    @TypeConverter
    @JvmStatic
    fun toString(stringList: List<String>?): String? {
        return Gson().toJson(stringList)
    }
    @TypeConverter
    @JvmStatic
    fun fromDoubleList(doubleListString: String?): List<Double>? {
        val listType = object : TypeToken<ArrayList<Double?>?>() {}.type
        return Gson().fromJson(doubleListString, listType)
    }

    @TypeConverter
    @JvmStatic
    fun toDoubleList(stringList: List<Double>?): String {
        return Gson().toJson(stringList)
    }

    @TypeConverter
    @JvmStatic
    fun fromCurrencyList(currencyList: List<Currency>?): String {
        return Gson().toJson(currencyList)
    }

    @TypeConverter
    @JvmStatic
    fun toCurrencyList(data: String): List<Currency>? {
        val listType = object : TypeToken<ArrayList<Currency?>?>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromLanguageList(languageList: List<Language>?): String {
        return Gson().toJson(languageList)
    }

    @TypeConverter
    @JvmStatic
    fun toLanguageList(data: String): List<Language>? {
        val listType = object : TypeToken<ArrayList<Language?>?>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromRegionList(regionList: List<RegionalBloc>?): String {
        return Gson().toJson(regionList)
    }

    @TypeConverter
    @JvmStatic
    fun toRegionList(data: String): List<RegionalBloc>? {
        val listType = object : TypeToken<ArrayList<RegionalBloc?>?>() {}.type
        return Gson().fromJson(data, listType)
    }
}