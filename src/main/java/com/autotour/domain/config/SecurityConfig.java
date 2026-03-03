// 이거 로그인 로직쪽임 만들거면 다시 주석해제

// package com.autotour.domain.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

// @Bean
// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// http
// .csrf(csrf -> csrf.disable())
// .cors(cors -> cors.disable())
// .authorizeHttpRequests(auth -> auth
// // [수정] 모든 API 호출을 일단 허용하고, 권한 체크는 컨트롤러 내부에서 세션으로 처리함
// .requestMatchers("/api/**").permitAll()
// .requestMatchers("/", "/index.html", "/static/**", "/css/**",
// "/js/**").permitAll()
// .anyRequest().permitAll() // 테스트를 위해 전체 허용으로 우선 변경
// )
// .sessionManagement(session -> session
// // 세션 생성 정책을 '필요 시 생성'으로 설정
// .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
// .formLogin(form -> form.disable())
// .logout(logout -> logout.disable());

// return http.build();
// }

// @Bean
// public PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder();
// }
// }