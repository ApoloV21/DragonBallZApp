package com.example.dragonballzapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.dragonballzapp.ui.theme.DragonBallZAppTheme
import com.example.dragonballzapp.CharactersActivity
import androidx.compose.ui.res.painterResource
import com.example.dragonballzapp.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DragonBallZAppTheme {
                WelcomeScreen {
                    startActivity(Intent(this, CharactersActivity::class.java))
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(onNavigate: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen en la parte superior
            Image(
                painter = painterResource(id = R.drawable.dragon_ball_z),
                contentDescription = "Logo Dragon Ball",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), // Puedes ajustar el tamaño aquí
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Texto de bienvenida
            Text(
                "¡Bienvenido a Dragon Ball Z App!",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón
            Button(onClick = onNavigate) {
                Text("Ver personajes")
            }
        }
    }
}
