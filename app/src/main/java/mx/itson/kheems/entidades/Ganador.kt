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

        fun guardar(context: Context, nombre: String?, intentos: Int, puntos: Int) {

            contexto = context.applicationContext
            val kheemsDB = KheemsDB(contexto, "KheemsDB", null, 1)
            baseDatos = kheemsDB.writableDatabase

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

        fun obtenerTodos(context: Context): List<Ganador> {

            contexto = context.applicationContext
            val kheemsDB = KheemsDB(context, "KheemsDB", null, 1)
            val baseDatos = kheemsDB.readableDatabase

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
