package tech.idle.aop

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.http.HttpInputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import tech.idle.annotation.PassEncrypt
import tech.idle.constant.CommonConstant
import tech.idle.result.Result
import java.lang.reflect.Type
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class EncryptAop : ResponseBodyAdvice<Any>, RequestBodyAdvice {


    @Autowired
    lateinit var request: HttpServletRequest

    /**
     * 响应数据加密
     */
    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        req: ServerHttpRequest,
        res: ServerHttpResponse
    ): Any? {
        return if (CommonConstant.OPEN_ENCRYPT && !returnType.method!!.isAnnotationPresent(PassEncrypt::class.java)) {
            var result = body as Result
            var data = result.data
            val token = request.getHeader(CommonConstant.TOKEN_NAME)
//            if (SessionUtil.isTimeout(token)) {
//                return body
//            }
//            val userInfo = RedisUtil.getHashValueForUserInfo(token)
//            result.data = SM464Util.encryptData_ECB(data.toString(), userInfo!!.sm4Key)
            result
        } else {
            body
        }

    }

    override fun afterBodyRead(
        body: Any,
        inputMessage: HttpInputMessage,
        parameter: MethodParameter,
        targetType: Type,
        converterType: Class<out HttpMessageConverter<*>>
    ): Any {
        return body
    }


    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun supports(
        methodParameter: MethodParameter,
        targetType: Type,
        converterType: Class<out HttpMessageConverter<*>>
    ): Boolean {
        return true
    }

    /**
     * 请求体数据解密
     */
    override fun beforeBodyRead(
        inputMessage: HttpInputMessage,
        parameter: MethodParameter,
        targetType: Type,
        converterType: Class<out HttpMessageConverter<*>>
    ): HttpInputMessage {
        if (CommonConstant.OPEN_ENCRYPT && !parameter.method!!.isAnnotationPresent(PassEncrypt::class.java)) {
            val token = request.getHeader(CommonConstant.TOKEN_NAME)
//            val userInfo = RedisUtil.getHashValueForUserInfo(token)
//            val enStr = IOUtils.toString(inputMessage.body, "UTF-8")
//            val deStr = SM464Util.decryptData_ECB(enStr, userInfo.sm4Key)
//            return EncryptHttpInputMessage(inputMessage.headers, IOUtils.toInputStream(deStr, "UTF-8"))
        }
        return inputMessage
    }


    override fun handleEmptyBody(
        body: Any?,
        inputMessage: HttpInputMessage,
        parameter: MethodParameter,
        targetType: Type,
        converterType: Class<out HttpMessageConverter<*>>
    ): Any? {
        return null
    }


}