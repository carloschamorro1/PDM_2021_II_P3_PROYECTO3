package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name = "Cliente", catalog = "dbo")
data class Cliente(val nombreCliente:String ="", val apellidoCliente:String="", val dniCliente:String = "",
                   val rtnCliente:String="",val telefonoCliente:String = "",
                    val direccionCliente:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCliente:Long=0
}