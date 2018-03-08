package net.fanzhiwei.core.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.fanzhiwei.util.Constants.TOKEN_CREATED_TIME;
import static net.fanzhiwei.util.Constants.USER_ID;

public class JwtUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JwtUtil.class);
    public static final long EXPIRATION_TIME = 3600_000_000L; // 1000 hour
    //public static final long EXPIRATION_TIME = 10_000L; // 1000 hour
    public static final String SECRET = "Oaw4JDPUAZnKIOTTvipmT7N87GsJ8FEuBiOfQ0NcdDJyfBmZVXaxnqNYvWNE";
    public static final String AUTHORIZATION = "Authorization";

    public static String generateToken(String userId) {
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put(USER_ID, userId);
        map.put(TOKEN_CREATED_TIME, System.currentTimeMillis());
        String jwt = Jwts.builder()
            .setClaims(map)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
        return jwt;
    }

    public static HttpServletRequest validateTokenAndAddUserIdToAttr(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        if (token != null) {
            // parse the token.
            try {
                Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
                //TODO 需要考虑修改密码的情况，判断数据库密码修改时间在token创建时间之后时，抛出token过期错误

                //设置token属性值到request attr
                //TODO 提示，controller层需要登录拦截的接口的userId都可以从这个属性中获得，request.getAttribute(Constants.USER_ID)
                body.forEach((k, v) -> request.setAttribute(k, v));
                return request;
                //return new CustomHttpServletRequest(request, body);
            } catch (ExpiredJwtException e) {
                throw e;
            } catch (Exception e) {
                throw new TokenValidationException(e.getMessage());
            }
        } else {
            throw new MissingTokenException("Missing token");
        }
    }

    ///**
    // * 自定义将jwt变量写入request header，方便controller层获取
    // */
    //public static class CustomHttpServletRequest extends HttpServletRequestWrapper {
    //    private Map<String, String> claims;
    //
    //    public CustomHttpServletRequest(HttpServletRequest request, Map<String, ?> claims) {
    //        super(request);
    //        this.claims = new HashMap<>();
    //        claims.forEach((k, v) -> this.claims.put(k, String.valueOf(v)));
    //    }
    //
    //    @Override
    //    public Enumeration<String> getHeaders(String name) {
    //        if (claims != null && claims.containsKey(name)) {
    //            return Collections.enumeration(Arrays.asList(claims.get(name)));
    //        }
    //        return super.getHeaders(name);
    //    }
    //
    //    public Map<String, String> getClaims() {
    //        return claims;
    //    }
    //}

    public static class TokenValidationException extends RuntimeException {
        public TokenValidationException(String msg) {
            super(msg);
        }
    }

    public static class MissingTokenException extends RuntimeException {
        public MissingTokenException(String msg) {
            super(msg);
        }
    }
}