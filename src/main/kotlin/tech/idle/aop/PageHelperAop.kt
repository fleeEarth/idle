package tech.idle.aop

import com.alibaba.fastjson.JSONObject
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.apache.commons.lang3.StringUtils
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import tech.idle.annotation.PassPageAop

/**
 * 全局分页处理
 */
@Aspect
@Component
class PageHelperAop {

    @Pointcut("execution(public * tech.idle.dao..*.*(..))")
    fun pageHelper() {
    }

    @Around("pageHelper()")
    fun doAround(joinPoint: ProceedingJoinPoint): Any? {
        if((joinPoint.signature as MethodSignature).method.isAnnotationPresent(PassPageAop::class.java)){
            //存在排除分页
            return joinPoint.proceed()
        }
        val obj = joinPoint.args
        if(obj == null || obj.isEmpty()){
            return joinPoint.proceed()
        }
        val params = obj[0] as JSONObject
        val page = params.getInteger("page")
        val pageSize = params.getInteger("pageSize")
        val orderBy = params.getString("orderBy")
        if (page != null && pageSize != null) {
            val pageResult: Page<Any> = PageHelper.startPage(page, pageSize, orderBy)
            val proceed = joinPoint.proceed()
            //将查询的count数放入参数中
            params["total"] = pageResult.total
            return proceed
        }
        return joinPoint.proceed()

    }
}