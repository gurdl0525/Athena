package athena.kanghyuk.com.presentation.auth

import athena.kanghyuk.com.core.auth.port.`in`.LoginUseCase
import athena.kanghyuk.com.core.auth.port.`in`.SignUpUseCase
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@Validated
@RestController
@RequestMapping("/auth")
class AuthController(
    private val signUpUseCase: SignUpUseCase,
    private val loginUseCase: LoginUseCase
) {

    @PostMapping("/signup/google")
    @ResponseStatus(HttpStatus.CREATED)
    fun signupGoogle(
        @Valid @NotBlank(message = "비어있거나 null, 공백일 수 없습니다.")
        @RequestParam("token", required = false)
        token: String?
    ) {
        signUpUseCase.google(token!!)
    }

    @GetMapping("/login/google")
    fun loginGoogle(
        @Valid @NotBlank(message = "비어있거나 null, 공백일 수 없습니다.")
        @RequestParam("token", required = false)
        token: String?
    ): ResponseEntity<Any> {
        val (access, refresh) = loginUseCase.google(token!!)

        val responseHeaders = HttpHeaders().apply {
            add(HttpHeaders.SET_COOKIE, "RF-TOKEN=$refresh; Secure; HttpOnly; SameSite=lax")
            setBearerAuth(access)
        }

        return ResponseEntity.ok().headers(responseHeaders).build()
    }

    @PostMapping("/signup/facebook")
    @ResponseStatus(HttpStatus.CREATED)
    fun signupFacebook(
        @Valid @NotBlank(message = "비어있거나 null, 공백일 수 없습니다.")
        @RequestParam("token", required = false)
        token: String?
    ) {
        signUpUseCase.facebook(token!!)
    }

    @GetMapping("/login/facebook")
    fun loginFacebook(
        @Valid @NotBlank(message = "비어있거나 null, 공백일 수 없습니다.")
        @RequestParam("token", required = false)
        token: String?
    ): ResponseEntity<Any> {
        val (access, refresh) = loginUseCase.facebook(token!!)

        val responseHeaders = HttpHeaders().apply {
            add(HttpHeaders.SET_COOKIE, "RF-TOKEN=$refresh; Secure; HttpOnly; SameSite=lax")
            setBearerAuth(access)
        }

        return ResponseEntity.ok().headers(responseHeaders).build()
    }
}