package Appdroid.ciclo4

import Appdroid.ciclo4.Model.PoiModelClass
import Appdroid.ciclo4.archivojson.ControladorArchivoJson
import Appdroid.ciclo4.datos.InfoPoi
import android.content.Intent
import android.media.audiofx.AudioEffect
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
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    //Archivos Json Local
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

        //Archivo Json
        archivo = File(this.applicationContext.filesDir, nombreArchivo)
        if (archivo!!.exists()) {
            archivo!!.createNewFile()
        }
        val usersList: ArrayList<PoiModelClass> = ArrayList()
        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val usersArray = obj.getJSONArray("poi")

            for (i in 0 until usersArray.length()) {
                val user = usersArray.getJSONObject(i)
                val id = user.getInt("id")
                val titulo = user.getString("titulo")
                val Descricion = user.getString("Descricion")
                val userDetails = PoiModelClass(id, titulo, Descricion)
                listaPoi.add(
                    InfoPoi(titulo, Descricion)
                )
                usersList.add(userDetails)
                controlJson.guardarJsonEnArchivo(archivo!!, listaPoi)
            }

        } catch (e: JSONException) {
            //exception
            println("Esta en error")
            e.printStackTrace()
        }

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

    //ArchivoJson
    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = assets.open("infopoi.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    //Menu Hamburguesa
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}


