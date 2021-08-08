package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name="FacturaDetalle", catalog = "dbo")
data class FacturaDetalle(val idFactura:Long = 0, val idServicio:Long = 0, val cantidadFactura:String="") {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idDetalle:Long = 0
}