package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model
import java.util.*
import javax.persistence.*

@Entity
@Table(name="Audiencia", catalog = "dbo")
data class Audiencia(val idFechaAudiencia:Long = 0 , val FechaAudiencia:Date,
                     val idJuzgado:Long = 0, val descripcionAudiencia:String= ""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCaso:Long=0
}

