package Appdroid.ciclo4

import Appdroid.ciclo4.archivojson.ControladorArchivoJson
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import java.io.File

class PoiActivity2 : AppCompatActivity() {

    //Archivos Json
    private val controlJson = ControladorArchivoJson()
    private var archivo: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi2)

        //Pantalla completa
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //Documento Json
        archivo = File(this.applicationContext.filesDir, "pois.txt")
        val titulo = findViewById<TextView>(R.id.museo)
        val descripcion = findViewById<TextView>(R.id.museum_description)
        val datos = controlJson.llamar(archivo!!, 1)
        titulo.text = datos[0]
        descripcion.text = datos[1]

        //Ir inicio
        val btninicio21 = findViewById<LottieAnimationView>(R.id.btnVolver21)

        btninicio21.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }

        val btninicio22 = findViewById<Button>(R.id.btnVolver22)
        btninicio22.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }

        //Mostrar mapa
        val btnMap = findViewById<LottieAnimationView>(R.id.animationView2)
        btnMap.setOnClickListener {
            val latIntent = Intent(this@PoiActivity2, MapsActivity::class.java)
            latIntent.putExtra("lat", 40.7564)
            latIntent.putExtra("long", -73.9888)
            startActivity(latIntent)
            val gotoMap = Intent(this, MapsActivity::class.java)
            startActivity(gotoMap)
        }

        //Recorrer Carusel
        val myList = ArrayList<SlideModel>()
        myList.add(SlideModel(R.drawable.poi2_img1))
        myList.add(SlideModel(R.drawable.poi2_img2))
        myList.add(SlideModel(R.drawable.poi2_img3))
        myList.add(SlideModel(R.drawable.poi2_img4))
        myList.add(SlideModel(R.drawable.poi2_img5))

        val imageSlider = findViewById<ImageSlider>(R.id.slider2)
        imageSlider.setImageList(myList, ScaleTypes.CENTER_CROP)
    }
}