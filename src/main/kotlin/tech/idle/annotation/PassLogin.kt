package tech.idle.annotation

/**
 *
 * * 排除在登录校验之外
 *
 */
@Target(AnnotationTarget.FUNCTION,AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
annotation class PassLogin()