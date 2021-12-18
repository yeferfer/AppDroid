package Appdroid.ciclo4

import Appdroid.ciclo4.archivojson.ControladorArchivoJson
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.synnapps.carouselview.CarouselView
import java.io.File

class PoiActivity5 : AppCompatActivity() {

    //Archivos Json
    private val controlJson = ControladorArchivoJson()
    private var archivo: File? = null

    //Carruser de imagenes
    var sampleImages: IntArray = intArrayOf(
        R.drawable.poi5_img1,
        R.drawable.poi5_img2,
        R.drawable.poi5_img3,
        R.drawable.poi5_img4,
        R.drawable.poi5_img5,
    )

    var cities = arrayOf(
        "EDGE IMG 1",
        "EDGE IMG 2",
        "EDGE IMG 3",
        "EDGE IMG 4",
        "EDGE IMG 5",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi5)

        //Pantalla completa
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //Documento Json
        archivo = File(this.applicationContext.filesDir, "pois.txt")
        val titulo = findViewById<TextView>(R.id.Edge)
        val descripcion = findViewById<TextView>(R.id.Edgedescription)
        val datos = controlJson.llamar(archivo!!, 4)
        titulo.text = datos[0]
        descripcion.text = datos[1]

        //Ir inicio
        val btninicio = findViewById<Button>(R.id.btnVolver5)
        btninicio.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }

        //Mostrar mapa
        val btnMap = findViewById<Button>(R.id.button)
        btnMap.setOnClickListener {
            var latIntent = Intent(this@PoiActivity5, MapsActivity::class.java)
            latIntent.putExtra("lat", 40.7559)
            latIntent.putExtra("long", -74.0005)
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