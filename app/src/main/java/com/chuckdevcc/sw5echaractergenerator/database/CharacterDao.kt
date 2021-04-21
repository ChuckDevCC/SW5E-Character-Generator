package com.chuckdevcc.sw5echaractergenerator.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.chuckdevcc.sw5echaractergenerator.CharacterSheet
import java.util.*

@Dao
interface CharacterDao {
    @Query("SELECT * FROM charactersheet")
    fun getCharacters(): LiveData<List<CharacterSheet>>

    @Query("SELECT * FROM charactersheet WHERE id=(:id)")
    fun getCharacter(id: UUID): LiveData<CharacterSheet?>

}