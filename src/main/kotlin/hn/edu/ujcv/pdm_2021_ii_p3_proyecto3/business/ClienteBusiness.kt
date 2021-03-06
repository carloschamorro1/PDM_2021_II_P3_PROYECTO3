package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.ClienteRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Cliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

@Service
class ClienteBusiness:IClienteBusiness {

    @Autowired
    val clienteRepository: ClienteRepository?=null

    @Throws(BusinessException::class)
    override fun getCliente(): List<Cliente> {
        try{
            return clienteRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getClienteById(idCliente: Long): Cliente {
        val opt: Optional<Cliente>
        try{
            opt = clienteRepository!!.findById(idCliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el cliente $idCliente")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveCliente(cliente: Cliente): Cliente {
        try{
            validarEspacios(cliente)
            validarLongitud(cliente)
            validarTelefono(cliente.telefonoCliente)
            validarIdentidad(cliente.dniCliente)
            validarLongitudMaxima(cliente)
            return clienteRepository!!.save(cliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeCliente(idCliente: Long) {
        val opt: Optional<Cliente>
        try{
            opt = clienteRepository!!.findById(idCliente)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontr?? el cliente $idCliente")
        }
        else{
            try{
                clienteRepository!!.deleteById(idCliente)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getClienteByNombre(nombreCliente: String): Cliente {
        val opt: Optional<Cliente>
        try{
            opt = clienteRepository!!.findByNombreCliente(nombreCliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el cliente $nombreCliente")
        }
        return opt.get()
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateCliente(cliente: Cliente): Cliente {
        val opt: Optional<Cliente>
        try{
            opt = clienteRepository!!.findById(cliente.idCliente)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el cliente ${cliente.idCliente}")
        }
        else{
            try{
                validarEspacios(cliente)
                validarLongitud(cliente)
                validarTelefono(cliente.telefonoCliente)
                validarIdentidad(cliente.dniCliente)
                validarLongitudMaxima(cliente)
                return clienteRepository!!.save(cliente)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()

    }

    // Comienzan las validaciones
    @Throws(BusinessException::class)
    fun validarEspacios(cliente: Cliente){
        if(cliente.nombreCliente.isEmpty()){
            throw BusinessException("El nombre no debe estar vac??o")
        }
        if(cliente.apellidoCliente.isEmpty()){
            throw BusinessException("El apellido no debe estar vac??o")
        }
        if(cliente.dniCliente.isEmpty()){
            throw BusinessException("El dni no debe estar vac??o")
        }
        if(cliente.telefonoCliente.isEmpty()){
            throw BusinessException("El telefono no debe estar vac??o")
        }
        if(cliente.rtnCliente.isEmpty()){
            throw BusinessException("El rtn no debe estar vac??o")
        }
        if(cliente.direccionCliente.isEmpty()){
            throw BusinessException("La direccion del cliente no debe estar vac??a")
        }
    }

    @Throws(BusinessException::class)
    fun validarIdentidad(identidad:String){
        val id = identidad.substring(0,1)
        if(identidad.length == 15){
            if("0".equals(identidad)){
                return
            }
            else if("1".equals(identidad)){
                return
            }
            else{
                throw BusinessException("El numero de identidad solo puede comenzar con 1 y 0")
            }
        }
    }

    @Throws(BusinessException::class)
    fun validarTelefono(telefono:String) {
        if (telefono.length == 8) {
            val pattern: Pattern = Pattern.compile("[23789]")
            val matcher: Matcher = pattern.matcher(telefono.substring(0, 1))
            if (matcher.matches()) {
                return
            } else {
                throw BusinessException("El n??mero de tel??fono debe comenzar con: 2,3,7,8 o 9")
            }
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitud(cliente: Cliente){
        if(cliente.nombreCliente.length < 3){
            throw BusinessException("El nombre no puede ser menor a 3 caracteres")
        }
        if(cliente.apellidoCliente.length < 3){
            throw BusinessException("El apellido no puede ser menor a 3 caracteres")
        }
        if(cliente.dniCliente.length != 15){
            throw BusinessException("El dni no puede ser distinto a 13 d??gitos")
        }
        if(cliente.telefonoCliente.length != 8){
            throw BusinessException("El telefono no puede ser distinto a 8 d??gitos")
        }
        if(cliente.rtnCliente.length != 14){
            throw BusinessException("El rtn no puede ser distinto a 14 d??gitos")
        }

        if(cliente.direccionCliente.length < 8){
            throw BusinessException("La direccion no puede ser menor a 8 caracteres")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitudMaxima(cliente: Cliente){
        if(cliente.nombreCliente.length > 40){
            throw BusinessException("El nombre no puede ser mayor a 40 caracteres")
        }
        if(cliente.apellidoCliente.length > 40){
            throw BusinessException("El apellido no puede ser mayor a 40 caracteres")
        }
        if(cliente.dniCliente.length != 15){
            throw BusinessException("El dni no puede ser distinto a 13 d??gitos")
        }
        if(cliente.telefonoCliente.length != 8){
            throw BusinessException("El telefono no puede ser distinto a 8 d??gitos")
        }
        if(cliente.rtnCliente.length != 14){
            throw BusinessException("El rtn no puede ser distinto a 14 d??gitos")
        }

        if(cliente.direccionCliente.length > 30){
            throw BusinessException("La direccion no puede ser mayor a 30 caracteres")
        }
    }

}