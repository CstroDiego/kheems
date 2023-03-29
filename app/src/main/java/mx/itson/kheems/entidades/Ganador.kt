package mx.itson.kheems.entidades

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import mx.itson.kheems.persistencia.KheemsDB

/**
 * Clase Ganador
 * @author Diego Castro
 *
 * Entidad que representa a un ganador en el juego Kheems.
 * Contiene información del nombre del jugador, la cantidad de intentos realizados para ganar y la cantidad de puntos obtenidos.
 *
 * @property id Identificador único del ganador
 * @property nombre Nombre del ganador
 * @property intentos Cantidad de intentos realizados por el ganador
 * @property puntos Puntos obtenidos por el ganador
 * @constructor Crea una instancia de la clase Ganador
 */
data class Ganador(
    var id: Int = 0,
    var nombre: String? = null,
    var intentos: Int = 0,
    var puntos: Int = 0
) {
    // Se define un objeto compañero que permite realizar operaciones sobre la clase Ganador sin necesidad de instanciarla.
    companion object {
        // Contexto y base de datos de la aplicación.
        @SuppressLint("StaticFieldLeak")
        private lateinit var contexto: Context
        private lateinit var baseDatos: SQLiteDatabase

        /**
         * Guarda un nuevo ganador en la base de datos
         *
         * @param context Contexto de la aplicación
         * @param nombre Nombre del ganador
         * @param intentos Cantidad de intentos realizados por el ganador
         * @param puntos Puntos obtenidos por el ganador
         */
        fun guardar(context: Context, nombre: String?, intentos: Int, puntos: Int) {
            // Se inicializa el contexto de la aplicación y la base de datos
            contexto = context.applicationContext
            val kheemsDB = KheemsDB(contexto, "KheemsDB", null, 1)
            baseDatos = kheemsDB.writableDatabase

            try {
                // Se crea un objeto ContentValues con los valores a guardar en la base de datos
                val valores = ContentValues()
                valores.put("nombre", nombre)
                valores.put("intentos", intentos)
                valores.put("puntos", puntos)
                // Se inserta el registro en la tabla "ganador"
                baseDatos.insert("ganador", null, valores)
            } catch (ex: Exception) {
                Log.e("ocurrio un error al guardar ganador", ex.toString())
            }
        }

        /**
         * Obtiene todos los ganadores almacenados en la base de datos
         * @param context Contexto de la aplicación
         * @return Lista de objetos Ganador
         */
        fun obtenerTodos(context: Context): List<Ganador> {

            // Se inicializa el contexto de la aplicación y la base de datos
            contexto = context.applicationContext
            val kheemsDB = KheemsDB(context, "KheemsDB", null, 1)
            val baseDatos = kheemsDB.readableDatabase

            // Crear una lista mutable de objetos Ganador para almacenar los resultados de la consulta
            val ganadores: MutableList<Ganador> = ArrayList()

            try {
                // Ejecutar la consulta para obtener todos los registros de la tabla ganador
                val cursor: Cursor = baseDatos.rawQuery("SELECT * FROM ganador", null)

                // Si el cursor tiene al menos un registro, iterar sobre los resultados
                if (cursor.moveToFirst()) {
                    do {

                        // Crear un objeto Ganador con los datos del registro actual
                        val ganador = Ganador(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getInt(3)
                        )

                        // Agregar el objeto Ganador a la lista
                        ganadores.add(ganador)
                    } while (cursor.moveToNext())
                }

                // Cerrar el cursor para liberar los recursos
                cursor.close()

            } catch (ex: Exception) {
                // En caso de error, registrar el mensaje de error en el registro de eventos de la aplicación
                Log.e("ocurrio un error al obtener ganadores", ex.toString())
            }

            // Devolver la lista de ganadores obtenida de la base de datos
            return ganadores
        }
    }
}
