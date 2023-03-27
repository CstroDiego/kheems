package mx.itson.kheems

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mx.itson.kheems.entidades.Ganador

/**
 * Clase GanadorFormActivity: esta clase define una actividad para guardar un ganador en la base de datos
 * @author Diego Castro
 *
 * @constructor Crea una nueva actividad de formulario de ganador
 */
class GanadorFormActivity : AppCompatActivity() {

    private var puntos = 0
    private var intentos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ganador_form)

        // Obtiene los puntos e intentos del intent que abrió esta actividad
        puntos = intent.getIntExtra("puntos", 0)
        intentos = intent.getIntExtra("intentos", 0)

        // Configura el botón "Guardar"
        findViewById<Button>(R.id.btnGuardar).setOnClickListener { guardarGanador() }
    }


    /**
     * Método guardarGanador: guarda el ganador en la base de datos y muestra un mensaje de éxito
     */
    private fun guardarGanador() {
        try {
            // Obtiene el nombre del ganador del campo de texto correspondiente
            val nombre = findViewById<EditText>(R.id.txtNombre).text.toString()

            // Guarda el ganador en la base de datos usando el método estático "guardar" de la clase Ganador
            Ganador.guardar(this, nombre, intentos, puntos)

            // Muestra un mensaje de éxito y cierra esta actividad
            Toast.makeText(this, "Ganador agregado correctamente", Toast.LENGTH_SHORT).show()
            finish()
        } catch (ex: Exception) {
            // Muestra un mensaje de error en caso de que ocurra una excepción al guardar el ganador
            Toast.makeText(this, "Ocurrió un error al agregar el ganador", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
