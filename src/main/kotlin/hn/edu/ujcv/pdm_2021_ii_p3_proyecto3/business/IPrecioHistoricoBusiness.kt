package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.PrecioHistorico

interface IPrecioHistoricoBusiness {
    fun getPrecioHistorico():List<PrecioHistorico>
    fun getPrecioHistoricoById(idPrecioHistorico:Long): PrecioHistorico
    fun savePrecioHistorico(precioHistorico: PrecioHistorico): PrecioHistorico
    fun removePrecioHistorico(idPrecioHistorico:Long)
    fun updatePrecioHistorico(precioHistorico: PrecioHistorico): PrecioHistorico
}