package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class Sucursal( nombreSucursal:String ="", val direccionSucursal:String="", val telefonoSucursal:Long=0,
val emailSucursal:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idSucursal:Long=0

}
/*[idSucursal] [numeric](18, 0) NOT NULL,
[nombreSucursal] [varchar](30) NOT NULL,
[direccionSucursal] [varchar](60) NOT NULL,
[telefonoSucursal] [char](8) NOT NULL,
[emailSucursal] [varchar](30) */
