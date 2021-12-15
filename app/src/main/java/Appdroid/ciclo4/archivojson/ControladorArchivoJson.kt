package Appdroid.ciclo4.archivojson

import android.widget.BaseExpandableListAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.File
import java.lang.reflect.Type
import Appdroid.ciclo4.datos.InfoPoi

class ControladorArchivoJson {

    public fun guardarJsonEnArchivo(archivo: File, listaPoi: List<InfoPoi>): Unit {
        val listaJson = converterListaPoiAJson(listaPoi)
        archivo.writeText(listaJson)
    }

    fun llamar(archivo: File, Indice: Int): ArrayList<String> {
        val controlJson = ControladorArchivoJson()
        val nombreArchivo = "pois.txt"
        if (archivo.exists()) {
            archivo.createNewFile()
        }
        val listarPoi = controlJson.recuperarJsonDeArchivo(archivo)
        val arrayDatos = arrayListOf<String>()
        arrayDatos.addAll(
            listOf(
                (listarPoi[Indice].titulo.toString()),
                (listarPoi[Indice].descripcion.toString())
            )
        )
        return arrayDatos
    }

    fun recuperarJsonDeArchivo(archivo: File): List<InfoPoi> {
        val br: BufferedReader = archivo.bufferedReader()
        val listaJson = br.use { it.readLine() }
        return reconstructJsonArrayAListInfoPoi(listaJson)
    }

    private fun converterAJson(info: InfoPoi): String {
        return Gson().toJson(info)
    }

    private fun converterListaPoiAJson(listaPoi: List<InfoPoi>): String {
        return Gson().toJson(listaPoi)
    }

    private fun reconstructJsonAPoi(infoJson: String): InfoPoi {
        return Gson().fromJson<InfoPoi>(infoJson, InfoPoi::class.java)
    }

    private fun reconstructJsonArrayAListInfoPoi(poiArrayJson: String): List<InfoPoi> {
        val tipo: Type = object : TypeToken<List<InfoPoi>>() {}.type
        return Gson().fromJson<List<InfoPoi>>(poiArrayJson, tipo)
    }

}

