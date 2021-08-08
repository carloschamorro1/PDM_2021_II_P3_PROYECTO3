package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IPrecioHistoricoBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Cliente
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.PrecioHistorico
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PRECIOHISTORICO)
class PrecioHistoricoRestController {
    @Autowired
    val precioHistoricoBusiness: IPrecioHistoricoBusiness ?= null

    @GetMapping("")
    fun list(): ResponseEntity<List<PrecioHistorico>> {
        return try{
            ResponseEntity(precioHistoricoBusiness!!.getPrecioHistorico(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idPrecioHistorico:Long):ResponseEntity<PrecioHistorico>{
        return try{
            ResponseEntity(precioHistoricoBusiness!!.getPrecioHistoricoById(idPrecioHistorico),HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addPrecioHistorico")
    fun insert(@RequestBody precioHistorico: PrecioHistorico):ResponseEntity<Any>{
        return try{
            precioHistoricoBusiness!!.savePrecioHistorico(precioHistorico)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_PRECIOHISTORICO+"/"+precioHistorico.idPrecioHistorico)
            ResponseEntity(precioHistorico,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody precioHistorico: PrecioHistorico):ResponseEntity<Any>{
        return try{
            precioHistoricoBusiness!!.savePrecioHistorico(precioHistorico)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idPrecioHistorico:Long):ResponseEntity<Any>{
        return try{
            precioHistoricoBusiness!!.removePrecioHistorico(idPrecioHistorico)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}