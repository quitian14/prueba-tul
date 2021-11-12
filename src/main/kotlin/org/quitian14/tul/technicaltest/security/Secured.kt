package org.quitian14.tul.technicaltest.security

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Secured(
    val permissions: Array<String> = arrayOf(),
)
