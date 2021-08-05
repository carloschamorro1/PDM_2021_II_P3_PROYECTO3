package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.Indicio

interface IIndicioBusiness {
    fun getIndicio():List<Indicio>
    fun getIndicioById(id:Long): Indicio
    fun saveIndicio(indicio: Indicio): Indicio
    fun removeIndicio(idIndicio:Long)
    fun getIndicioByIdCaso(idCaso: Long): Indicio
    fun updateIndicio(indicio: Indicio): Indicio
}