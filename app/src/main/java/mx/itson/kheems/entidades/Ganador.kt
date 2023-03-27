package mx.itson.kheems.entidades

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import mx.itson.kheems.persistencia.KheemsDB

@SuppressLint("StaticFieldLeak")
object Ganador { var id: Int = 0
    var nombre: String? = null
    var intentos: Int = 0
    var puntos: Int = 0

    private lateinit var contexto: Context
    private lateinit var baseDatos: SQLiteDatabase

    fun inicializar(context: Context) {
        contexto = context.applicationContext
        val bonkDB = KheemsDB(contexto, "KheemsDB", null, 1)
        baseDatos = bonkDB.writableDatabase
    }

    fun guardar(context: Context, nombre: String?, intentos: Int, puntos: Int) {
        inicializar(context)
        try {
            val valores = ContentValues()
            valores.put("nombre", nombre)
            valores.put("intentos", intentos)
            valores.put("puntos", puntos)
            baseDatos.insert("Ganador", null, valores)
        } catch (ex: Exception) {
            Log.e("ocurrio un error al guardar ganador", ex.toString())
        }
    }

    fun obtenerTodos(context: Context): List<Ganador> {
        inicializar(context)
        val ganadores: MutableList<Ganador> = ArrayList()
        try {
            val cursor =
                baseDatos.rawQuery("SELECT id, nombre, intentos, puntos FROM ganador", null)
            while (cursor.moveToNext()) {
                val ganador = Ganador.apply {
                    id = cursor.getInt(0)
                    nombre = cursor.getString(1)
                    intentos = cursor.getInt(2)
                    puntos = cursor.getInt(3)
                }
                ganadores.add(ganador)
            }
            cursor.close()
        } catch (ex: Exception) {
            Log.e("ocurrio un error al obtener ganador", ex.toString())
        }
        return ganadores
    }
}