package Appdroid.ciclo4.archivojson
import android.widget.BaseExpandableListAdapter
import com.google.gson.Gson
import com.google .gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.File

class ControladorArchivoJson {

    public fun guardarJsonEnArchivo(archivo:File,listaPoi:List<InfoPoi>):Unit{
        var listaJson = converterListaPoiAJson(listaPoi)
        archivo.writeText(listaJson)
    }

    public fun recuperarJsonDeArchivo(archivo:File):List<InfoPoi>{
        val br:BufferedReader=archivo.bufferedReader()
        return reconstructJsonArrayAListInfoPoi(listaJson)
    }

    private fun converterAJson(info:InfoPoi):String{
        return Gson().toJson(info)
    }

    private fun converterListaPoiAJson(listaPoi:List<InfoPoi>):String{
        return Gson().toJson(listaPoi)
    }

    private fun reconstructJsonAPoi(infoJson:String):InfoPoi{
        return Gson().fromJson<InfoPoi>(infoJson,InfoPoi:: class.java)
    }

    private fun reconstructJsonArrayAListInfoPoi(poiArrayJson:String):List<InfoPoi>{
        val tipo = object :TypeToken<List<InfoPoi>>(){ }.type
        return Gson().fromJson()<List<InfoPoi>>(poiArrayJson, tipo)
    }
}