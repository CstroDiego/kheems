package mx.itson.kheems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import mx.itson.kheems.entidades.Ganador

class GanadorFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ganador_form)
    }

    fun agregarGanador(view: View, puntos: Int?, intentos: Int?) {
        try {
            val nombre = findViewById<EditText>(R.id.txtNombre)
            Ganador.guardar(this, nombre.text.toString(), puntos!!, intentos!!)
            Toast.makeText(this, "Bonk!", Toast.LENGTH_SHORT).show()
            finish()
        } catch (ex: Exception) {
            Toast.makeText(this, "Ocurrio un error al agregar usuario", Toast.LENGTH_SHORT).show()
        }
    }
}