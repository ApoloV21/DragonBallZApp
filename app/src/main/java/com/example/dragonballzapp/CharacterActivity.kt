package com.example.dragonballzapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.dragonballzapp.api.RetrofitInstance
import com.example.dragonballzapp.model.Character
import com.example.dragonballzapp.ui.theme.DragonBallZAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DragonBallZAppTheme {
                CharacterListScreen()
            }
        }
    }
}

@Composable
fun CharacterListScreen() {
    val scope = rememberCoroutineScope()
    var characters by remember { mutableStateOf<List<Character>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitInstance.api.getCharacters()
            characters = response.items
            Log.d("CharactersActivity", "Descargados ${characters.size} personajes")
        } catch (e: Exception) {
            Log.e("CharactersActivity", "Error al descargar personajes", e)
        } finally {
            isLoading = false
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            items(characters) { character ->
                CharacterItem(character)
                HorizontalDivider()
            }
        }
    }
}


@Composable
fun CharacterItem(character: Character) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val intent = android.content.Intent(context, CharacterDetailActivity::class.java)
                val characterJson = com.google.gson.Gson().toJson(character)
                intent.putExtra("character", characterJson)
                context.startActivity(intent)
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(character.image),
            contentDescription = character.name,
            modifier = Modifier.width(100.dp).height(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = character.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Raza: ${character.race}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
