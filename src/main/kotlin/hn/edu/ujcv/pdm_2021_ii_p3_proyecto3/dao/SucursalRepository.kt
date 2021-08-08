package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao


import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Sucursal
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SucursalRepository: JpaRepository<Sucursal, Long> {
    fun findByNombreSucursal(nombreSucursal:String): Optional<Sucursal>

}