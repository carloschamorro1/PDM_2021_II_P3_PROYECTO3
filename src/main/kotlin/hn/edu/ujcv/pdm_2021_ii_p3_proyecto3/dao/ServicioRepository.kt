package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Juzgado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Servicio
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ServicioRepository : JpaRepository<Servicio, Long> {
        fun findByNombre(nombreServicio:String): Optional<Servicio>
    }