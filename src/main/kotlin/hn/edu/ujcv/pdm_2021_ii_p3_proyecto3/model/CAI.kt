package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model
import java.util.*
import javax.persistence.*

@Entity
@Table(name="CAI")
class CAI (val idCAI: Long = 0, val CAI:Long = 0, val rangoInicial:Long = 0, val rangoFinal:Long = 0,val FechaLimite: Date)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0
}