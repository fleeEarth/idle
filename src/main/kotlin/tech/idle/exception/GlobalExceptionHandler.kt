package tech.idle.exception

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import tech.idle.result.Code
import tech.idle.result.Result


@ControllerAdvice
@ResponseBody
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun exception(e:Exception):Result{
        e.printStackTrace()
        return Result(Code.ERROR,"Server busy !")
    }

}