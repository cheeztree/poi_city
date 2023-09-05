package poicity.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import poicity.entity.User;
import poicity.service.JwtService;

@Service
public class JwtServiceImpl implements JwtService{

	private final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
	
	@Override
	public String getToken(UserDetails user) {
		return getToken(new HashMap<>(), user);
	}	
	
	@Override
	public String getToken(User user) {
		return getToken(new HashMap<>(), user);
	}	
	
	private String getToken(Map<String, Object> extraClaims, UserDetails user) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + daysInMillis(30)))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	private String getToken(Map<String, Object> extraClaims, User user) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
//				.setSubject(user.getUsername())
				.setSubject(user.getEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + daysInMillis(30)))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
//	private Key getKey() {
//		byte[] keyBytes = Base64.decodeBase64(SECRET_KEY);
//		SecretKey key = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA512");
//		return key;
//	}


	@Override
	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		boolean valid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
		return valid;
	}
	
	private Claims getAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Date getExpiration(String token) {
		Date date = getClaim(token, Claims::getExpiration);
		System.out.println("EXPIRATION: " + date);
		return date;
//		return getClaim(token, Claims::getExpiration);
	}
	
	private boolean isTokenExpired(String token) {
		return getExpiration(token).before(new Date() );
	}
	
	private long daysInMillis(int days) {
		long l = 1000 * 60 * 60 * 24 * Long.valueOf(days);
		return l;
	}
	
	private long hoursInMillis(int hours) {
		long l = 1000 * 60 * 60 * Long.valueOf(hours);
		return l;
	}

}
