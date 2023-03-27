package mx.itson.kheems

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() , View.OnClickListener {

    private var ubicacion = 0
    private var aciertos = 0
    private var intentos = 1
    private var puntos = 0

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Carga los valores iniciales de la aplicación.
        iniciarJuego()

        // Asigna el evento click a el botón de reiniciar.
        val btnReiniciar = findViewById<View>(R.id.btnReiniciar) as Button
        btnReiniciar.setOnClickListener(this)

        // Asigna el evento click a el botón de ver los ganadores.
        val btnGanadores = findViewById<View>(R.id.btnGanadores) as Button
        btnGanadores.setOnClickListener(this)

        // Asigna el evento click a todas las opciones.
        for (i in 1..12) {
            val btnSeleccion = findViewById<View>(
                resources.getIdentifier(
                    "opcion$i",
                    "id",
                    this.packageName
                )
            ) as ImageButton
            btnSeleccion.setOnClickListener(this)
        }
    }

    @SuppressLint("DiscouragedApi")
    private fun iniciarJuego() {
        // Asigna el icono de pregunta a todas las opciones.
        for (i in 1..12) {
            val btnSeleccion = findViewById<View>(
                resources.getIdentifier(
                    "opcion$i",
                    "id",
                    this.packageName
                )
            ) as ImageButton
            btnSeleccion.setBackgroundResource(R.drawable.icon_pregunta)
        }

        // Habilita todas las opciones.
        for (i in 1..12) {
            val btnSeleccion =
                findViewById<ImageButton>(resources.getIdentifier("opcion$i", "id", packageName))
            btnSeleccion.isEnabled = true
        }

        // En caso de haber, sumamos los puntos obtenidos en el juego anterior.
        puntos += aciertos

        // Reinicia el contador de aciertos.
        aciertos = 0

        // Genera un número aleatorio entre 1 y 12 para la carta perdedora.
        val random = Random()
        ubicacion = random.nextInt(12) + 1
        Toast.makeText(this, "ubicacion $ubicacion", Toast.LENGTH_LONG).show()
    }

    @SuppressLint("DiscouragedApi")
    private fun destapar(opcion: Int) {

        // Si la opción seleccionada es igual a la ubicación del icono de perdedor, se destapa la carta de perdedor.
        if (opcion == ubicacion) {
            for (i in 1..12) {
                val btnSeleccion = findViewById<View>(
                    resources.getIdentifier(
                        "opcion$i",
                        "id",
                        this.packageName
                    )
                ) as ImageButton

                // Destapa la carta de perdedor.
                if (i == opcion) {
                    // Sumamos un intento
                    intentos++

                    btnSeleccion.setBackgroundResource(R.drawable.icon_cheems_llora)
                    Toast.makeText(this, "¡PERMDISTE!", Toast.LENGTH_LONG).show()
                } else {
                    btnSeleccion.setBackgroundResource(R.drawable.icon_cheems)
                }
            }
        } else {

            // Se destapa la carta seleccionada.
            val btnSeleccion = findViewById<View>(
                resources.getIdentifier(
                    "opcion$opcion",
                    "id",
                    this.packageName
                )
            ) as ImageButton
            btnSeleccion.setBackgroundResource(R.drawable.icon_cheems)

            // Se deshabilita la opción seleccionada.
            val btnSeleccionado = findViewById<View>(
                resources.getIdentifier(
                    "opcion$opcion",
                    "id",
                    this.packageName
                )
            ) as ImageButton
            btnSeleccionado.isEnabled = false

            // Aumenta el contador de intentos.
            aciertos++

            // Si el contador de intentos es igual a 11, se destapa la carta de ganador.
            if (aciertos == 11) {
                Toast.makeText(this, "¡GAMNASTE!", Toast.LENGTH_LONG).show()
                val btnGanador = findViewById<View>(
                    resources.getIdentifier(
                        "opcion$ubicacion",
                        "id",
                        this.packageName
                    )
                ) as ImageButton
                btnGanador.setBackgroundResource(R.drawable.icon_cheems_ganador)

                // Sumamos los puntos obtenidos en el juego anterior.
                puntos += aciertos

                // Inicia la actividad GanadorFormActivity y envía los datos de intentos y puntos como extras
                val intent = Intent(this, GanadorFormActivity::class.java)
                intent.putExtra("intentos", intentos)
                intent.putExtra("puntos", puntos)
                startActivity(intent)

                // Reinicia el contador de aciertos.
                aciertos = 0

                // Reinicia el contador de intentos.
                intentos = 1

                // Reinicia el contador de puntos.
                puntos = 0
            }
        }

    }

    private fun ganadores() {
        Toast.makeText(this, "Bienvenido al salon de la fama", Toast.LENGTH_LONG).show()
        val intent = Intent(this, GanadorListActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnGanadores -> ganadores()
            R.id.btnReiniciar -> iniciarJuego()
            R.id.opcion1 -> destapar(1)
            R.id.opcion2 -> destapar(2)
            R.id.opcion3 -> destapar(3)
            R.id.opcion4 -> destapar(4)
            R.id.opcion5 -> destapar(5)
            R.id.opcion6 -> destapar(6)
            R.id.opcion7 -> destapar(7)
            R.id.opcion8 -> destapar(8)
            R.id.opcion9 -> destapar(9)
            R.id.opcion10 -> destapar(10)
            R.id.opcion11 -> destapar(11)
            R.id.opcion12 -> destapar(12)
        }
    }
}