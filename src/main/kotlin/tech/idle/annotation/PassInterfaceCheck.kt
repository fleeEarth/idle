package tech.idle.annotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * 排除在接口权限校验之外
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
annotation class PassInterfaceCheck()
