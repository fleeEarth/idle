package tech.idle

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@MapperScan("tech.idle.dao")
@SpringBootApplication
class IdleApplication

fun main(args: Array<String>) {
	runApplication<IdleApplication>(*args)
}
