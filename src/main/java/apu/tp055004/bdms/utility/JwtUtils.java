package apu.tp055004.bdms.utility;

import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtUtils {
	
	public static Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
	
	public static String createAccessToken(HttpServletRequest request, User user) {
		return JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30 minutes
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
	}
	
	public static String createRefreshToken(HttpServletRequest request, User user) {
		return JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 1 day
				.withIssuer(request.getRequestURL().toString())
				.sign(algorithm);
	}
}
