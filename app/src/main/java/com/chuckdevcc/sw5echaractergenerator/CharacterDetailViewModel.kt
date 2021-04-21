package com.chuckdevcc.sw5echaractergenerator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class CharacterDetailViewModel() : ViewModel() {
    private val characterRepository = CharacterRepository.get()
    private val characterIdLiveData = MutableLiveData<UUID>()

    var characterLiveData: LiveData<CharacterSheet?> =
        Transformations.switchMap(characterIdLiveData) { charId ->
            characterRepository.getCharacter(charId)
        }

    fun loadCharacter(charId: UUID) {
        characterIdLiveData.value = charId
    }
}