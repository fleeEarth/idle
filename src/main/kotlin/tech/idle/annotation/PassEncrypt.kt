package tech.idle.annotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * 排除在加密之外
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
annotation class PassEncrypt()
