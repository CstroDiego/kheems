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

/**
 * Clase GanadorAdapter
 * @author Diego Castro
 *
 * @constructor Esta clase es un adaptador personalizado para la lista de ganadores.
 *
 * @param context La instancia del contexto de la aplicación
 * @param ganadores La lista de objetos Ganador que se van a mostrar en la lista
 */
class GanadorAdapter(
    context: Context,
    private var ganadores: List<Ganador> // La lista de objetos Ganador que se van a mostrar en la lista
) : BaseAdapter() {

    // La instancia del contexto de la aplicación
    private var context: Context? = context

    /**
     * Obtiene el número de elementos de la lista
     *
     * @return El número de elementos de la lista
     */
    override fun getCount(): Int {
        return ganadores.size
    }

    /**
     * Obtiene el objeto Ganador de la lista en la posición indicada
     *
     * @param position La posición del objeto Ganador en la lista
     * @return El objeto Ganador de la lista en la posición indicada
     */
    override fun getItem(position: Int): Any {
        return ganadores[position]
    }

    /**
     * Obtiene el identificador del objeto Ganador de la lista en la posición indicada
     *
     * @param position La posición del objeto Ganador en la lista
     * @return El identificador del objeto Ganador de la lista en la posición indicada
     */
    override fun getItemId(position: Int): Long {
        return ganadores[position].id.toLong()
    }

    /**
     * Obtiene la vista del renglón de la lista en la posición indicada
     *
     * @param position La posición del renglón de la lista
     * @param convertView La vista del renglón de la lista
     * @param parent El ViewGroup padre de la vista
     * @return La vista del renglón de la lista en la posición indicada
     */
    @SuppressLint("StringFormatInvalid", "ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Infla la vista para cada elemento de la lista
        val renglon = LayoutInflater.from(context).inflate(R.layout.item_ganador, null)
        try {
            // Obtiene el objeto Ganador de la lista en la posición indicada
            val ganador = getItem(position) as Ganador
            // Obtiene los TextView del renglón de la lista para asignarles los valores correspondientes
            val txtNombre: TextView = renglon.findViewById(R.id.txtNombre)
            val txtPuntos: TextView = renglon.findViewById(R.id.txtPuntos)
            val txtIntentos: TextView = renglon.findViewById(R.id.txtIntentos)
            // Asigna los valores del objeto Ganador a los TextView correspondientes
            txtNombre.text = ganador.nombre
            txtPuntos.text = ganador.puntos.toString()
            txtIntentos.text = ganador.intentos.toString()

        } catch (ex: Exception) {
            // Si ocurre un error al obtener el objeto Ganador, se registra en el Log de errores de la aplicación
            Log.e("ocurrio un error al obtener ganador", ex.toString())
        }
        // Retorna la vista completa del renglón de la lista
        return renglon
    }
}
