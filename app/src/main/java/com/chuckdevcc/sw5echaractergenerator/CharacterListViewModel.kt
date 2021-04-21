package com.chuckdevcc.sw5echaractergenerator

import androidx.lifecycle.ViewModel
import com.chuckdevcc.sw5echaractergenerator.database.CharacterDatabase

class CharacterListViewModel : ViewModel() {
    private val characterRepository = CharacterRepository.get()
    val CharacterListLiveData = characterRepository.getCharacters()

}