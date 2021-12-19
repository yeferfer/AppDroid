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


class PoiActivity1 : AppCompatActivity() {

    //Archivos Json
    private val controlJson = ControladorArchivoJson()
    private var archivo: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi1)

        //Pantalla completa
        this.window.setFlags(
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
        val btninicio11 = findViewById<LottieAnimationView>(R.id.btnVolver11)
        val btninicio12 = findViewById<Button>(R.id.btnVolver12)

        btninicio11.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }

        btninicio12.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }

        //Mostrar mapa
        val btnMap = findViewById<LottieAnimationView>(R.id.animationView1)
        btnMap.setOnClickListener {
            val latIntent = Intent(this@PoiActivity1, MapsActivity::class.java)
            latIntent.putExtra("lat", 40.6892)
            latIntent.putExtra("long", -74.0445)
            startActivity(latIntent)
            val gotoMap = Intent(this, MapsActivity::class.java)
            startActivity(gotoMap)
        }

        //Recorrer Carusel
        val myList = ArrayList<SlideModel>()
        myList.add(SlideModel(R.drawable.poi1_img1))
        myList.add(SlideModel(R.drawable.poi1_img2))
        myList.add(SlideModel(R.drawable.poi1_img3))
        myList.add(SlideModel(R.drawable.poi1_img4))
        myList.add(SlideModel(R.drawable.poi1_img5))

        val imageSlider = findViewById<ImageSlider>(R.id.slider1)
        imageSlider.setImageList(myList, ScaleTypes.CENTER_CROP)

    }

}


