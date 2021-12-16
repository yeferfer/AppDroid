package Appdroid.ciclo4

import Appdroid.ciclo4.archivojson.ControladorArchivoJson
import Appdroid.ciclo4.datos.InfoPoi
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import java.io.File

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private var listaPoi = mutableListOf<InfoPoi>()
    val nombreArchivo = "pois.txt"
    private val controlJson = ControladorArchivoJson()
    private var archivo: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        //Pantalla completa
        this.getWindow().setFlags(
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

        /**** Para la implementacion de la barra de navegacion (Parte superior) y el menu deslizable desde la izquierda ****/

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.activity_main_drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item:MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.nav_config -> {
                startActivity(Intent( this, SettingsActivity::class.java))
                true
            }
            R.id.nav_item_one -> {
                Toast.makeText(this, "Clicked Perfil", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_item_two -> {
                Toast.makeText(this, "Clicked Soporte", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_item_three -> {
                Toast.makeText(this, "Clicked Contactenos", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_item_four -> {
                Toast.makeText(this, "Clicked Ayuda", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_item_five -> {
                System.exit(0)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**** Aca se configura la navegacion de los items del menú desplegable desde la izquierda ****/

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }


    /*  override fun onBackPressed() {
          if (drawer.isDrawerOpen(GravityCompat.START)) {
              drawer.closeDrawer(GravityCompat.START)
          } else {
              super.onBackPressed()
          }
      }*/

}