package an.example.randomchat.configuration

import an.example.randomchat.interceptor.TokenValidationInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

//스프링에서 TokenValidationInterceptor을 사용할 수 있도록 등록

@Configuration
class WebConfig(private val tokenValidationInterceptor: TokenValidationInterceptor): WebMvcConfigurer
{
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(tokenValidationInterceptor).addPathPatterns("/api/**")
    }
}