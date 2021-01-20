package an.example.randomchat.domain.user

import java.util.concurrent.ConcurrentHashMap
import org.springframework.stereotype.Repository
import an.example.randomchat.common.RandomChatException

@Repository
class UserRepository {
    private val users = mutableListOf<User>()

    private val indexById = ConcurrentHashMap<Long, User>()
    private val indexByNickName = ConcurrentHashMap<String, User>()

    fun create(nickName: String): User {
        validate(nickName)

        return createUser(nickName)
    }

    private fun validate(nickName: String) {
        findByNickName(nickName)?.let {
            throw RandomChatException("이미 사용중인 닉네임입니다.")
        }
    }

    private fun createUser(nickName: String): User {
        val user = User.create(nickName)

        users.add(user)
        onCreateUser(user)
        return user
    }

    fun findByNickName(nickname: String):User? {
        return indexByNickName[nickname]
    }

    fun findById(id: Long): User? {
        return indexById[id]
    }

    fun deleteUser(user: User) {
        findById(user.id)?.let {
            users.remove(it)
            onDeleteUser(it)
        }
    }

    private fun onCreateUser(user: User) {
        indexById[user.id] = user
        indexByNickName[user.nickName] = user
    }

    private fun onDeleteUser(user: User) {
        indexById.remove(user.id)
        indexByNickName.remove(user.nickName)
    }
}