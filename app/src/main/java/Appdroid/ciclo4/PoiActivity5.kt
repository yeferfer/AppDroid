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

class PoiActivity5 : AppCompatActivity() {

    //Archivos Json
    private val controlJson = ControladorArchivoJson()
    private var archivo: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi5)

        //Pantalla completa
        this.window.setFlags(
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
        val btninicio51 = findViewById<LottieAnimationView>(R.id.btnVolver51)
        btninicio51.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }

        val btninicio52 = findViewById<Button>(R.id.btnVolver52)
        btninicio52.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }

        //Mostrar mapa
        val btnMap = findViewById<LottieAnimationView>(R.id.animationView5)
        btnMap.setOnClickListener {
            val latIntent = Intent(this@PoiActivity5,MapsActivity::class.java)
            latIntent.putExtra("inice",4)
            startActivity(latIntent)
            val gotoMap = Intent(this, MapsActivity::class.java)
            startActivity(gotoMap)
        }

        //Carusel Imagenes
        val myList = ArrayList<SlideModel>()
        myList.add(SlideModel(R.drawable.poi5_img1))
        myList.add(SlideModel(R.drawable.poi5_img2))
        myList.add(SlideModel(R.drawable.poi5_img3))
        myList.add(SlideModel(R.drawable.poi5_img4))
        myList.add(SlideModel(R.drawable.poi5_img5))

        val imageSlider = findViewById<ImageSlider>(R.id.slider5)
        imageSlider.setImageList(myList, ScaleTypes.CENTER_CROP)
    }
}