package Appdroid.ciclo4

import Appdroid.ciclo4.archivojson.ControladorArchivoJson
import Appdroid.ciclo4.datos.InfoPoi
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import java.io.File
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    private var listaPoi = mutableListOf<InfoPoi>()
    private val nombreArchivo = "pois.txt"
    private val controlJson = ControladorArchivoJson()
    private var archivo: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        //Pantalla completa
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //Informacion archivo Json
        archivo = File(this.applicationContext.filesDir, nombreArchivo)
        if (archivo!!.exists()) {
            archivo!!.createNewFile()
        }

        listaPoi.addAll(
            arrayListOf(
                (InfoPoi(
                    "STATUE OF LIBERTY",
                    "Nueva York cuenta con una temperatura aproximada de 6 Cº, incluye 5 distritos que se ubican donde el río Hudson desemboca en el océano Atlántico. En su centro se encuentra Manhattan, un distrito densamente poblado que se encuentra entre los principales centros comerciales, financieros y culturales del mundo. Sus sitios icónicos incluyen rascacielos, como el Empire State Building, y el amplio Central Park. El teatro Broadway se ubica en la zona del Times Square iluminada con neones."
                )),
                (InfoPoi(
                    "MUSEUM MADAME TUSSAUNDS",
                    "El Museo Madame Tussauds es el museo de cera más conocido en el mundo. Posee la colección más grande de figuras de celebridades. La sede central del museo está en Londres, pero también hay establecimientos en otras grandes ciudades alrededor del mundo."
                )),
                (InfoPoi(
                    "ONE WORLD OBSERVATORY",
                    "Situado en la parte baja de Manhattan, el Observatorio One World presenta un mirador de última generación a 102 pisos de altura. Viaja directamente a su cima en 47 segundos en el ascensor SkyPod y profundiza en la historia cultural y financiera de la ciudad con una serie de exposiciones interactivas e interesantes charlas dirigidas por los embajadores del tour."
                )),
                (InfoPoi(
                    "911 MUSEUM",
                    "El centro está ubicado en el sitio del World Trade Center, en la misma ubicación de las Torres Gemelas destruidas en los ataques del 11 de septiembre de 2001. El World Trade Center Memorial Foundation cambió de nombre a National September 11 Memorial & Museum at the World Trade Center en 2007. El ganador del diseño para la competencia del World Trade Center Site Memorial fue el arquitecto israelí-estadounidense Michael Arad de Handel Architects, una firma de San Francisco y Nueva York. Arad trabajó con la firma de arquitectura paisajista Peter Walker and Partners en el diseño en el que lo llaman 'bosque de árboles' con dos piscinas gemelas en el centro, en los cimientos donde alguna vez estuvieron las Torres Gemelas."
                )),
                (InfoPoi(
                    "EDGE",
                    "Edge es la nueva y emocionante atracción turística de la ciudad de Nueva York, situada en lo alto del fabuloso y reluciente rascacielos Hudson Yards. Subirás 100 pisos en su moderno ascensor y, después, podrás salir al aire libre en su amplio mirador para deleitarte con las vistas del horizonte más famoso del mundo."
                ))
            )
        )
        controlJson.guardarJsonEnArchivo(archivo!!, listaPoi)


        //Funcion de RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        //Menu hamburguesa
        val toolbar: Toolbar? = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navView = findViewById<NavigationView>(R.id.navView)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_config -> startActivity(Intent(this, SettingsActivity::class.java))
                R.id.nav_item_one -> Toast.makeText(
                    applicationContext,
                    "Click Perfil",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_item_two -> Toast.makeText(
                    applicationContext,
                    "Click Soporte",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_item_three -> Toast.makeText(
                    applicationContext,
                    "Click Contactenos",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_item_four -> Toast.makeText(
                    applicationContext,
                    "Click Ayuda",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_item_five -> exitProcess(0)
            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}