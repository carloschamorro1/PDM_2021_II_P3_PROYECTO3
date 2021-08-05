package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name = "Expediente")
data class Expediente(val Entidad:String="", val numExpediente:String="", val idCaso:Long) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idExpediente:Long=0
}