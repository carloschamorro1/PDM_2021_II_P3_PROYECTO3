package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IJuzgadoBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IServicioBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.ServicioBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Juzgado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Servicio
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(Constants.URL_BASE_SERVICIO)
class ServicioRestController {
    @Autowired
    val ServicioBusiness: IServicioBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Servicio>> {
        return try{
            ResponseEntity(ServicioBusiness!!.getServicio(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id")idServicio:Long): ResponseEntity<Servicio> {
        return try{
            ResponseEntity(ServicioBusiness!!.getServicioById(idServicio), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreJuzgado:String): ResponseEntity<Servicio> {
        return try{
            ResponseEntity(ServicioBusiness!!.getServicioByNombre(nombreJuzgado), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addServicio")
    fun insert(@RequestBody  servicio: Servicio): ResponseEntity<Any> {
        return try{
            ServicioBusiness!!.saveServicio(servicio)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location", Constants.URL_BASE_SERVICIO+"/"+servicio.id)
            ResponseEntity(servicio,responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody servicio: Servicio): ResponseEntity<Any> {
        return try{
            ServicioBusiness!!.updateSercicio(servicio)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idServicio: Long): ResponseEntity<Any> {
        return try{
            ServicioBusiness!!.removeServicio(idServicio)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}