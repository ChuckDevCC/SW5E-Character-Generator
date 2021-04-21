package com.chuckdevcc.sw5echaractergenerator

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.chuckdevcc.sw5echaractergenerator.database.CharacterDao
import com.chuckdevcc.sw5echaractergenerator.database.CharacterDatabase
import java.util.*

// name our database!
private const val DATABASE_NAME = "character-database"

class CharacterRepository private constructor(context: Context) {
    // create an implementation of the abstract
    private val database: CharacterDatabase = Room.databaseBuilder(
        context.applicationContext,
        CharacterDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val CharacterDao = database.characterDao()

    fun getCharacters(): LiveData<List<CharacterSheet>> = CharacterDao.getCharacters()
    fun getCharacter(id: UUID): LiveData<CharacterSheet?> = CharacterDao.getCharacter(id)

    companion object {
        // initializer of the repository, and the get() function declaration.
        private var INSTANCE: CharacterRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CharacterRepository(context)
            }
        }

        fun get(): CharacterRepository {
            return INSTANCE
                ?: throw IllegalStateException("CharacterRepository must be initialized")
        }
    }
}
