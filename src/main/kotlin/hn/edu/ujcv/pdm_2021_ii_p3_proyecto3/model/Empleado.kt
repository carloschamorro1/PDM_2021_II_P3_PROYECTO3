package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="Empleado")
data class Empleado(val nombre:String ="", val apellido:String="", val dni:Long = 0, val telefono:Long = 0,
                    val salario:Double = 0.0, val tipoEmpleado:String="", val nombreUsuario:String="", val contraseña:String = "") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0
}