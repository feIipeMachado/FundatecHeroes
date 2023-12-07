import com.example.fundatecheroes.login.data.repository.LoginRepository

class LoginUseCase {
    private val repository by lazy {
        LoginRepository()
    }

    suspend fun loginUser(
        email: String,
        password: String,
    ): Boolean {
        return repository.loginUser(
            email = email,
            password = password,
        )
    }

    suspend fun createUser(
        name: String,
        email: String,
        password: String,
    ): Boolean {
        return repository.createUser(
            name = name,
            email = email,
            password = password,
        )
    }

}