package tech.idle.interceptor.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import tech.idle.interceptor.LoginInterceptor

@Configuration
class InterceptorConfig : WebMvcConfigurer {

    @Autowired
    lateinit var loginInterceptor: LoginInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
    }
}