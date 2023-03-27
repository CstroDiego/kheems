package mx.itson.kheems

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mx.itson.kheems.entidades.Ganador

class GanadorFormActivity : AppCompatActivity() {

    private var puntos = 0
    private var intentos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ganador_form)

        puntos = intent.getIntExtra("puntos", 0)
        intentos = intent.getIntExtra("intentos", 0)
        findViewById<Button>(R.id.btnGuardar).setOnClickListener { guardarGanador() }
    }

    private fun guardarGanador() {
        try {
            val nombre = findViewById<EditText>(R.id.txtNombre).text.toString()
            Ganador.guardar( nombre, intentos, puntos)
            Toast.makeText(this, "Ganador agregado correctamente", Toast.LENGTH_SHORT).show()
            finish()
        } catch (ex: Exception) {
            Toast.makeText(this, "Ocurrió un error al agregar el ganador", Toast.LENGTH_SHORT).show()
        }
    }
}
