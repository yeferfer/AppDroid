package Appdroid.ciclo4

import Appdroid.ciclo4.archivojson.ControladorArchivoJson
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.File


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

    }

    override fun onMapReady(googleMap: GoogleMap) {
        val intent = intent
        indice = intent.getIntExtra("inice", 8)
        //Documento Json
        archivo = File(this.applicationContext.filesDir, "pois.txt")
        val datos = controlJson.llamar(archivo!!, indice!!)
        val latJson: Double = datos[2].toDouble()
        val longJson: Double = datos[3].toDouble()
        lat = intent.getDoubleExtra("lat", latJson)
        long = intent.getDoubleExtra("long", longJson)

        mMap = googleMap

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

      