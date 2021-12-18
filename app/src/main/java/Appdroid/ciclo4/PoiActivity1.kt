package Appdroid.ciclo4

import Appdroid.ciclo4.archivojson.ControladorArchivoJson
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.synnapps.carouselview.CarouselView
import java.io.File
import java.lang.reflect.Array


class PoiActivity1 : AppCompatActivity() {

    //Archivos Json
    private val controlJson = ControladorArchivoJson()
    private var archivo: File? = null

    //Carruser de imagenes
    var sampleImages: IntArray = intArrayOf(
        R.drawable.poi1_img1,
        R.drawable.poi1_img2,
        R.drawable.poi1_img3,
        R.drawable.poi1_img4,
        R.drawable.poi1_img5,
    )

    var cities = arrayOf(
        "STATUE OF LIBERTY IMG 1",
        "STATUE OF LIBERTY IMG 2",
        "STATUE OF LIBERTY IMG 3",
        "STATUE OF LIBERTY IMG 4",
        "STATUE OF LIBERTY IMG 5",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi1)

        //Pantalla completa
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //Documento Json
        archivo = File(this.applicationContext.filesDir, "pois.txt")

        val titulo = findViewById<TextView>(R.id.liberty)
        val descripcion = findViewById<TextView>(R.id.estatua_description)
        val datos = controlJson.llamar(archivo!!, 0)
        titulo.text = datos[0]
        descripcion.text = datos[1]

        //Ir inicio
        val btninicio = findViewById<Button>(R.id.btnVolver1)
        btninicio.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }

        //Mostrar mapa
        val btnMap = findViewById<Button>(R.id.button)
        btnMap.setOnClickListener {
            var latIntent = Intent(this@PoiActivity1, MapsActivity::class.java)
            latIntent.putExtra("lat", 40.6892)
            latIntent.putExtra("long", -74.0445)
            startActivity(latIntent)
            val gotoMap = Intent(this, MapsActivity::class.java)
            startActivity(gotoMap)
        }

        //Recorrer Carusel
        val carouselView = findViewById<CarouselView>(R.id.carouselView)
        carouselView.pageCount = cities.size

        carouselView.setImageListener { position, imageView ->
            imageView.setImageResource(sampleImages[position])
        }

        carouselView.setImageClickListener { position ->
            Toast.makeText(applicationContext, cities[position], Toast.LENGTH_SHORT).show()
        }

    }
}

