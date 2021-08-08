package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.CasoEmpleadoRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.EmpleadoRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.CasoEmpleado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class CasoEmpleadoBusiness:ICasoEmpleadoBusiness
{

    @Autowired
    val casoEmpleadoRepository: CasoEmpleadoRepository?=null

    @Throws(BusinessException::class)
    override fun getCasoEmpleado(): List<CasoEmpleado> {
        try{
            return casoEmpleadoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }


    @Throws(BusinessException::class, NotFoundException::class)
    override fun getCasoEmpleadoById(idCasoEmpleado: Long): CasoEmpleado {
        val opt: Optional<CasoEmpleado>
        try{
            opt = casoEmpleadoRepository!!.findById(idCasoEmpleado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el caso empleado $idCasoEmpleado")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveCasoEmpleado(casoEmpleado: CasoEmpleado): CasoEmpleado {
        try{
            validarEspacios(casoEmpleado)
            validarLongitud(casoEmpleado)

            return casoEmpleadoRepository!!.save(casoEmpleado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeCasoEmpleado(idCasoEmpleado: Long) {
        val opt: Optional<CasoEmpleado>
        try{
            opt = casoEmpleadoRepository!!.findById(idCasoEmpleado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el caso empleado $idCasoEmpleado")
        }
        else{
            try{
                casoEmpleadoRepository!!.deleteById(idCasoEmpleado)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun updateCasoEmpleado(casoEmpleado: CasoEmpleado): CasoEmpleado {
        val opt: Optional<CasoEmpleado>
        try{
            opt = casoEmpleadoRepository!!.findById(casoEmpleado.idCasoEmpleado)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el caso empleado ${casoEmpleado.idCasoEmpleado}")
        }
        else{
            try{
                validarEspacios(casoEmpleado)
                validarLongitud(casoEmpleado)
                return casoEmpleadoRepository!!.save(casoEmpleado)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
//VALIDACIONES
@Throws(BusinessException::class)
fun validarEspacios(casoEmpleado: CasoEmpleado){
    if(casoEmpleado.idCasoEmpleado.toString().isEmpty()){
        throw BusinessException("El id del caso empleado no debe estar vacío")
    }
    if(casoEmpleado.idEmpleado.toString().isEmpty()){
        throw BusinessException("El id del empleado no debe estar vacío")
    }
    if(casoEmpleado.idCaso.toString().isEmpty()){
        throw BusinessException("El id del caso no debe estar vacío")
    }
    if(casoEmpleado.fechaInicioTrabajoEnCaso.toString().isEmpty()){
        throw BusinessException("La fecha de inicio no debe estar vacía")
    }
    if(casoEmpleado.fechaFinalTrabajoEnCaso.toString().isEmpty()){
        throw BusinessException("La fecha final no debe estar vacío")
    }
    if(casoEmpleado.descripcionCasoEmpleado.isEmpty()){
        throw BusinessException("La descripcion no debe estar vacío")
    }

}

    /*
[idCasoEmpleado] [numeric](18, 0) NOT NULL,
[idEmpleado] [numeric](18, 0) NOT NULL,
[idCaso] [numeric](18, 0) NOT NULL,
[fechaInicioTrabajoEnCaso] [date] NOT NULL,
[fechaFinalTrabajoEnCaso] [date] NOT NULL,
[descripcionCasoEmpleado] [varchar](80) NOT NUL
*/
    @Throws(BusinessException::class)
    fun validarLongitud(casoEmpleado: CasoEmpleado){
        if(casoEmpleado.idCasoEmpleado.toString().length<8 ){
            throw BusinessException("El id del caso empleado no puede ser menor a 8 digitos")
        }
        if(casoEmpleado.idEmpleado.toString().length<8){
            throw BusinessException("El id del empleado no puede ser menor a 8 digitos")
        }
        if(casoEmpleado.idCaso.toString().length<8){
            throw BusinessException("El id del caso no puede ser menor a 8 dígitos")
        }
        if(casoEmpleado.fechaInicioTrabajoEnCaso.toString().length<6){
            throw BusinessException("La fecha de inicio no puede ser menor a 6 dígitos")
        }
        if(casoEmpleado.fechaFinalTrabajoEnCaso.toString().length<6){
            throw BusinessException("La fecha final no puede ser menor a 6 dígitos")
        }
        if(casoEmpleado.descripcionCasoEmpleado.length>100){
            throw BusinessException("La descripcion no puede ser mayor a 100 caracteres")
        }


    }
}
//