package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Audiencia
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AudienciaRepository: JpaRepository<Audiencia, Long> {
    fun findByidCaso(idCaso:Long): Optional<Audiencia>
}