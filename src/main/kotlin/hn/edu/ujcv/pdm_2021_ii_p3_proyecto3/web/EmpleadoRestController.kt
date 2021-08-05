import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business.IEmpleadoBusiness
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.exceptions.BusinessException
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Empleado
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.Constants
import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_EMPLEADO)
class EmpleadoRestController {
    @Autowired
    val empleadoBusiness:IEmpleadoBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Empleado>> {
        return try{
            ResponseEntity(empleadoBusiness!!.getEmpleado(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idEmpleado:Long):ResponseEntity<Empleado>{
        return try{
            ResponseEntity(empleadoBusiness!!.getEmpleadoById(idEmpleado),HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreEmpleado:String):ResponseEntity<Empleado>{
        return try{
            ResponseEntity(empleadoBusiness!!.getEmpleadoByNombre(nombreEmpleado),HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addEmpleado")
    fun insert(@RequestBody empleado: Empleado):ResponseEntity<Any>{
        return try{
            empleadoBusiness!!.saveEmpleado(empleado)
            val responseHeader = HttpHeaders ()
            responseHeader.set("location",Constants.URL_BASE_EMPLEADO+"/"+empleado.idEmpleado)
            ResponseEntity(empleado,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida",e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody empleado: Empleado):ResponseEntity<Any>{
        return try{
            empleadoBusiness!!.updateEmpleado(empleado)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id")idEmpleado:Long):ResponseEntity<Any>{
        return try{
            empleadoBusiness!!.removeEmpleado(idEmpleado)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:BusinessException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}