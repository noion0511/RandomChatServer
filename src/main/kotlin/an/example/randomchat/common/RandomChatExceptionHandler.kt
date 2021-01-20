package an.example.randomchat.common

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.ExceptionHandler
import org.slf4j.LoggerFactory

@ControllerAdvice
@RestController
class RandomChatExceptionHandler {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(RandomChatException::class)
    fun handleRandomChatException(e: RandomChatException): ApiResponse {
        logger.error("API error", e)
        return ApiResponse.error(e.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ApiResponse {
        logger.error("API error", e)
        return ApiResponse.error("알 수 없는 오류가 발생했습니다.")
    }
}