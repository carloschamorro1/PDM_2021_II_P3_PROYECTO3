package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IIndicioBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IJuzgadoBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.JuzgadoBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Indicio
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Juzgado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(Constants.URL_BASE_JUZGADO)
class JuzgadoRestController {
    @Autowired
    val JuzgadoBusiness: IJuzgadoBusiness? = null
    fun list(): ResponseEntity<List<Juzgado>> {
        return try{
            ResponseEntity(JuzgadoBusiness!!.getJuzgado(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id")idJuzgado:Long): ResponseEntity<Juzgado> {
        return try{
            ResponseEntity(JuzgadoBusiness!!.getJuzgadoById(idJuzgado), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreJuzgado:String): ResponseEntity<Juzgado> {
        return try{
            ResponseEntity(JuzgadoBusiness!!.getJuzgadoByNombre(nombreJuzgado), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/addJuzgado")
    fun insert(@RequestBody juzgado: Juzgado): ResponseEntity<Any> {
        return try{
            JuzgadoBusiness!!.saveJuzgado(juzgado)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location", Constants.URL_BASE_JUZGADO+"/"+juzgado.id)
            ResponseEntity(juzgado,responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("")
    fun update(@RequestBody juzgado: Juzgado): ResponseEntity<Any> {
        return try{
            JuzgadoBusiness!!.updateJuzgado(juzgado)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idJuzgado: Long): ResponseEntity<Any> {
        return try{
            JuzgadoBusiness!!.removeJuzgado(idJuzgado)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}