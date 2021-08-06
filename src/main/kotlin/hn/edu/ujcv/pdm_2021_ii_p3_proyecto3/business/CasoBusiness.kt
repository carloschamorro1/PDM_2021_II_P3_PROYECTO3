package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.CasoRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Caso
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CasoBusiness: ICasoBusiness{
    @Autowired
    val casoRepository: CasoRepository?=null
    @Throws(BusinessException::class)
    override fun getCaso(): List<Caso> {
        try{
            return casoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getCasoById(idCaso: Long): Caso {
        val opt: Optional<Caso>
        try{
            opt = casoRepository!!.findById(idCaso)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el caso $idCaso")
        }
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveCaso(caso: Caso): Caso {
        try{
            validarEspacios(caso)
            validarLongitud(caso)

            return casoRepository!!.save(caso)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeCaso(idCaso: Long) {
        val opt: Optional<Caso>
        try{
            opt = casoRepository!!.findById(idCaso)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el caso $idCaso")
        }
        else{
            try{
                casoRepository!!.deleteById(idCaso)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }
    override fun getCasoByIdCliente(idCliente: Long): Caso {
        val opt: Optional<Caso>
        try{
            opt = casoRepository!!.findByIdCliente(idCliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el caso $idCliente")
        }
        return opt.get()
    }
    override fun updateCaso(caso: Caso): Caso {
        val opt: Optional<Caso>
        try{
            opt = casoRepository!!.findById(caso.idCaso)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el caso ${caso.idCaso}")
        }
        else{
            try{
                validarEspacios(caso)
                validarLongitud(caso)
                return casoRepository!!.save(caso)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    // VALIDACIONES
    @Throws(BusinessException::class)
    fun validarEspacios(caso: Caso){
        if(caso.idCaso.toString().isEmpty()){
            throw BusinessException("El id del caso no debe estar vacío")
        }
        if(caso.tipoCaso.isEmpty()){
            throw BusinessException("El tipo de caso no debe estar vacío")
        }
        if(caso.sentenciaCaso.isEmpty()){
            throw BusinessException("La sentencia no debe estar vacío")
        }
        if(caso.idCliente.toString().isEmpty()){
            throw BusinessException("El id del cliente no debe estar vacío")
        }
        if(caso.idServicio.toString().isEmpty()){
            throw BusinessException("El id del servicio no debe estar vacío")
        }
        if(caso.estadoCaso.isEmpty()){
            throw BusinessException("El estado del caso no debe estar vacío")
        }

    }
    @Throws(BusinessException::class)
    fun validarLongitud(caso: Caso){
        if(caso.idCaso.toString().length<8){
            throw BusinessException("El id del caso no puede ser menor a 8 caracteres")
        }
        if(caso.tipoCaso.length<10){
            throw BusinessException("El tipo de caso no puede ser menor a 10 caracteres")
        }
        if(caso.sentenciaCaso.length<10){
            throw BusinessException("La sentencia no puede ser menor a 10 caracteres")
        }
        if(caso.idCliente.toString().length<8){
            throw BusinessException("El id del cliente no puede ser menor a 8 dígitos")
        }
        if(caso.idServicio.toString().length<8){
            throw BusinessException("El id del servicio no puede ser menor a 8 dígitos")
        }

        if(caso.estadoCaso.length<10){
            throw BusinessException("El estado del caso no puede ser menor a 10 caracteres")
        }


    }


}