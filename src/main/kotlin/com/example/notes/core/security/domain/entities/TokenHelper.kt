package com.example.notes.core.security.domain.entities

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component
import org.springframework.web.util.WebUtils
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.RSAKeyGenParameterSpec
import java.security.spec.X509EncodedKeySpec
import java.util.*

@Component
class TokenHelper {
    @Value("\${system.security.jwt.path}")
    private lateinit var path: String

    @Value("\${system.security.jwt.publicKey}")
    private lateinit var publicKey: String

    @Value("\${system.security.jwt.privateKey}")
    private lateinit var privateKey: String

    @Value("\${system.security.jwt.expiration}")
    private var expiration: Int = 0

    @Value("\${system.security.jwt.cookieName}")
    private lateinit var cookieName: String

    @Value("\${system.security.jwt.issuer}")
    private lateinit var issuer: String

    @Value("\${system.security.jwt.secureCookie}")
    private var secureCookie: Boolean = false

    @Value("\${system.security.jwt.sameSite}")
    private var sameSite: String = "None"

    fun getJwtFromCookies(request: HttpServletRequest): String? =
        WebUtils.getCookie(request, cookieName)?.run { value }

    fun generateJwtCookie(userUUID: UUID): ResponseCookie {
        val jwt = generateTokenFromUserUUID(userUUID.toString())
        return ResponseCookie.from(cookieName, jwt)
            .path(path)
            .maxAge(24 * 60 * 60)
            .httpOnly(true)
            .sameSite(sameSite)
            .secure(secureCookie)
            .build()
    }

    fun getCleanJwtCookie(): ResponseCookie = ResponseCookie.from(cookieName).path(path).build()

    fun getUserUUIDFromJwtToken(authToken: String): String {
        val decoded = JWT.require(algorithm())
            .withIssuer(issuer)
            .build()
            .verify(authToken)
        return decoded.subject
    }

    fun validateJwtToken(authToken: String): Boolean {
        return try {
            JWT.require(algorithm())
                .withIssuer(issuer)
                .build()
                .verify(authToken)
            true
        } catch (e: JWTVerificationException) {
            false
        }
    }

    fun generateTokenFromUserUUID(username: String?): String {
        val now = Date()
        val expiresAt = GregorianCalendar().run {
            time = now
            add(GregorianCalendar.SECOND, expiration)
            time
        }

        return JWT
            .create()
            .withIssuer(issuer)
            .withSubject(username)
            .withIssuedAt(now)
            .withExpiresAt(expiresAt)
            .sign(algorithm())
    }

    private fun algorithm(): Algorithm {
        val keyFactory = KeyFactory.getInstance("RSA")
        val publicKeySpec = X509EncodedKeySpec(Base64.getDecoder().decode(publicKey))
        val privateKeySpec = PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey))
        val decodedPublicKey = keyFactory.generatePublic(publicKeySpec) as RSAPublicKey
        val decodedPrivateKey = keyFactory.generatePrivate(privateKeySpec) as RSAPrivateKey
        return Algorithm.RSA256(decodedPublicKey, decodedPrivateKey)
    }

    // this method was used to generate the keys added to the application properties
    private fun generateKeys() {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA").apply {
            initialize(RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F4))
        }

        val keyPair = keyPairGenerator.generateKeyPair()

        println("Public: ${Base64.getEncoder().encodeToString(keyPair.public.encoded)}")
        println("Private: ${Base64.getEncoder().encodeToString(keyPair.private.encoded)}")
    }
}