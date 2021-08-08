package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Cobro
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CobroRepository : JpaRepository<Cobro, Long> {
    fun findByIdFactura(idFactura:Long): Optional<Cobro>
}