package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IFacturaDetalleBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.FacturaDetalle
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_FACTURADETALLE)
class FacturaDetalleRestController {
    @Autowired
    val facturaDetalleBusiness: IFacturaDetalleBusiness?= null

    @GetMapping("")
    fun list(): ResponseEntity<List<FacturaDetalle>> {
        return try{
            ResponseEntity(facturaDetalleBusiness!!.getFacturaDetalle(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idDetalle:Long):ResponseEntity<FacturaDetalle>{
        return try{
            ResponseEntity(facturaDetalleBusiness!!.getFacturaDetalleById(idDetalle),HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addFacturaDetalle")
    fun insert(@RequestBody facturaDetalle: FacturaDetalle):ResponseEntity<Any>{
        return try{
            facturaDetalleBusiness!!.saveFacturaDetalle(facturaDetalle)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_FACTURADETALLE+"/"+facturaDetalle.idDetalle)
            ResponseEntity(facturaDetalle,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody facturaDetalle: FacturaDetalle):ResponseEntity<Any>{
        return try{
            facturaDetalleBusiness!!.updateFacturaDetalle(facturaDetalle)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idDetalle:Long):ResponseEntity<Any>{
        return try{
            facturaDetalleBusiness!!.removeFacturaDetalle(idDetalle)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}