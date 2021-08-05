package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.AudienciaRepository

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Audiencia
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AudienciaBusiness: IAudienciaBusiness {
    @Autowired
    val audienciaRepository: AudienciaRepository?=null
    @Throws(BusinessException::class)
    override fun getAudiencia(): List<Audiencia> {
        try{
            return audienciaRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }


    @Throws(BusinessException::class, NotFoundException::class)
    override fun getAudienciaById(idCaso: Long): Audiencia{
        val opt: Optional<Audiencia>
        try{
            opt = audienciaRepository!!.findById(idCaso)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la audiencia $idCaso")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveAudiencia(audiencia: Audiencia): Audiencia {
        try{
            validarEspacios(audiencia)
            validarLongitud(audiencia)


            return audienciaRepository!!.save(audiencia)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeAudiencia(idCaso: Long) {
        val opt: Optional<Audiencia>
        try{
            opt = audienciaRepository!!.findById(idCaso)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró la audiencia $idCaso")
        }
        else{
            try{
                audienciaRepository!!.deleteById(idCaso)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun getAudienciaByidCaso(idCaso: Long): Audiencia {
        val opt: Optional<Audiencia>
        try{
            opt = audienciaRepository!!.findByidCaso(idCaso)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la audiencia$idCaso")
        }
        return opt.get()
    }

    override fun updateAudiencia(audiencia: Audiencia): Audiencia {
        val opt: Optional<Audiencia>
        try{
            opt = audienciaRepository!!.findByidCaso(audiencia.id)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la audiencia ${audiencia.id}")
        }
        else{
            try{
                return audienciaRepository!!.save(audiencia)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
     //VALIDACIONES
    @Throws(BusinessException::class)
    fun validarEspacios(audiencia: Audiencia){
        if(audiencia.idFechaAudiencia.toString().isEmpty()){
            throw BusinessException("El id de la fecha no debe estar vacío")
        }
        if(audiencia.idCaso.toString().isEmpty()){
            throw BusinessException("El id del caso no debe estar vacío")
        }
        if(audiencia.FechaAudiencia.toString().isEmpty()){
            throw BusinessException("La fecha no debe estar vacía")
        }
        if(audiencia.idJuzgado.toString().isEmpty()){
            throw BusinessException("El id del juzgado no debe estar vacío")
        }
        if(audiencia.descripcionAudiencia.isEmpty()){
            throw BusinessException("La descripcion no debe estar vacía")
        }

    }
    @Throws(BusinessException::class)
    fun validarLongitud(audiencia: Audiencia){
        if(audiencia.idFechaAudiencia.toString().length<8){
            throw BusinessException("La fecha no puede ser menor a 8 caracteres")
        }
        if(audiencia.idCaso.toString().length<8){
            throw BusinessException("El id del caso no puede ser menor a 8 caracteres")
        }
        if(audiencia.FechaAudiencia.toString().length<3){
            throw BusinessException("La fecha  no puede ser menor a 3 caracteres ")
        }
        if(audiencia.idJuzgado.toString().length<8){
            throw BusinessException("El id del juzgado no puede ser menor a 8 caracteres")
        }
        if(audiencia.descripcionAudiencia.length<3){
            throw BusinessException("La descripcion no puede ser menor a 3 caracteres")
        }

    }
}