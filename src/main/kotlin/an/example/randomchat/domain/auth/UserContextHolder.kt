package an.example.randomchat.domain.auth

import org.springframework.stereotype.Component
import kotlin.properties.Delegates

//요청 스레드 전역에서 사용할 수 있는 사용자 정보를 가지고 있는 클래스

@Component
class UserContextHolder {

    private val userHolder = ThreadLocal.withInitial{
        UserHolder()
    }

    val nickName: String get() = userHolder.get().nickName
    val id: Long get() = userHolder.get().id

    fun set(nickName: String, id: Long) = this.userHolder.get().also {
        it.id = id
        it.nickName = nickName
    }.run(userHolder::set)

    fun clear() {
        userHolder.remove()
    }

    class UserHolder {
        var id by Delegates.notNull<Long>()
        lateinit var nickName: String
    }
}