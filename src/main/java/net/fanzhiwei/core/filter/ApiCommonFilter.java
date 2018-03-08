package net.fanzhiwei.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import net.fanzhiwei.core.mvc.ApiResult;
import net.fanzhiwei.core.util.JwtUtil;
import net.fanzhiwei.core.util.JwtUtil.MissingTokenException;
import net.fanzhiwei.core.util.JwtUtil.TokenValidationException;
import net.fanzhiwei.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 通过api接口filter，拦截所有api接口，做日志记录与jwt拦截校验
 * @author fanzhiwei
 */
@Configuration
public class ApiCommonFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger("api");
    private static final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws
        ServletException, IOException {
        try {
            if (pathMatcher.matchStart(request.getServletPath(), "/api/")) {
                Object userId = request.getAttribute(Constants.USER_ID);
                //请求含有jwt路径时，做登录校验
                if (pathMatcher.match("/**/jwt/**", request.getServletPath())) {
                    request = JwtUtil.validateTokenAndAddUserIdToAttr(request);
                }

                //TODO 记录接口日志到指定日志文件api.log
                LOG.info("aa11111111");
            }
        } catch (ExpiredJwtException e) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(ApiResult.createErrorInstance(901, "expired token")));
            return;
        } catch (MissingTokenException e) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(ApiResult.createErrorInstance(902, "missing token")));
            return;
        } catch (TokenValidationException e) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(ApiResult.createErrorInstance(903, "invalid token")));
            return;
        }
        filterChain.doFilter(request, response);
    }
}