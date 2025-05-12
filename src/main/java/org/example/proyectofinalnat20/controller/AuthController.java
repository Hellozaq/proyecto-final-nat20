package org.example.proyectofinalnat20.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.proyectofinalnat20.dto.auth.LoginRequest;
import org.example.proyectofinalnat20.dto.auth.LoginResponse;
import org.example.proyectofinalnat20.entity.User;
import org.example.proyectofinalnat20.security.jwt.JwtTokenProvider;
import org.example.proyectofinalnat20.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@Controller
@RequestMapping("/")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private static final Duration TOKEN_DURATION = Duration.ofHours(1);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    // Endpoints para Thymeleaf
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupCreate(@Valid @ModelAttribute User user) {
        try {
            userService.save(user);
            return "redirect:/login?registered";
        } catch (IllegalArgumentException e) {
            logger.warn("Intento de registro con email existente: {}", user.getEmail());
            return "redirect:/signup?emailAlreadyExists";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(@RequestParam String email, 
                             @RequestParam String password,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Generar token JWT
            String jwt = tokenProvider.generateToken(authentication);
            
            // Crear cookie con el token
            Cookie jwtCookie = createSecureCookie("jwt", jwt);
            response.addCookie(jwtCookie);

            // Obtener información del usuario
            User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

            // Agregar información del usuario al modelo
            model.addAttribute("userEmail", user.getEmail());
            model.addAttribute("userRole", user.getRole().getName());

            logger.info("Login exitoso para usuario: {}", email);
            return "redirect:/home";
        } catch (BadCredentialsException e) {
            logger.warn("Intento de login fallido para usuario: {}", email);
            return "redirect:/login?error=invalid";
        } catch (Exception e) {
            logger.error("Error en login para usuario: {}", email, e);
            return "redirect:/login?error";
        }
    }

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    model.addAttribute("jwtToken", cookie.getValue());
                    break;
                }
            }
        }
        return "home";
    }

    // Endpoint REST para login (usado por Postman)
    @PostMapping("/api/auth/login")
    @ResponseBody
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);
            
            User user = userService.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

            logger.info("Login API exitoso para usuario: {}", loginRequest.getEmail());
            return ResponseEntity.ok(new LoginResponse(jwt, "Bearer", user.getEmail(), user.getRole().getName()));
        } catch (BadCredentialsException e) {
            logger.warn("Intento de login API fallido para usuario: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid email or password");
        } catch (Exception e) {
            logger.error("Error en login API para usuario: {}", loginRequest.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred during authentication");
        }
    }

    // Endpoint para verificar el token actual
    @GetMapping("/api/auth/verify")
    @ResponseBody
    public ResponseEntity<?> verifyToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    if (tokenProvider.validateToken(token)) {
                        String email = tokenProvider.getUsernameFromJWT(token);
                        User user = userService.findByEmail(email)
                            .orElseThrow(() -> new RuntimeException("User not found"));
                        return ResponseEntity.ok(new LoginResponse(null, null, user.getEmail(), user.getRole().getName()));
                    }
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
    }

    @PostMapping("/api/auth/logout")
    @ResponseBody
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie jwtCookie = createSecureCookie("jwt", null);
        jwtCookie.setMaxAge(0);
        response.addCookie(jwtCookie);

        return ResponseEntity.ok().build();
    }

    private Cookie createSecureCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge((int) TOKEN_DURATION.toSeconds());
        return cookie;
    }
}
