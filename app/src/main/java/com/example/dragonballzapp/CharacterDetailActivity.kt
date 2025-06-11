package com.example.dragonballzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.dragonballzapp.model.Character
import com.example.dragonballzapp.ui.theme.DragonBallZAppTheme
import com.google.gson.Gson


class CharacterDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val characterJson = intent.getStringExtra("character")
        val character = Gson().fromJson(characterJson, Character::class.java)

        setContent {
            DragonBallZAppTheme {
                CharacterDetailScreen(character)
            }
        }
    }
}

@Composable
fun CharacterDetailScreen(character: Character) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo desenfocada simulada con opacidad
        Image(
            painter = rememberAsyncImagePainter(character.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.2f
        )

        // Capa opcional para mejorar legibilidad
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.3f))
        )

        // Contenido del personaje
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = character.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Raza: ${character.race}")
            Text(text = "Género: ${character.gender}")
            Text(text = "Ki: ${character.ki}")
            Text(text = "Ki Máximo: ${character.maxKi}")
            Text(text = "Afiliación: ${character.affiliation}")
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "📖 Descripción:", style = MaterialTheme.typography.titleMedium)
            Text(text = character.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
