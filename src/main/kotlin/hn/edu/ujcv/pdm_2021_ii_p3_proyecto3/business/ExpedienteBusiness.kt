package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.ExpedienteRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Expediente
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class ExpedienteBusiness:IExpedienteBusiness {

    @Autowired
    val expedienteRepository: ExpedienteRepository? =null

    @Throws(BusinessException::class)
    override fun getExpediente(): List<Expediente> {
        try{
            return expedienteRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getExpedienteById(idExpediente: Long): Expediente {
        val opt: Optional<Expediente>
        try{
            opt = expedienteRepository!!.findById(idExpediente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el Expediente $idExpediente")
        }
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveExpediente(expediente: Expediente): Expediente {
        try{
            validarEspacios(expediente)
            validarLongitud(expediente)
            validarLongitudMaxima(expediente)
            return expedienteRepository!!.save(expediente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeExpediente(idExpediente: Long) {
        val opt: Optional<Expediente>
        try{
            opt = expedienteRepository!!.findById(idExpediente)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el Expediente $idExpediente")
        }
        else{
            try{
                expedienteRepository!!.deleteById(idExpediente)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateExpediente(expediente: Expediente): Expediente {
        val opt: Optional<Expediente>
        try{
            opt = expedienteRepository!!.findById(expediente.idExpediente)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el cliente ${expediente.idExpediente}")
        }
        else{
            try{
                validarEspacios(expediente)
                validarLongitud(expediente)
                validarLongitudMaxima(expediente)
                return expedienteRepository!!.save(expediente)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    fun validarEspacios(expediente: Expediente){
        if(expediente.Entidad.isEmpty()){
            throw BusinessException("El nombre no debe estar vacío")
        }
        if(expediente.numExpediente.isEmpty()){
            throw BusinessException("El apellido no debe estar vacío")
        }
        if(expediente.idCaso.toString().isEmpty()){
            throw BusinessException("El dni no debe estar vacío")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitud(expediente: Expediente){
        if(expediente.Entidad.length < 5){
            throw BusinessException("El nombre de la entidad no puede ser menor a 5 caracteres")
        }
        if(expediente.numExpediente.length < 8){
            throw BusinessException("El número de expediente no puede ser menor a 8 caracteres")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitudMaxima(expediente: Expediente){
        if(expediente.Entidad.length < 50){
            throw BusinessException("El nombre de la entidad no puede ser mayor a 50 caracteres")
        }
        if(expediente.numExpediente.length < 30){
            throw BusinessException("El número de expediente no puede ser mayor a 30 caracteres")
        }
    }

}