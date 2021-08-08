package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.dao.CobroRepository
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.NotFoundException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Cobro
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CobroBusiness: ICobroBusiness{
    @Autowired
    val cobroRepository: CobroRepository?=null

    @Throws(BusinessException::class)
    override fun getCobro(): List<Cobro> {
        try{
            return cobroRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getCobroById(idFactura: Long): Cobro {
        val opt: Optional<Cobro>
        try{
            opt = cobroRepository!!.findByIdFactura(idFactura)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw BusinessException("Debe ingresar un id")
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la factura $idFactura")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveCobro(cobro: Cobro):Cobro {
        try{
            validarEspacios(cobro)
            validarLongitud(cobro)

            return cobroRepository!!.save(cobro)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeCobro(idFactura: Long) {
        val opt: Optional<Cobro>
        try{
            opt = cobroRepository!!.findById(idFactura)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontró la factura $idFactura")
        }
        else{
            try{
                cobroRepository!!.deleteById(idFactura)
            }catch (e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
    }
    override fun updateCobro(cobro: Cobro): Cobro {
        val opt: Optional<Cobro>
        try{
            opt = cobroRepository!!.findById(cobro.idFactura)
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent){
            throw NotFoundException("No se encontro la factura ${cobro.idFactura}")
        }
        else{
            try{
                validarEspacios(cobro)
                validarLongitud(cobro)

                return cobroRepository!!.save(cobro)
            }catch(e: java.lang.Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
    //VALIDACIONES
    @Throws(BusinessException::class)
    fun validarEspacios(cobro: Cobro){
        if(cobro.fechaEmisionFactura.toString().isEmpty()){
            throw BusinessException("La fecha no debe estar vacío")
        }
        if(cobro.idCAI.toString().isEmpty()){
            throw BusinessException("El id CAI no debe estar vacío")
        }
        if(cobro.idSucursal.toString().isEmpty()){
            throw BusinessException("El id Sucursal no debe estar vacío")
        }
        if(cobro.totalFactura.toString().isEmpty()){
            throw BusinessException("El total no debe estar vacío")
        }
        if(cobro.idEmpleado.toString().isEmpty()){
            throw BusinessException("El id del empleado no debe estar vacío")
        }
        if(cobro.idCaso.toString().isEmpty()){
            throw BusinessException("El id del caso no debe estar vacío")
        }
        if(cobro.idDetalle.toString().isEmpty()){
            throw BusinessException("El id detalle no debe estar vacío")
        }
        if(cobro.idFactura.toString().isEmpty()){
            throw BusinessException("El id de la factura no debe estar vacía")
        }
        if(cobro.cantidadFactura.toString().isEmpty()){
            throw BusinessException("La cantidad de la factura no debe estar vacía")
        }
    }
    @Throws(BusinessException::class)
    fun validarLongitud(cobro: Cobro){
        if(cobro.fechaEmisionFactura.toString().length <4 ){
            throw BusinessException("La fecha no puede ser menor a 4 caracteres")
        }
        if(cobro.idCAI.toString().length<8){
            throw BusinessException("El id CAI no puede ser menor a 8 digitos")
        }
        if(cobro.idSucursal.toString().length<8){
            throw BusinessException("El id de la sucursal no puede ser menor a 8 dígitos")
        }
        if(cobro.idEmpleado.toString().length<8){
            throw BusinessException("El id del empleado no puede ser menor a 8 dígitos")
        }
        if(cobro.idCaso.toString().length<8){
            throw BusinessException("El id del caso no puede ser menor a 8 dígitos")
        }
        if(cobro.idDetalle.toString().length<8){
            throw BusinessException("El id del detalle no puede ser menor a 8 dígitos")
        }

        if(cobro.idFactura.toString().length<8){
            throw BusinessException("El id de la factura no puede ser menor a 8 dígitos")
        }


//Marce
    }
}