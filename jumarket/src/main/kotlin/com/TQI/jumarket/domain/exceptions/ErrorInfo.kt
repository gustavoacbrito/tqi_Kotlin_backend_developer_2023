package com.TQI.jumarket.domain.exceptions

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import org.springframework.web.context.request.WebRequest
import java.time.OffsetDateTime

@JsonInclude(Include.NON_NULL)
data class ErrorInfo(
    var status: Int?,
    var dateTime: OffsetDateTime?,
    var title: String?,
    var fields: List<Field>? = null,
    val exception: String
) {
    data class Field(
        var name: String,
        var message: String
    )
}
