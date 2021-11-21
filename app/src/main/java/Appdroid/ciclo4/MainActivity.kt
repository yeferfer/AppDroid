package Appdroid.ciclo4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonPoi1 = findViewById<Button>(R.id.btnPoi1)
        val buttonPoi2 = findViewById<Button>(R.id.btnPoi2)
        val buttonPoi3 = findViewById<Button>(R.id.btnPoi3)
        val buttonPoi4 = findViewById<Button>(R.id.btnPoi4)
        val buttonPoi5 = findViewById<Button>(R.id.btnPoi5)


        buttonPoi1.setOnClickListener {
            val lanzar1 = Intent(this, PoiActivity1::class.java)
            startActivity(lanzar1)
        }

        buttonPoi2.setOnClickListener {
            val lanzar2 = Intent(this, PoiActivity2::class.java)
            startActivity(lanzar2)
        }


        buttonPoi3.setOnClickListener {
            val lanzar3 = Intent(this, PoiActivity3::class.java)
            startActivity(lanzar3)
        }


        buttonPoi4.setOnClickListener {
            val lanzar4 = Intent(this, PoiActivity4::class.java)
            startActivity(lanzar4)
        }


        buttonPoi5.setOnClickListener {
            val lanzar5 = Intent(this, PoiActivity5::class.java)
            startActivity(lanzar5)
        }
    }
}