package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.ICasoBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Caso
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_CASO)
class CasoRestController {
    @Autowired
    val casoBusiness: ICasoBusiness? = null
    @GetMapping("")
    fun list(): ResponseEntity<List<Caso>> {
        return try{
            ResponseEntity(casoBusiness!!.getCaso(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idCaso:Long):ResponseEntity<Caso>{
        return try{
            ResponseEntity(casoBusiness!!.getCasoById(idCaso),HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @GetMapping("/idCliente/{idCliente}")
    fun loadByIdCliente(@PathVariable("idCliente") idCliente:Long):ResponseEntity<Caso>{
        return try{
            ResponseEntity(casoBusiness!!.getCasoByIdCliente(idCliente),HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addCaso")
    fun insert(@RequestBody caso: Caso):ResponseEntity<Any>{
        return try{
            casoBusiness!!.saveCaso(caso)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_CASO+"/"+caso.idCaso)
            ResponseEntity(caso,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody caso: Caso):ResponseEntity<Any>{
        return try{
            casoBusiness!!.updateCaso(caso)
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
            casoBusiness!!.removeCaso(idCaso)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}