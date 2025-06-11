package com.example.dragonballzapp.model

data class CharacterResponse(
    val items: List<Character>,
    val totalItems: Int,
    val currentPage: Int,
    val totalPages: Int
)