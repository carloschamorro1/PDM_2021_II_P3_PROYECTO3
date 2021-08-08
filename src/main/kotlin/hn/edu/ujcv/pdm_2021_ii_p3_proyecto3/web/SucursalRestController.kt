package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IEmpleadoBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.ISucursalBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Sucursal
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_SUCURSAL)
class SucursalRestController {
    @Autowired
    val sucursalBusiness: ISucursalBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Sucursal>> {
        return try{
            ResponseEntity(sucursalBusiness!!.getSucursal(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idSucursal:Long):ResponseEntity<Sucursal>{
        return try{
            ResponseEntity(sucursalBusiness!!.getSucursalById(idSucursal),HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreSucursal:String):ResponseEntity<Sucursal>{
        return try{
            ResponseEntity(sucursalBusiness!!.getSucursalByNombre(nombreSucursal),HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addSucursal")
    fun insert(@RequestBody sucursal: Sucursal):ResponseEntity<Any>{
        return try{
            sucursalBusiness!!.saveSucursal(sucursal)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_SUCURSAL+"/"+sucursal.idSucursal)
            ResponseEntity(sucursal,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody sucursal: Sucursal):ResponseEntity<Any>{
        return try{
            sucursalBusiness!!.updateSucursal(sucursal)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
//
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idSucursal:Long):ResponseEntity<Any>{
        return try{
            sucursalBusiness!!.removeSucursal(idSucursal)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}