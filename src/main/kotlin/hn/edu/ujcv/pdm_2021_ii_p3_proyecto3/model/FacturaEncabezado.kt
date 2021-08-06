package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import javax.persistence.*

@Entity
@Table(name = "FacturaEncabezado", catalog = "dbo")
data class FacturaEncabezado(val fechaEmisionFactura: String, val idCai:Long, val idSucursal:Long,
val totalFactura:Double, val idEmpleado:Long, val idCaso:Long) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idFactura:Long=0
}