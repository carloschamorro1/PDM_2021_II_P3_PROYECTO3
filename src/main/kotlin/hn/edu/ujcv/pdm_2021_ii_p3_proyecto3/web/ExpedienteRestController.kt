package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IExpedienteBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Expediente
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_EXPEDIENTE)

class ExpedienteRestController {
    @Autowired
    val expedienteBusiness:IExpedienteBusiness?=null

    @GetMapping("")
    fun list(): ResponseEntity<List<Expediente>> {
        return try{
            ResponseEntity(expedienteBusiness!!.getExpediente(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addExpediente")
    fun insert(@RequestBody expediente: Expediente):ResponseEntity<Any>{
        return try{
            expedienteBusiness!!.saveExpediente(expediente)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_CLIENTE+"/"+expediente.idExpediente)
            ResponseEntity(expediente,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody expediente: Expediente):ResponseEntity<Any>{
        return try{
            expedienteBusiness!!.updateExpediente(expediente)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idExpediente:Long):ResponseEntity<Any>{
        return try{
            expedienteBusiness!!.removeExpediente(idExpediente)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}