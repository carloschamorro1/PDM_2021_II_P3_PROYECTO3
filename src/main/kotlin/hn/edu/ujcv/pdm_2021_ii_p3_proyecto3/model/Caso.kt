package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="Caso", catalog = "dbo")
class Caso( val tipoCaso:String ="", val sentenciaCaso:String="", val idCliente:Long = 0,
            val idServicio:Long = 0, val estadoCaso: String = "") {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCaso:Long=0
}