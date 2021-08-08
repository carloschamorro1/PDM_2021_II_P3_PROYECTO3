package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.ICobroBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IEmpleadoBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Cobro
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_COBRO)
class CobroRestController {
    @Autowired
    val cobroBusiness: ICobroBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Cobro>> {
        return try{
            ResponseEntity(cobroBusiness!!.getCobro(),HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idFactura:Long):ResponseEntity<Cobro>{
        return try{
            ResponseEntity(cobroBusiness!!.getCobroById(idFactura),HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addCobro")
    fun insert(@RequestBody cobro: Cobro):ResponseEntity<Any>{
        return try{
            cobroBusiness!!.saveCobro(cobro)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_COBRO+"/"+cobro.idFactura)
            ResponseEntity(cobro,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody cobro: Cobro):ResponseEntity<Any>{
        return try{
            cobroBusiness!!.updateCobro(cobro)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
//m
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idFactura:Long):ResponseEntity<Any>{
        return try{
            cobroBusiness!!.removeCobro(idFactura)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}