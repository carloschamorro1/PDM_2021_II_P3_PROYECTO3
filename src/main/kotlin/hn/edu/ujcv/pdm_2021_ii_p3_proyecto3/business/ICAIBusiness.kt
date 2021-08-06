package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.business

import hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.model.CAI

interface ICAIBusiness {
    fun getCAI():List<CAI>
    fun getCAIById(id:Long): CAI
    fun saveCAI(cai: CAI): CAI
    fun removeCAI(idCAI:Long)
    fun updateCAI(cai: CAI): CAI
}