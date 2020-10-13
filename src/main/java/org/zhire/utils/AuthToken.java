package org.zhire.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AuthToken {

    private static final String secretKey = "SECRET_KEY";
    private static final String issUser = "user";

    /**
     * 创建Token
     *
     * @param claims Map<String,String>
     * @return
     * @throws Exception
     */
    public String createToken(Map<String, String> claims) throws Exception {
        try {
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(issUser)
                    .withExpiresAt(DateUtils.addDays(new Date(), 365));
            claims.forEach(builder::withClaim);
            String token = builder.sign(Algorithm.HMAC256(secretKey));
            return token;
        } catch (Exception e) {
            log.error(String.format("创建token失败"));
            throw new RuntimeException("创建token失败");
        }
    }

    /**
     * 验证Token
     *
     * @param sign String 签名
     * @throws Exception
     */
    public Map<String, String> verify(String sign) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issUser).build();
            DecodedJWT jwt = verifier.verify(sign);
            Map<String, Claim> claims = jwt.getClaims();
            Map<String, String> resultMap = new HashMap<>(claims.size());
            claims.forEach((k, v) -> resultMap.put(k, v.asString()));
            return resultMap;
        } catch (Exception e) {
            throw new RuntimeException("token验证失败");
        }
    }
}
