package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Caso


interface ICasoBusiness {
    fun getCaso():List<Caso>
    fun getCasoById(idCaso:Long): Caso
    fun saveCaso(caso: Caso): Caso
    fun removeCaso(idCaso:Long)
    fun getCasoByIdCliente(idCliente: Long): Caso
    fun updateCaso(caso: Caso): Caso
}