package Appdroid.ciclo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PoiActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi4)
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