package Appdroid.ciclo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PoiActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi1)
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

