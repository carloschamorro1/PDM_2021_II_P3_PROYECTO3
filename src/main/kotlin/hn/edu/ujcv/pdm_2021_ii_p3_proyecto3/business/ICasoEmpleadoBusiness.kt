package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.CasoEmpleado


interface ICasoEmpleadoBusiness {
    fun getCasoEmpleado():List<CasoEmpleado>
    fun getCasoEmpleadoById(idCasoEmpleado:Long): CasoEmpleado
    fun saveCasoEmpleado(casoEmpleado: CasoEmpleado): CasoEmpleado
    fun removeCasoEmpleado(idCasoEmpleado:Long)
    fun updateCasoEmpleado(casoEmpleado: CasoEmpleado):CasoEmpleado
}