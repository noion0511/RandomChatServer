package an.example.randomchat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//스프링부트 애플리케이션을 정의하고 실행해줄 파일

@SpringBootApplication
class App

fun main(args: Array<String>) {
	runApplication<App>(*args)
}
