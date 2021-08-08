package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="PrecioHistorico", catalog ="dbo")
data class PrecioHistorico(val fechaInicialPrecioHistorico:String="", val fechaFinalPrecioHistorico:String="", val idServicio:Long = 0,
                            val precio:Double=0.0) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idPrecioHistorico:Long = 0
}