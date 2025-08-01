package shaadProject.example.delivery.partner.app.JTWconfigruation;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class jtwService {

    private String secretKey = "aGVsbG9fZnJvbV9DaGF0R1BUX2p3dF9zZWNyZXRfa2V5XzI1Ng==";



 public String getToken(UserDetails userDetails){
     Map<String,Object> claim = new HashMap<>();
     claim.put("role", userDetails.getAuthorities().iterator().next().getAuthority());
     claim.put("email", userDetails.getUsername());
//     claim.put("role", userDetails.getAuthorities().iterator().next().getAuthority());
//     claim.put("email", email);
     return Jwts.builder()
             .claims()
             .add(claim)
             .subject(userDetails.getUsername())
             .issuedAt(new Date(System.currentTimeMillis()))
             .expiration(new Date(System.currentTimeMillis()+1000*60*60*10))
             .and()
             .signWith(getKey())
             .compact();
 }

 public SecretKey getKey(){
     byte[] keyBytes = Decoders.BASE64.decode(secretKey);
     return Keys.hmacShaKeyFor(keyBytes);
 }

    public String extractUsername(String token) {

            return extractClaim(token, Claims::getSubject); // ✅ subject = email
        }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getKey()) // your secret key here
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public boolean validToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, claims -> claims.getExpiration());
    }
}

//    public jtwService(){
//
//            try {
//                KeyGenerator keygen =KeyGenerator.getInstance("HmacSHA256");
//                SecretKey sk = keygen.generateKey();
//                secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//            } catch (NoSuchAlgorithmException e) {
//                throw new RuntimeException(e);
//            }
//
//    }
