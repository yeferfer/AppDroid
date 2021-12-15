package Appdroid.ciclo4.datos

class InfoPoi{
    var titulo : String? = null
    var descripcion : String? = null

    constructor(): super(){}

    constructor(titulo: String, descripcion:String){
        this.titulo =titulo
        this.descripcion = descripcion
    }

}
