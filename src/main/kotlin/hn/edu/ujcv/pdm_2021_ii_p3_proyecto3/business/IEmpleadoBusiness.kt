package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado

interface IEmpleadoBusiness {
    fun getEmpleado():List<Empleado>
    fun getEmpleadoById(id:Long):Empleado
    fun saveEmpleado(empleado: Empleado):Empleado
    fun removeEmpleado(idEmpleado:Long)
    fun getEmpleadoByNombre(nombreEmpleado: String):Empleado
    fun updateEmpleado(empleado: Empleado):Empleado
}