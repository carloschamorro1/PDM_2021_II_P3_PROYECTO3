package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model
import java.util.*
import javax.persistence.*

@Entity//m
@Table(name="Cobro", catalog = "dbo")
class Cobro (val fechaEmisionFactura: Date, val idCAI:Long=0, val idSucursal:Long=0, val totalFactura:Float= 0F,
             val idEmpleado:Long=0, val idCaso:Long=0, val idDetalle:Long=0, val idServicio:Long=0, val cantidadFactura:Long=0){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idFactura:Long=0
}
/*
factura encabezado
[fechaEmisionFactura] [date] NOT NULL,
	[idCAI] [numeric](18, 0) NOT NULL,
	[idSucursal] [numeric](18, 0) NOT NULL,
	[totalFactura] [money] NOT NULL,
	[idEmpleado] [numeric](18, 0) NOT NULL,
	[idCaso] [numeric](18, 0) NOT NULL,

factura detalle
[idDetalle] [numeric](18, 0) NOT NULL,
	[idFactura] [numeric](18, 0) NOT NULL,
	[idServicio] [numeric](18, 0) NOT NULL,
	[cantidadFactura] [char](2) NOT NULL,
 */