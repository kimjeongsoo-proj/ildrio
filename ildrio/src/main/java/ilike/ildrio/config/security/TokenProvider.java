package ilike.ildrio.config.security;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenProvider {
	
	//JWT 생성 및 검증을 위한 키
	private static final String SECURITY_KEY = "jwtseckey!@0";
	
	// JWT 생성하는 매서드
	public String create (String buyerId) {
		
		// 만료날짜를 현재날짜 + 1시간으로 설정
		Date expireTime = Date.from(Instant.now().plus(1,ChronoUnit.HOURS));
		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
				.setSubject(buyerId)
				.setIssuedAt(new Date())
				.setExpiration(expireTime)
				.compact();
	}
	
	public String validate (String token) {
		Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
		
		return claims.getSubject();
	}

}
