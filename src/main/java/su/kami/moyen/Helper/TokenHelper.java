package su.kami.moyen.Helper;

import io.jsonwebtoken.*;

import java.util.Date;

public class TokenHelper {

    static String sign = "BlagodariaIzzaOtsukaMoooreLetUsPlayGenshinImpact";

    public static String Create(String info){
        return Jwts.builder()
                .setSubject("AUTH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .claim("info", info)
                .signWith(SignatureAlgorithm.HS256, sign)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    public static boolean Verify(String token, String info){
        try {
            if(token.equals("") || info.equals("")) return false;
            var claim = Jwts.parser().setSigningKey(sign).parseClaimsJws(token).getBody();
            return claim.get("info").equals(info);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

}
