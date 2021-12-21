package Appdroid.ciclo4

import Appdroid.ciclo4.archivojson.ControladorArchivoJson
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.File
import java.lang.IndexOutOfBoundsException


internal class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var lat: Double? = null
    private var long: Double? = null
    private var indice: Int? = null

    //Archivos Json
    private val controlJson = ControladorArchivoJson()
    private var archivo: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        //Pantalla completa
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapView) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        //Volver
        val btnVolverMaps = findViewById<LottieAnimationView>(R.id.btnVolverMaps)
        btnVolverMaps.setOnClickListener {
            val regresa = Intent(this, MainActivity::class.java)
            startActivity(regresa)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        val intent = intent
        indice = intent.getIntExtra("indice", 8)
        //Documento Json
        archivo = File(this.applicationContext.filesDir, "pois.txt")
        val datos = controlJson.llamar(archivo!!, indice!!)
        val latJson: Double = datos[2].toDouble()
        val longJson: Double = datos[3].toDouble()
        lat = intent.getDoubleExtra("lat", latJson)
        long = intent.getDoubleExtra("long", longJson)

        mMap = googleMap

        //Mapa Satelital
        val animationView1 = findViewById<LottieAnimationView>(R.id.animationView1)
        animationView1.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }

        //Mapa Normal
        val animationView2 = findViewById<LottieAnimationView>(R.id.animationView2)
        animationView2.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }

        // Add a marker and move the camera
        val marker = LatLng(lat!!, long!!)
        mMap.addMarker(
            MarkerOptions()
                .position(marker)
                .title("Marker")
        )

        val cameraPosition = CameraPosition.Builder()
            .target(marker)
            .zoom(17f)
            .bearing(90f)
            .tilt(30f)
            .build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

}

      