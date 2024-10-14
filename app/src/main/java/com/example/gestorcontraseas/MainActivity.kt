package com.example.gestorcontraseas



import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
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
                    Contenedor("ejemplo.txt")
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

    Column {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().heightIn(70.dp).background(Color(0xFFFFAF42))
        ){
            Text(
                text = "Gestor de Contraseñas",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
            )
        }
        Row(modifier = Modifier.padding(40.dp)) {
            Column {
                Text(
                    text = "Añadir Usuario",
                    fontSize = 15.sp,
                    modifier = Modifier
                )
                OutlinedTextField(
                    value = usuario,
                    onValueChange = { newText -> usuario = newText },
                    label = { Text("Usuario") }
                )
                OutlinedTextField(
                    value = contrasena,
                    onValueChange = { newText -> contrasena = newText },
                    label = { Text("Contraseña: ") }
                )
                var texto = usuario + ":" + contrasena
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFAF42),
                    ),
                    modifier = Modifier.padding(top = 15.dp),
                    onClick = {
                        usuario = ""
                        contrasena = ""
                        var outs1 = WriteReadUserPass.guardarUserPassArchivo(myContext, texto, nombreArchivo)
                        Log.i("prueba", outs1)
                    }
                ) {
                    Text("Añadir")
                }
            }
        }
        Row(modifier = Modifier.padding(40.dp)){
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
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFAF42),
                        ),
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
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFAF42),
                        ),
                        onClick = {
                            val UsuarioM = outs2[index]
                            WriteReadUserPass.eliminarUserPassPorPuesto(myContext, nombreArchivo, UsuarioM)
                            Log.i("prueba", UsuarioM)

                        }
                    ) {
                        Text("Borrar")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFAF42),
                        ),
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
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFAF42),
                        ),
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