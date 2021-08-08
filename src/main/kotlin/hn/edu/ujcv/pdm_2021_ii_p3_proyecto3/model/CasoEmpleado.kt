package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name="CasoEmpleado", catalog = "dbo")
class CasoEmpleado (val idEmpleado:Long=0, val idCaso:Long=0, val fechaInicioTrabajoEnCaso:Date, val fechaFinalTrabajoEnCaso:Date,
                    val descripcionCasoEmpleado:String=""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCasoEmpleado:Long=0
}//
/*
[idCasoEmpleado] [numeric](18, 0) NOT NULL,
	[idEmpleado] [numeric](18, 0) NOT NULL,
	[idCaso] [numeric](18, 0) NOT NULL,
	[fechaInicioTrabajoEnCaso] [date] NOT NULL,
	[fechaFinalTrabajoEnCaso] [date] NOT NULL,
	[descripcionCasoEmpleado] [varchar](80) NOT NUL
 */