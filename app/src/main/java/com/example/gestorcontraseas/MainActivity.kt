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
import androidx.compose.ui.platform.LocalContext
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
                    Contenedor("login.txt")
                }
            }
        }
    }
}


@Composable
fun Contenedor(nombreArchivo: String) {
    val myContext = LocalContext.current
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var usuarioMostrar by remember { mutableStateOf("") }
    var contrasenaMostrar by remember { mutableStateOf("") }
    var index by remember { mutableStateOf(0) }
    var outs2 by remember { mutableStateOf(WriteReadUserPass.leerUserPassArchivo(myContext, nombreArchivo)) }
    Text(
        text = "Gestor de Contraseñas",
        fontSize = 30.sp,
        modifier = Modifier.padding(16.dp)
    )
    Column {
        Row(modifier = Modifier.padding(40.dp)) {
            Column {
                OutlinedTextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    label = { Text("Usuario") }
                )
                OutlinedTextField(
                    value = contrasena,
                    onValueChange = { contrasena = it },
                    label = { Text("Contraseña: ") }
                )
                var texto = usuario + ":" + contrasena
                Button(
                    onClick = {
                        var outs1 = WriteReadUserPass.guardarUserPassArchivo(myContext, texto, nombreArchivo)
                        Log.i("prueba", outs1)
                    }
                ) {
                    Text("Añadir")
                }
            }


        }
        Row(modifier = Modifier){
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
            ) {
                Text(
                    text = "Usuario: " + usuarioMostrar,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "Contraseña: " + contrasenaMostrar,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
                Row {
                    Button(
                        onClick = {
                            outs2 = WriteReadUserPass.leerUserPassArchivo(myContext, nombreArchivo)
                            val partes = outs2[index].split(":")
                            if (partes.size == 2) {
                                usuarioMostrar = partes[0]
                                contrasenaMostrar = partes[1]
                            }
                            Log.i("prueba", usuarioMostrar + contrasenaMostrar)
                        }
                    ) {
                        Text("Actualizar")
                    }
                    Button(
                        onClick = {
                            val UsuarioM = outs2[index]
                            //var outs1 = WriteReadUserPass.guardarUserPassArchivo(myContext, UsuarioM, nombreArchivo)
                            Log.i("prueba", UsuarioM)

                        }
                    ) {
                        Text("Borrar")
                    }
                    Button(
                        onClick = {
                            if (index > 0) {
                                Log.i("prueba", outs2.size.toString())
                                index -= 1
                                Log.i("prueba", index.toString())
                                val partes = outs2[index].split(":")
                                if (partes.size == 2) {
                                    usuarioMostrar = partes[0]
                                    contrasenaMostrar = partes[1]
                                }
                            }
                            else{
                                index = outs2.size - 1
                                val partes = outs2[index].split(":")
                                if (partes.size == 2) {
                                    usuarioMostrar = partes[0]
                                    contrasenaMostrar = partes[1]
                                }

                            }
                        }
                    ) {
                        Text("<")
                    }
                    Button(
                        onClick = {
                            if (index < outs2.size -1 ) {
                                Log.i("prueba", outs2.size.toString())
                                index += 1
                                Log.i("prueba", index.toString())
                                val partes = outs2[index].split(":")
                                if (partes.size == 2) {
                                    usuarioMostrar = partes[0]
                                    contrasenaMostrar = partes[1]
                                }
                            }
                            else{
                                index = 0
                                val partes = outs2[index].split(":")
                                if (partes.size == 2) {
                                    usuarioMostrar = partes[0]
                                    contrasenaMostrar = partes[1]
                                }

                            }
                        }
                    ) {
                        Text(">")
                    }
                }


            }
        }
    }


}

//agregar, ver, editar y eliminar contraseñas
//técnicas de seguridad adecuadas
//implementación del almacenamiento de contraseñas
// pruebas unitarias y de integración.