package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.FacturaDetalle

interface IFacturaDetalleBusiness {
    fun getFacturaDetalle():List<FacturaDetalle>
    fun getFacturaDetalleById(idDetalle:Long): FacturaDetalle
    fun saveFacturaDetalle(facturaDetalle: FacturaDetalle): FacturaDetalle
    fun removeFacturaDetalle(idDetalle:Long)
    fun updateFacturaDetalle(facturaDetalle: FacturaDetalle): FacturaDetalle
}