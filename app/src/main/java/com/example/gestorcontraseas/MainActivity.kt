package com.example.gestorcontraseas



import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var contrasena:String = "1111"
    Text(
        text = "Gestor de Contraseñas",
        fontSize = 30.sp,
        modifier = Modifier.padding(16.dp)
    )
    Box(modifier = Modifier.padding(40.dp)) {
        Column {
            var usuario by remember { mutableStateOf("") }

            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Usuario") }
            )

            var contrasena by remember { mutableStateOf("") }

            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña: ") }
            )
            Button(
                onClick = {
//                    var outs2 = WriteReadUserPass.leerUserPassArchivo(myContext, nombreArchivo)
                    Log.i("prueba", "hola")
//                    modifier = Modifier.

                }
            ) {
                Text("Añadir")
            }
        }


    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = "Usuario: " + usuario,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Contraseña: " + contrasena,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

//agregar, ver, editar y eliminar contraseñas
//técnicas de seguridad adecuadas
//implementación del almacenamiento de contraseñas
// pruebas unitarias y de integración.