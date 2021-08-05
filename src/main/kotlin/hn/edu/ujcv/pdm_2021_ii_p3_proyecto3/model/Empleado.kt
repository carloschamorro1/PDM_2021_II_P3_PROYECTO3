package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="Empleado", catalog = "dbo")
data class Empleado(val nombreEmpleado:String ="", val apellidoEmpleado:String="", val dniEmpleado:Long = 0, val telefonoEmpleado:Long = 0,
                    val salarioEmpleado:Double = 0.0, val tipoEmpleado:String="", val nombreUsuario:String="", val contraseñaEmpleado:String = "") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idEmpleado:Long=0
}