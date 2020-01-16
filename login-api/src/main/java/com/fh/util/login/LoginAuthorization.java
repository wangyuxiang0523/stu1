package com.fh.util.login;

import com.fh.model.VipPo;
import com.fh.util.JWT;
import com.fh.util.RedisPool;
import com.fh.util.exception.AuthenticateException;
import com.fh.util.response.ResponseServer;
import com.fh.util.response.ServerEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.Jedis;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Aspect
@Component
public class LoginAuthorization {


    @Around(value="execution(* com.fh.controller.*.*(..))&&@annotation(loginAnnotation)")
    public Object loginAround(ProceedingJoinPoint joinPoint, LoginAnnotation loginAnnotation){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Object obj=null;
        String token =request.getHeader("token");
        System.out.println(token);
        byte[] decode = Base64.getDecoder().decode(token);
        String s = new String(decode);
        String[] split = s.split("!");
       if (split.length!=2){
            //token有误
            throw new AuthenticateException(ServerEnum.SAFETY_ERROR);
        }
        String phone = split[0];
        String jwtToken = split[1];
        System.out.println(jwtToken);
        Jedis jedis = RedisPool.getJedis();
        String redisToken = jedis.get(phone);

        if(redisToken!=null){ //验证登录是否超时
            //验证token是不是最新的
            if(redisToken.equals(jwtToken)){
                //token正确   解析用户信息  放入request
                VipPo vipPo = JWT.unsign(jwtToken, VipPo.class);
                request.setAttribute("login_vip",vipPo);
                jedis.expire(vipPo.getPhone(),60*30);
                RedisPool.returnJedis(jedis);
            }else{
                throw new AuthenticateException(ServerEnum.VIP_LOGIN_ING);
            }
        } else {
           throw new AuthenticateException(ServerEnum.TOKEN_TIMEOUT);
        }


        try {
            obj=joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
}
