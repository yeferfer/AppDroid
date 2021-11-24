package Appdroid.ciclo4

import android.graphics.Color
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {



    val titles = arrayOf(
        "STATUE OF LIBERTY",
        "MUSEUM MADAME TUSSAUDS",
        "ONE WORLD OBSERVATORY",
        "911 MUSEUM",
        "EDGE",
    )

    val details = arrayOf(
        "La Libertad iluminando el mundo, conocida como la Estatua de la Libertad.",
        "Madame Tussauds New York es una atracción turística ubicada en 42nd Stree.",
        "Mirador que va de la planta 100 a la 102 del One World Trade Center, con exposiciones y restaurantes.",
        "El 9/11 National Memorial, es un museo en homenaje al 11 de septiembre de 1973",
        "Mirador que sobresale del piso 100, con suelo de cristal, bar y vistas panorámicas de Nueva York."
    )

    val images = intArrayOf(
        R.mipmap.ic_poi1_foreground,
        R.mipmap.ic_poi2_foreground,
        R.mipmap.ic_poi3_foreground,
        R.mipmap.ic_poi4_foreground,
        R.mipmap.ic_poi5_foreground
    )

    val stars = arrayOf(
        4.7f,
        4.4f,
        4.7f,
        4.6f,
        4.6f
    )

    val starScore = arrayOf(
        "4.7",
        "4.4",
        "4.7",
        "4.6",
        "4.6"
    )

    val cardColor = intArrayOf(
        R.drawable.custom_button_poi1,
        R.drawable.custom_button_poi2,
        R.drawable.custom_button_poi3,
        R.drawable.custom_button_poi4,
        R.drawable.custom_button_poi5,
    )


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemDetail.text = details[i]
        viewHolder.itemImage.setImageResource(images[i])
        viewHolder.itemStar.rating= stars[i]
        viewHolder.itemStarScore.text = starScore[i]
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        var itemStar:RatingBar
        var itemStarScore:TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_description)
            itemStar = itemView.findViewById(R.id.starCard)
            itemStarScore = itemView.findViewById(R.id.starScore)
        }
    }
}

