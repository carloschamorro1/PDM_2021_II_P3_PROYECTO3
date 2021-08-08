package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Cobro


interface ICobroBusiness {
    fun getCobro():List<Cobro>
    fun getCobroById(idFactura:Long): Cobro
    fun saveCobro(cobro: Cobro): Cobro
    fun removeCobro(idFactura:Long)
    fun updateCobro(cobro: Cobro): Cobro
}