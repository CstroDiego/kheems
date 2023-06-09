package mx.itson.kheems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import mx.itson.kheems.adapters.GanadorAdapter
import mx.itson.kheems.entidades.Ganador

/**
 * Lista de ganadores
 *
 * @constructor Crear una nueva actividad de lista de ganadores
 */
class GanadorListActivity : AppCompatActivity() {

    private var listaGanadores: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ganador_list)

        listaGanadores = findViewById(R.id.listGanador)
        cargarLista()
    }

    /**
     * Carga la lista de ganadores
     *
     */
    private fun cargarLista() {
        val ganadores: List<Ganador> = Ganador.obtenerTodos(this).sortedWith(compareBy({ it.puntos }, { it.nombre }))
            .take(10)
        val adapter = GanadorAdapter(this, ganadores)
        listaGanadores?.adapter = adapter
    }
}