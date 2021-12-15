package Appdroid.ciclo4

import Appdroid.ciclo4.archivojson.ControladorArchivoJson
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.File

class PoiActivity4 : AppCompatActivity() {

    private val controlJson = ControladorArchivoJson()
    private var archivo: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi4)

        //Documento Json
        archivo = File(this.applicationContext.filesDir, "pois.txt")
        val titulo = findViewById<TextView>(R.id.monumento)
        val descripcion = findViewById<TextView>(R.id.Monumento_description)
        val datos = controlJson.llamar(archivo!!,3)
        titulo.text=datos[0]
        descripcion.text=datos[1]

        val btninicio = findViewById<Button>(R.id.btnVolver4)
        btninicio.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }
        val btnMap = findViewById<Button>(R.id.button)
        btnMap.setOnClickListener {
            var latIntent = Intent(this@PoiActivity4,MapsActivity::class.java)
            latIntent.putExtra("lat", 40.7113)
            latIntent.putExtra("long", -74.0132)
            startActivity(latIntent)
            val gotoMap = Intent(this, MapsActivity::class.java)
            startActivity(gotoMap)
        }
    }
}