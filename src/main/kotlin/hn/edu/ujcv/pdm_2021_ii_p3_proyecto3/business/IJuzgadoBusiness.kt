package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Juzgado

interface IJuzgadoBusiness {
    fun getJuzgado():List<Juzgado>
    fun getJuzgadoById(id:Long): Juzgado
    fun saveJuzgado(juzgado: Juzgado): Juzgado
    fun removeJuzgado(idJuzgado: Long)
    fun getJuzgadoByNombre(nombreJuzgado: String): Juzgado
    fun updateJuzgado(juzgado: Juzgado): Juzgado
}