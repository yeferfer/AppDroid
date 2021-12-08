package Appdroid.ciclo4

import Appdroid.ciclo4.datos.infoPoi
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val poi1 = infoPoi("STATUE OF LIBERTY","Lola")
        val poi2 = infoPoi("MUSEUM MADAME TUSSAUNDS","Lole")
        val poi3 = infoPoi("ONE WORLD OBSERVATORY","Loli")
        val poi4 = infoPoi("911 MUSEUM","Lolo")
        val poi5 = infoPoi("EDGE","Lolu")

        var jsonPoi1 = convertirAJson(poi1)
        var jsonPoi2 = convertirAJson(poi2)
        var jsonPoi3 = convertirAJson(poi3)
        var jsonPoi4 = convertirAJson(poi4)
        var jsonPoi5 = convertirAJson(poi5)

        var rJsonPoi1 = reconstructJsonInformacionPoi(jsonPoi1)
        rJsonPoi1.descripcion="En los lugares"

        println("descripcion: ${rJsonPoi1.descripcion}\nTitulo: ${rJsonPoi1.titulo}")

        println("${jsonPoi1}\n${jsonPoi2}\n${jsonPoi3}\n${jsonPoi4}\n${jsonPoi5}\n")


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

    private fun convertirAJson(informacion: infoPoi):String{
        return Gson().toJson(informacion)
    }

    private fun reconstructJsonInformacionPoi(informacionJson: String): infoPoi {
        return Gson().fromJson(informacionJson, infoPoi::class.java)
    }

    /**** Aca se configura la navegacion de los items del menú desplegable desde la izquierda ****/
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_item_one -> Toast.makeText(this,"Pues ya nos fuimos Carnal!", Toast.LENGTH_LONG).show()
            R.id.nav_item_one -> Toast.makeText(applicationContext,"Andale!", Toast.LENGTH_LONG).show()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}