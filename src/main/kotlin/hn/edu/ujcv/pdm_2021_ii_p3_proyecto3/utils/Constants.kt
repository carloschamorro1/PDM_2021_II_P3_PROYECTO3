package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils

class Constants {
    companion object{
        private const val URL_API_BASE ="/api"
        private const val URL_API_VERSION ="/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSION
        const val URL_BASE_EMPLEADO ="$URL_BASE/empleados"
    }
}