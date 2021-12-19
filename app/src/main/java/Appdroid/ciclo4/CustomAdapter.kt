package Appdroid.ciclo4

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val contexto: Context
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val titles = arrayOf(
        "STATUE OF LIBERTY",
        "MUSEUM MADAME TUSSAUDS",
        "ONE WORLD OBSERVATORY",
        "911 MUSEUM",
        "EDGE"
    )

    private val details = arrayOf(
        "La Libertad iluminando el mundo, conocida como la Estatua de la Libertad.",
        "Madame Tussauds New York es una atracción turística ubicada en 42nd Stree.",
        "Mirador que va de la planta 100 a la 102 del One World Trade Center, con exposiciones y restaurantes.",
        "El 9/11 National Memorial, es un museo en homenaje al 11 de septiembre de 1973",
        "Mirador que sobresale del piso 100, con suelo de cristal, bar y vistas panorámicas de NY."
    )

    private val images = intArrayOf(
        R.mipmap.ic_poi1_foreground,
        R.mipmap.ic_poi2_foreground,
        R.mipmap.ic_poi3_foreground,
        R.mipmap.ic_poi4_foreground,
        R.mipmap.ic_poi5_foreground
    )

    private val stars = arrayOf(
        4.7f,
        4.4f,
        4.7f,
        4.6f,
        4.6f
    )

    private val starScore = arrayOf(
        "4.7",
        "4.4",
        "4.7",
        "4.6",
        "4.6"
    )

    private val cardColor = intArrayOf(
        android.graphics.Color.parseColor("#E2F4FA"),
        android.graphics.Color.parseColor("#C6E9F6"),
        android.graphics.Color.parseColor("#A9DFF1"),
        android.graphics.Color.parseColor("#8DD4ED"),
        android.graphics.Color.parseColor("#70C9E8"),
    )


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v, contexto)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemDetail.text = details[i]
        viewHolder.itemImage.setImageResource(images[i])
        viewHolder.itemStar.rating = stars[i]
        viewHolder.itemStarScore.text = starScore[i]
        viewHolder.colorCard2.setCardBackgroundColor(cardColor[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View, contexto: Context) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.item_image)
        var itemTitle: TextView = itemView.findViewById(R.id.item_title)
        var itemDetail: TextView = itemView.findViewById(R.id.item_description)
        var itemStar: RatingBar = itemView.findViewById(R.id.starCard)
        var itemStarScore: TextView = itemView.findViewById(R.id.starScore)
        var colorCard2: CardView = itemView.findViewById(R.id.card_view)

        init {

            itemView.setOnClickListener {
                when {
                    (itemTitle.text).equals("STATUE OF LIBERTY") -> {
                        val inte = Intent(contexto, PoiActivity1::class.java)
                        contexto.startActivity(inte)
                    }
                    (itemTitle.text).equals("MUSEUM MADAME TUSSAUDS") -> {
                        val inte = Intent(contexto, PoiActivity2::class.java)
                        contexto.startActivity(inte)

                    }
                    (itemTitle.text).equals("ONE WORLD OBSERVATORY") -> {
                        val inte = Intent(contexto, PoiActivity3::class.java)
                        contexto.startActivity(inte)

                    }
                    (itemTitle.text).equals("911 MUSEUM") -> {
                        val inte = Intent(contexto, PoiActivity4::class.java)
                        contexto.startActivity(inte)

                    }
                    (itemTitle.text).equals("EDGE") -> {
                        val inte = Intent(contexto, PoiActivity5::class.java)
                        contexto.startActivity(inte)
                    }
                }

            }
        }
    }
}





