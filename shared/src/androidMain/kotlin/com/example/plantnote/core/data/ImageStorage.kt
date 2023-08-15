package com.example.plantnote.core.data

import android.content.Context
import com.example.plantnote.database.PlantDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID


actual class ImageStorage(private val context: Context) {
    actual suspend fun saveImage(bytes: ByteArray): String {
        return withContext(Dispatchers.IO) {
            val fileName = UUID.randomUUID().toString() + ".jpg"
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
                outputStream.write(bytes)
            }
            fileName
        }
    }

    actual suspend fun getImage(fileName: String): ByteArray? {
        return withContext(Dispatchers.IO) {
            context.openFileInput(fileName).use { inputsStream ->
                inputsStream.readBytes()
            }
        }
    }

    actual suspend fun deleteImage(fileName: String) {
        withContext(Dispatchers.IO) {
            context.deleteFile(fileName)
        }
    }
}