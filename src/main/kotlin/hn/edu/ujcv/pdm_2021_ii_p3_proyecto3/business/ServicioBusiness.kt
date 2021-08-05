package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.ServicioRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Juzgado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Servicio
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class ServicioBusiness: IServicioBusiness {
    @Autowired
    val servicioRepository: ServicioRepository? = null

    @Throws(BusinessException::class)
    override fun getServicio(): List<Servicio> {
        try {
            return servicioRepository!!.findAll()
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getServicioById(idServicio: Long): Servicio {
        val opt: Optional<Servicio>
        try{
            opt = servicioRepository!!.findById(idServicio)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el Servicio $idServicio")
        }
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveServicio(servicio: Servicio): Servicio {
        try{
            validarEspacios(servicio)
            validarLongitud(servicio)

            return servicioRepository!!.save(servicio)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeServicio(idServicio: Long) {
        val opt: Optional<Servicio>
        try{
            opt = servicioRepository!!.findById(idServicio)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el Servicio : $idServicio")
        }
        else{
            try{
                servicioRepository!!.deleteById(idServicio)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun getServicioByNombre(nombreServicio: String): Servicio {
        val opt: Optional<Servicio>
        try{
            opt = servicioRepository!!.findByNombre(nombreServicio)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el Servicio $nombreServicio")
        }
        return opt.get()
    }

    override fun updateSercicio(servicio: Servicio): Servicio {
        val opt: Optional<Servicio>
        try{
            opt = servicioRepository!!.findById(servicio.id)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el Servicio: ${servicio.id}")
        }
        else{
            try{
                return servicioRepository!!.save(servicio)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }


    // Comienzan las validaciones
    @Throws(BusinessException::class)
    fun validarEspacios(servicio: Servicio){
        if(servicio.nombre.isEmpty()){
            throw BusinessException("El nombre del servicio no debe estar vacío")
        }
        if(servicio.descripcion.isEmpty()){
            throw BusinessException("La descripcion no debe estar vacío")
        }

    }

    @Throws(BusinessException::class)
    fun validarLongitud(servicio: Servicio) {
        if (servicio.nombre.length < 4) {
            throw BusinessException("El nombre no puede ser menor a 4 caracteres")
        }
        if (servicio.descripcion.length < 10) {
            throw BusinessException("La descripcion no puede ser menor a 10 caracteres")
        }
    }

}