package Appdroid.ciclo4.datos

class InfoPoi {
    var titulo: String? = null
    var descripcion: String? = null
    var coordenadaslat:String?=null
    var coordenadaslong:String?=null

    constructor() : super()

    constructor(titulo: String, descripcion: String, coordenadaslat: String, coordenadaslong: String) {
        this.titulo = titulo
        this.descripcion = descripcion
        this.coordenadaslat = coordenadaslat
        this.coordenadaslong = coordenadaslong
    }

}
