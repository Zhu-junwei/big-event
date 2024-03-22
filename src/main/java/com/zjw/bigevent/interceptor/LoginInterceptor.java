package com.zjw.bigevent.interceptor;

import com.zjw.bigevent.utils.JwtUtil;
import com.zjw.bigevent.utils.RedisUtil;
import com.zjw.bigevent.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * 登录拦截器
 * @author 朱俊伟
 * @since 2024/03/15 22:06
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Resource
    private RedisUtil redisUtil;

    /**
     * 登录前校验token，并将解析后的token放入ThreadLocal中
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginToken = request.getHeader("Authorization");
        try {
            Map<String, Object> claim = JwtUtil.parseToken(loginToken);
            Integer id = (Integer) claim.get("id");
            String username = (String) claim.get("username");
            String redisToken = redisUtil.getString("token:" + id + ":" + username);
            if (!loginToken.equals(redisToken)){
                throw new RuntimeException("token校验失败");
            }
            ThreadLocalUtil.set(claim);
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
        return true;
    }

    /**
     * 登录后清理ThreadLocal中的token
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
