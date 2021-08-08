package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.ICasoEmpleadoBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IEmpleadoBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.CasoEmpleado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_CASOEMPLEADO)
class CasoEmpleadoRestController {
    @Autowired
    val casoEmpleadoBusiness: ICasoEmpleadoBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<CasoEmpleado>> {
        return try{
            ResponseEntity(casoEmpleadoBusiness!!.getCasoEmpleado(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idCasoEmpleado:Long):ResponseEntity<CasoEmpleado>{
        return try{
            ResponseEntity(casoEmpleadoBusiness!!.getCasoEmpleadoById(idCasoEmpleado),HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }
    @PostMapping("/addCasoEmpleado")
    fun insert(@RequestBody casoEmpleado: CasoEmpleado):ResponseEntity<Any>{
        return try{
            casoEmpleadoBusiness!!.saveCasoEmpleado(casoEmpleado)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_CASOEMPLEADO+"/"+casoEmpleado.idCasoEmpleado)
            ResponseEntity(casoEmpleado,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody casoEmpleado: CasoEmpleado):ResponseEntity<Any>{
        return try{
            casoEmpleadoBusiness!!.updateCasoEmpleado(casoEmpleado)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idCasoEmpleado:Long):ResponseEntity<Any>{
        return try{
            casoEmpleadoBusiness!!.removeCasoEmpleado(idCasoEmpleado)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


//

}