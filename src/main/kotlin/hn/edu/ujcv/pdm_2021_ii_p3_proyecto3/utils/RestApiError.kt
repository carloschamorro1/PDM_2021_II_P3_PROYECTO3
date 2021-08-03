package hn.edu.ujcv.pdm_2021_ii_p3_proyecto3.utils

import org.springframework.http.HttpStatus

class RestApiError(val httpStatus: HttpStatus, errorMessage: String, detallesError:String) {

}