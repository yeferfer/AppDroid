package Appdroid.ciclo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class PoiActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poi1)
        val btninicio = findViewById<Button>(R.id.btnVolver1)
        btninicio.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }
    }
}

