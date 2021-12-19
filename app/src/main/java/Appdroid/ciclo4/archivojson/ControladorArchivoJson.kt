package Appdroid.ciclo4.archivojson

import Appdroid.ciclo4.datos.InfoPoi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.File
import java.lang.reflect.Type

class ControladorArchivoJson {

    fun guardarJsonEnArchivo(archivo: File, listaPoi: List<InfoPoi>) {
        val listaJson = converterListaPoiAJson(listaPoi)
        archivo.writeText(listaJson)
    }

    fun llamar(archivo: File, Indice: Int): ArrayList<String> {
        val controlJson = ControladorArchivoJson()
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

    private fun recuperarJsonDeArchivo(archivo: File): List<InfoPoi> {
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
        return Gson().fromJson(infoJson, InfoPoi::class.java)
    }

    private fun reconstructJsonArrayAListInfoPoi(poiArrayJson: String): List<InfoPoi> {
        val tipo: Type = object : TypeToken<List<InfoPoi>>() {}.type
        return Gson().fromJson(poiArrayJson, tipo)
    }

}

