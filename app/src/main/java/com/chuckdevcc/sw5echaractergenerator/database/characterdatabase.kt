package com.chuckdevcc.sw5echaractergenerator.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.chuckdevcc.sw5echaractergenerator.CharacterSheet

@Database(entities = [CharacterSheet::class], version = 1)
@TypeConverters(CharTypeConverters::class)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}