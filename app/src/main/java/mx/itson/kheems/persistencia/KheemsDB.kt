package mx.itson.kheems.persistencia

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Clase KheemsDB
 * @author Diego Castro
 *
 * Crea una instancia de la base de datos de la aplicación.
 *
 * @param context Contexto de la aplicación
 * @param name Nombre de la base de datos
 * @param factory No se utiliza
 * @param version Versión de la base de datos
 */
class KheemsDB(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    /**
     * Crea la tabla 'Ganador' en la base de datos.
     *
     * @param sqliteDatabase Base de datos
     */
    @SuppressLint("LongLogTag")
    override fun onCreate(sqliteDatabase: SQLiteDatabase) {
        try {
            Log.i("DataBase", "Creando base de datos")
            sqliteDatabase.execSQL(
                "CREATE TABLE Ganador" +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT, intentos INTEGER, puntos INTEGER)"
            )
        } catch (ex: Exception) {
            Log.e("ocurrio un error al crear la base de datos", ex.toString())
        }
    }

    /**
     * Borra la tabla 'Ganador' y la vuelve a crear.
     *
     * @param sqliteDatabase Base de datos
     * @param oldVersion Versión anterior de la base de datos
     * @param newVersion Versión nueva de la base de datos
     */
    override fun onUpgrade(sqliteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.i("DataBase", "Actualizando base de datos")
        sqliteDatabase.execSQL("DROP TABLE IF EXISTS Ganador")
        onCreate(sqliteDatabase)
    }
}
