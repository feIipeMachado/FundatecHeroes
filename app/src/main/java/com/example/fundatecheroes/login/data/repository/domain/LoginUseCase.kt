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


}