package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Audiencia


interface IAudienciaBusiness {
    fun getAudiencia():List<Audiencia>
    fun getAudienciaById(id:Long): Audiencia
    fun saveAudiencia(audiencia: Audiencia): Audiencia
    fun removeAudiencia(idCaso:Long)
    fun getAudienciaByidCaso(idCaso: String): Audiencia
    fun updateAudiencia(audiencia: Audiencia): Audiencia
}