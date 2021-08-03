package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.EmpleadoRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.swing.JOptionPane





@Service
class EmpleadoBusiness: IEmpleadoBusiness {

    @Autowired
    val empleadoRepository: EmpleadoRepository?=null

    @Throws(BusinessException::class)
    override fun getEmpleado(): List<Empleado> {
       try{
           return empleadoRepository!!.findAll()
       }catch (e:Exception){
           throw BusinessException(e.message)
       }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getEmpleadoById(idEmpleado: Long): Empleado {
        val opt: Optional<Empleado>
        try{
            opt = empleadoRepository!!.findById(idEmpleado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isEmpty){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la persona $idEmpleado")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveEmpleado(empleado: Empleado): Empleado {
        try{
            validarEspacios(empleado)
            validarLongitud(empleado)
            validarSalario(empleado.salario)
            validarTelefono(empleado.telefono.toString())
            validarIdentidad(empleado.dni.toString())
            validarContraseñas(empleado.contraseña)
           return empleadoRepository!!.save(empleado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeEmpleado(idEmpleado: Long) {
        val opt: Optional<Empleado>
        try{
            opt = empleadoRepository!!.findById(idEmpleado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró el empleado $idEmpleado")
        }
        else{
            try{
                empleadoRepository!!.deleteById(idEmpleado)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun getEmpleadoByNombre(nombreEmpleado: String): Empleado {
        val opt: Optional<Empleado>
        try{
            opt = empleadoRepository!!.findByNombre(nombreEmpleado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el empleado $nombreEmpleado")
        }
        return opt.get()
    }

    override fun updateEmpleado(empleado: Empleado): Empleado {
        val opt: Optional<Empleado>
        try{
            opt = empleadoRepository!!.findById(empleado.id)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro el empleado ${empleado.id}")
        }
        else{
            try{
                return empleadoRepository!!.save(empleado)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    // Comienzan las validaciones
    @Throws(BusinessException::class)
    fun validarEspacios(empleado: Empleado){
        if(empleado.nombre.isEmpty()){
            throw BusinessException("El nombre no debe estar vacío")
        }
        if(empleado.apellido.isEmpty()){
            throw BusinessException("El apellido no debe estar vacío")
        }
        if(empleado.dni.toString().isEmpty()){
            throw BusinessException("El dni no debe estar vacío")
        }
        if(empleado.telefono.toString().isEmpty()){
            throw BusinessException("El telefono no debe estar vacío")
        }
        if(empleado.salario.toString().isEmpty()){
            throw BusinessException("El salario no debe estar vacío")
        }
        if(empleado.tipoEmpleado.isEmpty()){
            throw BusinessException("El tipo del empleado no debe estar vacío")
        }
        if(empleado.nombreUsuario.isEmpty()){
            throw BusinessException("El nombre de usuario no debe estar vacío")
        }
        if(empleado.contraseña.isEmpty()){
            throw BusinessException("La contraseña no debe estar vacía")
        }
    }

    @Throws(BusinessException::class)
    fun validarLongitud(empleado: Empleado){
        if(empleado.nombre.length < 3){
            throw BusinessException("El nombre no puede ser menor a 3 caracteres")
        }
        if(empleado.apellido.length < 3){
            throw BusinessException("El apellido no puede ser menor a 3 caracteres")
        }
        if(empleado.dni.toString().length != 15){
            throw BusinessException("El dni no puede ser distinto a 13 dígitos")
        }
        if(empleado.telefono.toString().length != 8){
            throw BusinessException("El telefono no puede ser distinto a 8 dígitos")
        }
        if(empleado.salario.toString().length < 4){
            throw BusinessException("El salario no puede ser menor a 4 dígitos")
        }

        if(empleado.nombreUsuario.length < 8){
            throw BusinessException("El nombre de usuario no puede ser menor a 8 dígitos")
        }

        if(empleado.contraseña.length < 8){
            throw BusinessException("La contraseña no puede ser menor a 8 dígitos")
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
                throw BusinessException("El número de teléfono debe comenzar con: 2,3,7,8 o 9")
            }
        }
    }

    fun validarSalario(salario:Double){
        if(salario <= 0){
            throw BusinessException("El campo salario no puede comenzar con 0")
        }
    }

    fun validarContraseñas(contraseña: String){
        if (contraseña.length > 7) {
            if (!politicasContraseña(contraseña)) {
                throw BusinessException("\"La contraseña no cumple las siguientes directrices: \\n 1. Debe contener al menos una letra minúscula (a-z)\"\n" +
                        "                 + \"\\n 2. Debe contener al menos una letra mayúscula (A-Z) \\n 3. Debe contener al menos un número (0-9)\"")
            }
        }
    }

    fun politicasContraseña(contraseña: String): Boolean {
        var tieneNumero = false
        var tieneMayusculas = false
        var tieneMinusculas = false
        var c: Char
        for (i in 0 until contraseña.length) {
            c = contraseña[i]
            if (Character.isDigit(c)) {
                tieneNumero = true
            } else if (Character.isUpperCase(c)) {
                tieneMayusculas = true
            } else if (Character.isLowerCase(c)) {
                tieneMinusculas = true
            }
            if (tieneNumero && tieneMayusculas && tieneMinusculas) {
                return true
            }
        }
        return false
    }

}