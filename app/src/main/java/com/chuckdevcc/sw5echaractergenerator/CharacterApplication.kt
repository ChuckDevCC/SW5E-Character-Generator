package com.chuckdevcc.sw5echaractergenerator

import android.app.Application

// initializes the repository.
class CharacterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CharacterRepository.initialize(this)
    }
}