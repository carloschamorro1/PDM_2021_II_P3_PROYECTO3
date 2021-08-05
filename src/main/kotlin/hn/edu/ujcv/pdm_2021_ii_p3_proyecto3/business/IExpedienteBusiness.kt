package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Expediente

interface IExpedienteBusiness {
    fun getExpediente():List<Expediente>
    fun getExpedienteById(idExpediente:Long): Expediente
    fun saveExpediente(expediente: Expediente): Expediente
    fun removeExpediente(idExpediente:Long)
    fun updateExpediente(expediente: Expediente): Expediente
}