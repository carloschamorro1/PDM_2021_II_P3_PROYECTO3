package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.PrecioHistoricoRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Cliente
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.PrecioHistorico
import org.springframework.beans.factory.annotation.Autowired
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class PrecioHistoricoBusiness:IPrecioHistoricoBusiness {

    @Autowired
    val precioHistoricoRepository: PrecioHistoricoRepository?= null

    @Throws(BusinessException::class)
    override fun getPrecioHistorico(): List<PrecioHistorico> {
        try{
           return precioHistoricoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getPrecioHistoricoById(idPrecioHistorico: Long): PrecioHistorico {
        val opt: Optional<PrecioHistorico>
        try{
            opt = precioHistoricoRepository!!.findById(idPrecioHistorico)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el id del precio historico: $idPrecioHistorico")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun savePrecioHistorico(precioHistorico: PrecioHistorico): PrecioHistorico {
        try{
            validarEspacios(precioHistorico)
            validarLongitud(precioHistorico)
            validarLongitudMaxima(precioHistorico)
            validarFecha(precioHistorico.fechaInicialPrecioHistorico)
            validarFecha(precioHistorico.fechaFinalPrecioHistorico)
            return precioHistoricoRepository!!.save(precioHistorico)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removePrecioHistorico(idPrecioHistorico: Long) {
        val opt: Optional<PrecioHistorico>
        try{
            opt = precioHistoricoRepository!!.findById(idPrecioHistorico)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el id del precio historico $idPrecioHistorico")
        }
        else{
            try{
                precioHistoricoRepository!!.deleteById(idPrecioHistorico)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun updatePrecioHistorico(precioHistorico: PrecioHistorico): PrecioHistorico {
        val opt: Optional<PrecioHistorico>
        try{
            opt = precioHistoricoRepository!!.findById(precioHistorico.idPrecioHistorico)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el id del precio historico ${precioHistorico.idPrecioHistorico}")
        }
        else{
            try{
                validarEspacios(precioHistorico)
                validarLongitud(precioHistorico)
                validarLongitudMaxima(precioHistorico)
                validarFecha(precioHistorico.fechaInicialPrecioHistorico)
                validarFecha(precioHistorico.fechaFinalPrecioHistorico)
                return precioHistoricoRepository!!.save(precioHistorico)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    // Comienzan las validaciones
    @Throws(BusinessException::class)
    fun validarEspacios(precioHistorico: PrecioHistorico){
        if(precioHistorico.fechaInicialPrecioHistorico.isEmpty()){
            throw BusinessException("La fecha inicial no debe estar vacía")
        }
        if(precioHistorico.fechaFinalPrecioHistorico.isEmpty()){
            throw BusinessException("La fecha final no debe estar vacía")
        }
        if(precioHistorico.idServicio.toString().isEmpty()){
            throw BusinessException("El ID del servicio no debe estar vacío")
        }
        if(precioHistorico.precio.toString().isEmpty()){
            throw BusinessException("El precio no debe estar vacío")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitud(precioHistorico: PrecioHistorico){
        if(precioHistorico.fechaInicialPrecioHistorico.length != 10){
            throw BusinessException("La fecha inicial no puede ser distinta a 8 dígitos")
        }
        if(precioHistorico.fechaFinalPrecioHistorico.length != 10){
            throw BusinessException("La fecha final no puede ser distinta a 8 dígitos")
        }
        if(precioHistorico.precio.toString().length < 3){
            throw BusinessException("El precio no puede ser menor a 3 dígitos")
        }
    }

    @Throws(BusinessException::class)
    fun validarFecha(fecha:String) {
        val format = SimpleDateFormat("dd/mm/yyyy")
        if (fecha.length == 10) {
            try{
                format.parse(fecha)
            }catch(e: Exception){
                throw BusinessException("No ha ingresado una fecha valida, solo se pueden valores de fechas entre 1900 - 2099")
            }
            val regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$"
            val pattern: Pattern = Pattern.compile(regex)
            val matcher: Matcher = pattern.matcher(fecha)
            if (matcher.matches()) {
                return
            } else {
                throw BusinessException("El formato de fecha es incorrecto, debe ingresarlo (dd/mm/yyyy)")

            }
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitudMaxima(precioHistorico: PrecioHistorico){
        if(precioHistorico.precio.toString().length > 12){
            throw BusinessException("El precio no puede ser mayor a 12 dígitos")
        }
    }
}