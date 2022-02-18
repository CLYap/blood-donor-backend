package apu.tp055004.bdms.api;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import apu.tp055004.bdms.model.AppUser;
import apu.tp055004.bdms.model.Role;
import apu.tp055004.bdms.service.AppUserService;

@RestController
@RequestMapping("/api")
public class AppUserController {

	@Autowired
	private final AppUserService appUserService;

	public AppUserController(AppUserService appUserService) {
		this.appUserService = appUserService;
	}
	
	@PostMapping("/create/user/account")
	public ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/create/account").toUriString());
		return ResponseEntity.created(uri).body(appUserService.saveUser(appUser));
	}

	@PostMapping("/role/save")
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
		return ResponseEntity.created(uri).body(appUserService.saveRole(role));
	}

	@PostMapping("/role/addtouser")
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
		appUserService.addRoleToUser(form.getUsername(), form.getRoleName());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refresh_token = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refresh_token);
				String username = decodedJWT.getSubject();
				AppUser user = appUserService.getUser(username);
				String access_token = JWT.create().withSubject(user.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles",
								user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
						.sign(algorithm);
				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refresh_token);
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);

			} catch (Exception exception) {
				response.setHeader("error", exception.getMessage());
				response.setStatus(403);
				Map<String, String> error = new HashMap<>();
				error.put("error_message", exception.getMessage());
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		} else {
			throw new RuntimeException("Refresh token is missing");
		}
	}
	
	@PutMapping("/reset/password")
	public ResponseEntity<AppUser> resetPassword(@RequestBody AppUser appUser) {
		return ResponseEntity.ok(appUserService.updateAppUser(appUser));
	}
}

class RoleToUserForm {

	private String username;
	private String roleName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
