package an.example.randomchat.domain.user

import java.util.concurrent.atomic.AtomicLong

//1. User 객체를 직접 생성하지 못하도록 private 접근자로 생성
//2. User 객체를 생성하는 함수는 User 클래스 내부에 정적 함수로 정의, thread-safe AtomicLong의 nextUserId를 사용
//-> 객체마다 서버 전역에서 유일한 ID값을 할당

class User private constructor(val id: Long, val nickName: String)
{
    companion object {
        private val nextUserId = AtomicLong(1)

        fun create(nickName: String):User {
            val userId = nextUserId.getAndIncrement()
            return User(userId, nickName)
        }
    }
}