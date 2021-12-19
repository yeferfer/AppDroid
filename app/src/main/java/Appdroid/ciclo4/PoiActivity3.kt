package Appdroid.ciclo4

import Appdroid.ciclo4.archivojson.ControladorArchivoJson
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.synnapps.carouselview.CarouselView
import java.io.File

class PoiActivity3 : AppCompatActivity() {

    //Archivos Json
    private val controlJson = ControladorArchivoJson()
    private var archivo: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi3)

        //Pantalla completa
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //Documento Json
        archivo = File(this.applicationContext.filesDir, "pois.txt")
        val titulo = findViewById<TextView>(R.id.observatorio)
        val descripcion = findViewById<TextView>(R.id.Observatory_description)
        val datos = controlJson.llamar(archivo!!, 2)
        titulo.text = datos[0]
        descripcion.text = datos[1]

        //Ir inicio
        val btninicio31 = findViewById<LottieAnimationView>(R.id.btnVolver31)
        btninicio31.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }

        val btninicio32 = findViewById<Button>(R.id.btnVolver32)
        btninicio32.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }

        //Mostrar mapa
        val btnMap = findViewById<LottieAnimationView>(R.id.animationView3)
        btnMap.setOnClickListener {
            var latIntent = Intent(this@PoiActivity3, MapsActivity::class.java)
            latIntent.putExtra("lat", 40.7130)
            latIntent.putExtra("long", -74.0132)
            startActivity(latIntent)
            val gotoMap = Intent(this, MapsActivity::class.java)
            startActivity(gotoMap)
        }

        //Carusel Imagenes
        val myList = ArrayList<SlideModel>()
        myList.add(SlideModel(R.drawable.poi3_img1))
        myList.add(SlideModel(R.drawable.poi3_img2))
        myList.add(SlideModel(R.drawable.poi3_img3))
        myList.add(SlideModel(R.drawable.poi3_img4))
        myList.add(SlideModel(R.drawable.poi3_img5))

        val imageSlider = findViewById<ImageSlider>(R.id.slider3)
        imageSlider.setImageList(myList, ScaleTypes.CENTER_CROP)
    }
}