package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="Cliente")
data class Cliente(val nombre:String ="", val apellido:String="", val dni:String = "",
                   val rtn:String="",val telefono:String = "",
                    val direccion:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0
}