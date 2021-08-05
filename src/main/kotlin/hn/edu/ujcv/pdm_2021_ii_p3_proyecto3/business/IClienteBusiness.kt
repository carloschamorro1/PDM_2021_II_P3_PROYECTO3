package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Cliente

interface IClienteBusiness {
    fun getCliente():List<Cliente>
    fun getClienteById(idCliente:Long): Cliente
    fun saveCliente(cliente: Cliente): Cliente
    fun removeCliente(idCliente:Long)
    fun getClienteByNombre(nombreCliente: String): Cliente
    fun updateCliente(cliente: Cliente): Cliente
}