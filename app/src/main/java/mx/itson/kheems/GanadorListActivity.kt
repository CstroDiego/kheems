package mx.itson.kheems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import mx.itson.kheems.adapters.GanadorAdapter
import mx.itson.kheems.entidades.Ganador

class GanadorListActivity : AppCompatActivity() {

    private var listaGanadores: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ganador_list)

        listaGanadores = findViewById(R.id.listGanador)
        cargarLista()
    }

    private fun cargarLista() {
        val ganadores: List<Ganador> = Ganador.obtenerTodos(this)
        val adapter = GanadorAdapter(this, ganadores)
        listaGanadores?.adapter = adapter
    }
}