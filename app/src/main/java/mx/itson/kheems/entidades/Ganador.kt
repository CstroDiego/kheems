package mx.itson.kheems.entidades

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import mx.itson.kheems.persistencia.KheemsDB

data class Ganador(
    var id: Int = 0,
    var nombre: String? = null,
    var intentos: Int = 0,
    var puntos: Int = 0
) {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var contexto: Context
        private lateinit var baseDatos: SQLiteDatabase

        fun inicializar(context: Context) {
            contexto = context.applicationContext
            val bonkDB = KheemsDB(contexto, "KheemsDB", null, 1)
            baseDatos = bonkDB.writableDatabase
        }

        fun guardar(nombre: String?, intentos: Int, puntos: Int) {
            if (!::baseDatos.isInitialized) {
                Log.e("Ganador", "La base de datos no ha sido inicializada")
                return
            }
            try {
                val valores = ContentValues()
                valores.put("nombre", nombre)
                valores.put("intentos", intentos)
                valores.put("puntos", puntos)
                baseDatos.insert("ganador", null, valores)
            } catch (ex: Exception) {
                Log.e("ocurrio un error al guardar ganador", ex.toString())
            }
        }

        fun obtenerTodos(): List<Ganador> {
            if (!::baseDatos.isInitialized) {
                Log.e("Ganador", "La base de datos no ha sido inicializada")
                return emptyList()
            }
            val ganadores: MutableList<Ganador> = ArrayList()
            try {
                val cursor: Cursor = baseDatos.rawQuery("SELECT * FROM ganador", null)
                if (cursor.moveToFirst()) {
                    do {
                        val ganador = Ganador(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getInt(3)
                        )
                        ganadores.add(ganador)
                    } while (cursor.moveToNext())
                }
                cursor.close()
            } catch (ex: Exception) {
                Log.e("ocurrio un error al obtener ganadores", ex.toString())
            }
            return ganadores
        }
    }
}
