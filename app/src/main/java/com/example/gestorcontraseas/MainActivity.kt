package com.example.gestorcontraseas



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestorcontraseas.ui.theme.GestorContraseñasTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestorContraseñasTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Contenedor()
                }
            }
        }
    }
}


@Composable
fun Contenedor(modifier: Modifier = Modifier) {
    var usuario:String = "Alvaro"
    var contrasena:String = "1234"
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = "Usuario: " + usuario,
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Contraseña: " + contrasena,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}