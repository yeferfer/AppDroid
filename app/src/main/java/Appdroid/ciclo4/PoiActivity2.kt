package Appdroid.ciclo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PoiActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi2)
        val btninicio = findViewById<Button>(R.id.btnVolver2)
        btninicio.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }
        val btnMap = findViewById<Button>(R.id.button)
        btnMap.setOnClickListener {
            var latIntent = Intent(this@PoiActivity2,MapsActivity::class.java)
            latIntent.putExtra("lat", 40.7564)
            latIntent.putExtra("long", -73.9888)
            startActivity(latIntent)
            val gotoMap = Intent(this, MapsActivity::class.java)
            startActivity(gotoMap)
        }
    }
}