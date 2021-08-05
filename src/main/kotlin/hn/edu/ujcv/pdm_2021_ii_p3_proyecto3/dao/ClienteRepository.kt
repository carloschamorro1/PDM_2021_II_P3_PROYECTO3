package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Cliente
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClienteRepository: JpaRepository<Cliente,Long> {
    fun findByNombreCliente(nombreCliente:String): Optional<Cliente>
}