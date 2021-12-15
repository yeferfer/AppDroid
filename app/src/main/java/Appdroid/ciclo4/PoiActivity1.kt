package Appdroid.ciclo4

import Appdroid.ciclo4.archivojson.ControladorArchivoJson
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File


class PoiActivity1 : AppCompatActivity() {

    private val controlJson = ControladorArchivoJson()
    private var archivo: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi1)

        archivo = File(this.applicationContext.filesDir, "pois.txt")

        val titulo = findViewById<TextView>(R.id.liberty)
        val descripcion = findViewById<TextView>(R.id.estatua_description)
        val datos = controlJson.llamar(archivo!!,0)
        titulo.text=datos[0]
        descripcion.text=datos[1]


        val btninicio = findViewById<Button>(R.id.btnVolver1)
        btninicio.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }
        val btnMap = findViewById<Button>(R.id.button)
        btnMap.setOnClickListener {
            var latIntent = Intent(this@PoiActivity1,MapsActivity::class.java)
            latIntent.putExtra("lat", 40.6892)
            latIntent.putExtra("long", -74.0445)
            startActivity(latIntent)
            val gotoMap = Intent(this, MapsActivity::class.java)
            startActivity(gotoMap)
        }
    }
}

