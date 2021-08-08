package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.FacturaEncabezado

interface IFacturaEncabezadoBusiness {
    fun getFacturaEncabezado():List<FacturaEncabezado>
    fun getFacturaEncabezadoById(idFactura:Long): FacturaEncabezado
    fun saveFacturaEncabezado(facturaEncabezado: FacturaEncabezado): FacturaEncabezado
    fun removeFacturaEncabezado(idFactura:Long)
    fun updateFacturaEncabezado(facturaEncabezado: FacturaEncabezado): FacturaEncabezado
}