package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IAudienciaBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Audiencia
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping(Constants.URL_BASE_AUDIENCIA)
class AudienciaRestController {
    @Autowired
    val audienciaBusiness: IAudienciaBusiness? = null
    fun list(): ResponseEntity<List<Audiencia>> {
        return try{
            ResponseEntity(audienciaBusiness!!.getAudiencia(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idFechaAudiencia:Long):ResponseEntity<Audiencia>{
        return try{
            ResponseEntity(audienciaBusiness!!.getAudienciaById(idFechaAudiencia),HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/idcaso/{idcaso}")
    fun loadByNombre(@PathVariable("idcaso") idcaso:String):ResponseEntity<Audiencia>{
        return try{
            ResponseEntity(audienciaBusiness!!.getAudienciaByidCaso(idcaso),HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/addAudiencia")
    fun insert(@RequestBody audiencia: Audiencia):ResponseEntity<Any>{
        return try{
            audienciaBusiness!!.saveAudiencia(audiencia)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_AUDIENCIA+"/"+audiencia.id)
            ResponseEntity(audiencia,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("")
    fun update(@RequestBody audiencia: Audiencia):ResponseEntity<Any>{
        return try{
            audienciaBusiness!!.updateAudiencia(audiencia)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idCaso:Long):ResponseEntity<Any>{
        return try{
            audienciaBusiness!!.removeAudiencia(idCaso)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


}
