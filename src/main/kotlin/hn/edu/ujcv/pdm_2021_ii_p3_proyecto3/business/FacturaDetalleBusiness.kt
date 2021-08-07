package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.FacturaDetalleRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.FacturaDetalle
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class FacturaDetalleBusiness:IFacturaDetalleBusiness {

    @Autowired
    val facturaDetalleRepository: FacturaDetalleRepository?= null

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getFacturaDetalle(): List<FacturaDetalle> {
        try{
            return facturaDetalleRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getFacturaDetalleById(idDetalle: Long): FacturaDetalle {
        val opt:Optional<FacturaDetalle>
        try{
            opt = facturaDetalleRepository!!.findById(idDetalle)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el id del detalle de la factura $idDetalle")
        }
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveFacturaDetalle(facturaDetalle: FacturaDetalle): FacturaDetalle {
        try{
        validarEspacios(facturaDetalle)
        validarLongitudMaxima(facturaDetalle)
        return facturaDetalleRepository!!.save(facturaDetalle)
             }catch (e:Exception){
        throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeFacturaDetalle(idDetalle: Long) {
        val opt:Optional<FacturaDetalle>
        try{
            opt = facturaDetalleRepository!!.findById(idDetalle)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el id del detalle de la factura $idDetalle")
        }
        else{
            try{
                facturaDetalleRepository!!.deleteById(idDetalle)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateFacturaDetalle(facturaDetalle: FacturaDetalle): FacturaDetalle {
        val opt:Optional<FacturaDetalle>
        try{
            opt = facturaDetalleRepository!!.findById(facturaDetalle.idDetalle)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el id del detalle de la factura ${facturaDetalle.idDetalle}")
        }
        else{
            try{
                validarLongitudMaxima(facturaDetalle)
                return facturaDetalleRepository!!.save(facturaDetalle)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    fun validarEspacios(facturaDetalle: FacturaDetalle){
        if(facturaDetalle.idDetalle.toString().isEmpty()){
            throw BusinessException("El id del detalle de la factura no debe estar vacío")
        }
        if(facturaDetalle.idFactura.toString().isEmpty()){
            throw BusinessException("El id de la factura no debe estar vacío")
        }
        if(facturaDetalle.idServicio.toString().isEmpty()){
            throw BusinessException("El id del servicio no debe estar vacío")
        }

        if(facturaDetalle.cantidadFactura.isEmpty()){
            throw BusinessException("La cantidad no puede estar vacía")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitudMaxima(facturaDetalle: FacturaDetalle){
        if(facturaDetalle.idDetalle.toString().length > 10){
            throw BusinessException("El id del detalle de la factura no puede ser mayor a 10 dígitos")
        }
        if(facturaDetalle.idFactura.toString().length > 10){
            throw BusinessException("El id de la factura no puede ser mayor a 10 dígitos")
        }
        if(facturaDetalle.idServicio.toString().length > 10){
            throw BusinessException("El id del servicio no puede ser mayor a 10 dígitos")
        }

        if(facturaDetalle.cantidadFactura.length > 2){
            throw BusinessException("La cantidad no puede ser mayor a 2")
        }
    }
}