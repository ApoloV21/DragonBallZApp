package com.example.dragonballzapp.api

import com.example.dragonballzapp.model.Character
import com.example.dragonballzapp.model.CharacterResponse
import retrofit2.http.GET

interface ApiService {
    @GET("api/characters")
    suspend fun getCharacters(): CharacterResponse
}

