package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.web


import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IIndicioBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Indicio
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(Constants.URL_BASE_INDICIO)
class IndicioRestController {
    @Autowired
    val IndicioBusiness: IIndicioBusiness? = null
    fun list(): ResponseEntity<List<Indicio>> {
        return try{
            ResponseEntity(IndicioBusiness!!.getIndicio(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id")idIndicio:Long): ResponseEntity<Indicio> {
        return try{
            ResponseEntity(IndicioBusiness!!.getIndicioById(idIndicio), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") idcaso:Long): ResponseEntity<Indicio> {
        return try{
            ResponseEntity(IndicioBusiness!!.getIndicioByIdCaso(idcaso), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/addIndicio")
    fun insert(@RequestBody indicio: Indicio): ResponseEntity<Any> {
        return try{
            IndicioBusiness!!.saveIndicio(indicio)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_JUZGADO+"/"+indicio.id)
            ResponseEntity(indicio,responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("")
    fun update(@RequestBody indicio: Indicio): ResponseEntity<Any> {
        return try{
            IndicioBusiness!!.updateIndicio(indicio)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idIndicio: Long): ResponseEntity<Any> {
        return try{
            IndicioBusiness!!.removeIndicio(idIndicio)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}