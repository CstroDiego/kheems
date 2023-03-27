package mx.itson.kheems.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import mx.itson.kheems.R
import mx.itson.kheems.entidades.Ganador

class GanadorAdapter(
    context: Context,
    ganadores: List<Ganador>
) : BaseAdapter() {

    var context: Context? = context
    var ganadores: List<Ganador> = ganadores

    override fun getCount(): Int {
        return ganadores.size
    }

    override fun getItem(position: Int): Any {
        return ganadores[position]
    }

    override fun getItemId(position: Int): Long {
        return ganadores[position].id.toLong()
    }

    @SuppressLint("StringFormatInvalid", "ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val renglon = LayoutInflater.from(context).inflate(R.layout.item_ganador, null)
        try {
            val ganador = getItem(position) as Ganador
            val txtNombre: TextView = renglon.findViewById(R.id.txtNombre)
            val txtPuntos: TextView = renglon.findViewById(R.id.txtPuntos)
            val txtIntentos: TextView = renglon.findViewById(R.id.txtIntentos)
            txtNombre.text = ganador.nombre
            txtPuntos.text = ganador.puntos.toString()
            txtIntentos.text = ganador.intentos.toString()

        } catch (ex: Exception) {
            Log.e("ocurrio un error al obtener ganador", ex.toString())
        }
        return renglon
    }
}
