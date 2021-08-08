package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business


import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Sucursal

interface ISucursalBusiness {
    fun getSucursal():List<Sucursal>
    fun getSucursalById(idSucursal:Long): Sucursal
    fun saveSucursal(sucursal: Sucursal): Sucursal
    fun removeSucursal(idSucursal:Long)
    fun getSucursalByNombre(nombreSucursal: String): Sucursal
    fun updateSucursal(sucursal: Sucursal): Sucursal
}
