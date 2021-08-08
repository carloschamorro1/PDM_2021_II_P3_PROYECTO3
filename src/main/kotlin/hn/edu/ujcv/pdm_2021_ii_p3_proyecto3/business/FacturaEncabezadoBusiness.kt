package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.FacturaEncabezadoRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.FacturaEncabezado
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class FacturaEncabezadoBusiness:IFacturaEncabezadoBusiness {

    @Autowired
    val facturaEncabezadoRepository: FacturaEncabezadoRepository?=null

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getFacturaEncabezado(): List<FacturaEncabezado> {
        try{
            return facturaEncabezadoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getFacturaEncabezadoById(idFactura: Long): FacturaEncabezado {
        val opt:Optional<FacturaEncabezado>
        try{
            opt = facturaEncabezadoRepository!!.findById(idFactura)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el encabezado de la factura $idFactura")
        }
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveFacturaEncabezado(facturaEncabezado: FacturaEncabezado): FacturaEncabezado {
        try{
            validarEspacios(facturaEncabezado)
            validarLongitud(facturaEncabezado)
            validarLongitudMaxima(facturaEncabezado)
            return facturaEncabezadoRepository!!.save(facturaEncabezado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeFacturaEncabezado(idFactura: Long) {
        val opt:Optional<FacturaEncabezado>
        try{
            opt = facturaEncabezadoRepository!!.findById(idFactura)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el encabezado de la factura $idFactura")
        }
        else{
            try{
                facturaEncabezadoRepository!!.deleteById(idFactura)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateFacturaEncabezado(facturaEncabezado: FacturaEncabezado): FacturaEncabezado {
        val opt:Optional<FacturaEncabezado>
        try{
            opt = facturaEncabezadoRepository!!.findById(facturaEncabezado.idFactura)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la factura ${facturaEncabezado.idFactura}")
        }
        else{
            try{
                validarEspacios(facturaEncabezado)
                validarLongitud(facturaEncabezado)
                validarLongitudMaxima(facturaEncabezado)
                return facturaEncabezadoRepository!!.save(facturaEncabezado)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    // Comienzan las validaciones
    @Throws(BusinessException::class)
    fun validarEspacios(facturaEncabezado: FacturaEncabezado){
        if(facturaEncabezado.fechaEmisionFactura.isEmpty()){
            throw BusinessException("La fecha de emisión no debe estar vacía")
        }
        if(facturaEncabezado.idCai.toString().isEmpty()){
            throw BusinessException("El ID del CAI no debe estar vacío")
        }
        if(facturaEncabezado.idSucursal.toString().isEmpty()){
            throw BusinessException("El ID de la sucursal no debe estar vacío")
        }
        if(facturaEncabezado.totalFactura.toString().isEmpty()){
            throw BusinessException("El total de la factura no debe estar vacío")
        }
        if(facturaEncabezado.idEmpleado.toString().isEmpty()){
            throw BusinessException("El ID del empleado no debe estar vacío")
        }
        if(facturaEncabezado.idCaso.toString().isEmpty()){
            throw BusinessException("La direccion del cliente no debe estar vacía")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitud(facturaEncabezado: FacturaEncabezado){
        if(facturaEncabezado.fechaEmisionFactura.length != 10){
            throw BusinessException("La fecha no puede ser distinta de 10 caracteres")
        }
        if(facturaEncabezado.totalFactura.toString().length < 3){
            throw BusinessException("El total de la factura no puede ser menor a 3 dígitos")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitudMaxima(facturaEncabezado: FacturaEncabezado){
        if(facturaEncabezado.idCai.toString().length > 10){
            throw BusinessException("El ID del CAI no puede ser mayor a 10 caracteres")
        }
        if(facturaEncabezado.idSucursal.toString().length > 10){
            throw BusinessException("El dni no puede ser mayor a 10 caracteress")
        }
        if(facturaEncabezado.totalFactura.toString().length > 18){
            throw BusinessException("El total de la factura no puede ser distinto a 18 dígitos")
        }
        if(facturaEncabezado.idEmpleado.toString().length > 10){
            throw BusinessException("El ID del empleado no puede ser mayor a 10 caracteres")
        }
        if(facturaEncabezado.idCaso.toString().length > 10){
            throw BusinessException("La ID del caso no puede ser mayor a 10 caracteres")
        }
    }
}