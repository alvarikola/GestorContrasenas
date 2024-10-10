package com.example.gestorcontraseas

import android.content.Context
import android.os.Environment
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.OutputStreamWriter

class WriteReadUserPass {
    companion object{
        fun guardarUserPassArchivo(context: Context, texto: String, nombreArchivo: String): String {
            val estadoAlmacenamiento = Environment.getExternalStorageState()

            if (estadoAlmacenamiento == Environment.MEDIA_MOUNTED) {
                val directorio = context.filesDir
                val archivo = File(directorio, nombreArchivo)
                try {
                    val flujoSalida = FileOutputStream(archivo, true)
                    val writer = OutputStreamWriter(flujoSalida)
                    writer.append(texto + "\n")
                    writer.close()

                    return "Usuario y contraseña añadido en $directorio $nombreArchivo"
                } catch (e: Exception) {
                    e.printStackTrace()
                    return "Error al guardar"
                }
            }else {
                return "No se pudo acceder al almacenamiento externo"
            }
        }
        fun leerUserPassArchivo(context: Context, nombreArchivo: String): String {
            val estadoAlmacenamiento = Environment.getExternalStorageState()

            if (estadoAlmacenamiento == Environment.MEDIA_MOUNTED) {
                val directorio = context.filesDir
                val archivo = File(directorio, nombreArchivo)
                try {
                    val flujoEntrada = FileReader(archivo)
                    val leer = BufferedReader(flujoEntrada)
                    val contenidoArchivo = leer.readLines()
                    leer.close()

                    return "Contenido del archivo: $contenidoArchivo"
                    flujoEntrada.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                    return "Error al leer"
                }
            }else {
                return "No se pudo acceder al almacenamiento externo"
            }
        }
    }
}