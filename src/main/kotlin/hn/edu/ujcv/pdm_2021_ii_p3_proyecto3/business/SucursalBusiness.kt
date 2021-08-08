package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.EmpleadoRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.SucursalRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Sucursal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SucursalBusiness: ISucursalBusiness {

    @Autowired
    val sucursalRepository: SucursalRepository?=null

    @Throws(BusinessException::class)
    override fun getSucursal(): List<Sucursal> {
        try{
            return sucursalRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

//marce

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getSucursalById(idSucursal: Long): Sucursal {
        val opt: Optional<Sucursal>
        try{
            opt = sucursalRepository!!.findById(idSucursal)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la sucursal $idSucursal")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveSucursal(sucursal: Sucursal): Sucursal {
        try{
            validarEspacios(sucursal)
            validarLongitud(sucursal)

            return sucursalRepository!!.save(sucursal)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeSucursal(idSucursal: Long) {
        val opt: Optional<Sucursal>
        try{
            opt = sucursalRepository!!.findById(idSucursal)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró la sucursal $idSucursal")
        }
        else{
            try{
                sucursalRepository!!.deleteById(idSucursal)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun getSucursalByNombre(nombreSucursal: String): Sucursal {
        val opt: Optional<Sucursal>
        try{
            opt = sucursalRepository!!.findByNombreSucursal(nombreSucursal)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la sucursal $nombreSucursal")
        }
        return opt.get()
    }

    override fun updateSucursal(sucursal: Sucursal): Sucursal{
        val opt: Optional<Sucursal>
        try{
            opt = sucursalRepository!!.findById(sucursal.idSucursal)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la sucursal ${sucursal.idSucursal}")
        }
        else{
            try{
                validarEspacios(sucursal)
                validarLongitud(sucursal)

                return sucursalRepository!!.save(sucursal)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

//VALIDACIONES


    @Throws(BusinessException::class)
fun validarEspacios(sucursal: Sucursal){
    if(sucursal.idSucursal.toString().isEmpty()){
        throw BusinessException("El id de la sucursal no debe estar vacío")
    }
    if(sucursal.nombreSucursal.isEmpty()){
        throw BusinessException("El nombre no debe estar vacío")
    }
    if(sucursal.direccionSucursal.isEmpty()){
        throw BusinessException("La direccion no debe estar vacío")
    }
    if(sucursal.telefonoSucursal.toString().isEmpty()){
        throw BusinessException("El telefono no debe estar vacío")
    }
    if(sucursal.emailSucursal.isEmpty()){
        throw BusinessException("El email no debe estar vacío")
    }
}


    /*[idSucursal] [numeric](18, 0) NOT NULL,
[nombreSucursal] [varchar](30) NOT NULL,
[direccionSucursal] [varchar](60) NOT NULL,
[telefonoSucursal] [char](8) NOT NULL,
[emailSucursal] [varchar](30) */
    @Throws(BusinessException::class)
    fun validarLongitud(sucursal: Sucursal){
        if(sucursal.idSucursal.toString().length<8 ){
            throw BusinessException("El id no puede ser menor a 8 digitos")
        }
        if(sucursal.nombreSucursal.length>30){
            throw BusinessException("El nombre no puede ser mayor a 30 caracteres")
        }
        if(sucursal.direccionSucursal.length>60){
            throw BusinessException("La direccion no puede ser mayor a 60 caracteres")
        }
        if(sucursal.telefonoSucursal.toString().length<8){
            throw BusinessException("El telefono no puede ser menor a 8 dígitos")
        }
        if(sucursal.emailSucursal.length>30){
            throw BusinessException("El email no puede ser mayor a 30 caracteres")
        }


    }
}