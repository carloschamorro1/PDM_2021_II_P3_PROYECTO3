package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model
//,
import javax.persistence.*

@Entity
@Table(name = "Sucursal", catalog = "dbo")
data class Sucursal( val nombreSucursal:String ="", val direccionSucursal:String="", val telefonoSucursal:Long=0,
val emailSucursal:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idSucursal:Long=0

}
