package org.quitian14.tul.technicaltest.exceptions

data class Error(
    val path: String?,
    val code: String,
    val status: String?,
    val message: String
) {
    override fun toString(): String {
        return "Error(path=$path, code='$code', status=$status, message='$message')"
    }
}
