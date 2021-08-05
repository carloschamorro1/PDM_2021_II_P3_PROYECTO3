package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Juzgado
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface JuzgadoRepository: JpaRepository<Juzgado, Long> {
        fun findByNombre(nombreJuzgado:String): Optional<Juzgado>
}