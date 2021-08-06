package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils

class Constants {
    companion object{
        private const val URL_API_BASE ="/api"
        private const val URL_API_VERSION ="/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSION
        const val URL_BASE_EMPLEADO = "$URL_BASE/empleado"
        const val URL_BASE_INDICIO ="$URL_BASE/indicio"
        const val URL_BASE_JUZGADO ="$URL_BASE/juzgado"
        const val URL_BASE_SERVICIO ="$URL_BASE/servicio"
        const val URL_BASE_AUDIENCIA = "$URL_BASE/audiencia"
        const val URL_BASE_CAI = "$URL_BASE/cai"
        const val URL_BASE_CASO = "$URL_BASE/caso"
        const val URL_BASE_CASOEMPLEADO =  "$URL_BASE/casoEmpleado"
        const val URL_BASE_CLIENTE = "$URL_BASE/cliente"
        const val URL_BASE_EXPEDIENTE = "$URL_BASE/expediente"
        const val URL_BASE_FACTURAENCABEZADO = "$URL_BASE/facturaEncabezado"
    }
}