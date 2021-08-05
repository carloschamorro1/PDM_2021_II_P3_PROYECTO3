package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.EmpleadoRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.JuzgadoRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Juzgado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class JuzgadoBusiness:IJuzgadoBusiness {
    @Autowired
    val juzgadoRepository: JuzgadoRepository? = null

    @Throws(BusinessException::class)
    override fun getJuzgado(): List<Juzgado> {
        try {
            return juzgadoRepository!!.findAll()
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getJuzgadoById(idJuzgado: Long): Juzgado {
        val opt: Optional<Juzgado>
        try{
            opt = juzgadoRepository!!.findById(idJuzgado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el Juzgado $idJuzgado")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveJuzgado(juzgado: Juzgado): Juzgado {
        try{
            validarEspacios(juzgado)
            validarLongitud(juzgado)

            return juzgadoRepository!!.save(juzgado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeJuzgado(idJuzgado: Long) {
        val opt: Optional<Juzgado>
        try{
            opt = juzgadoRepository!!.findById(idJuzgado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el Juzgado : $idJuzgado")
        }
        else{
            try{
                juzgadoRepository!!.deleteById(idJuzgado)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun getJuzgadoByNombre(nombreJuzgado: String): Juzgado {
        val opt: Optional<Juzgado>
        try{
            opt = juzgadoRepository!!.findByNombre(nombreJuzgado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el Juzgado $nombreJuzgado")
        }
        return opt.get()
    }

    override fun updateJuzgado(juzgado: Juzgado): Juzgado {
        val opt: Optional<Juzgado>
        try{
            opt = juzgadoRepository!!.findById(juzgado.id)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el Juzgado ${juzgado.id}")
        }
        else{
            try{
                return juzgadoRepository!!.save(juzgado)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }


    // Comienzan las validaciones
    @Throws(BusinessException::class)
    fun validarEspacios(juzgado: Juzgado){
        if(juzgado.nombre.isEmpty()){
            throw BusinessException("El nombre del juzgado no debe estar vacío")
        }
        if(juzgado.dirreccion.isEmpty()){
            throw BusinessException("La dirección no debe estar vacío")
        }

    }

    @Throws(BusinessException::class)
    fun validarLongitud(juzgado: Juzgado) {
        if (juzgado.nombre.length < 4) {
            throw BusinessException("El nombre no puede ser menor a 4 caracteres")
        }
        if (juzgado.dirreccion.length < 10) {
            throw BusinessException("La direccion no puede ser menor a 10 caracteres")
        }
    }


}