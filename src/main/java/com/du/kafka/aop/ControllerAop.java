package com.du.kafka.aop;

import com.du.kafka.constant.CommonConstant;
import com.du.kafka.constant.PromptConstant;
import com.du.kafka.model.sys.JsonResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回参数json格式的动态增强
 *
 * @author dxy
 */
@Aspect
@Component
public class ControllerAop {
    private final static Logger logger = LoggerFactory.getLogger(ControllerAop.class);
    /**
     * 相应json返回格式切点,返回格式增强
     * 该切点只会拦截有ResponseBody注解的方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void responseBodyDataToMap() {
    }

    /**
     * 相应json返回格式切点,返回格式增强
     * 该切点拦截类上有@RestController注解下的所有方法
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerDataToMap() {
    }

    /**
     * controller 返回json格式的动态增强,转换为map
     *
     */
    @Around("responseBodyDataToMap() || restControllerDataToMap()")
    public Object controllerAfter (ProceedingJoinPoint joinPoint) {
        Map<String, Object> result = new HashMap<>(3);
        try {
            Object proceed = joinPoint.proceed();
            if (proceed instanceof Exception) {
                Exception ex = (Exception) proceed;
                logger.error(PromptConstant.AOP_EXCEPTION, ex);

                result.put(CommonConstant.CODE, 1);
                result.put(CommonConstant.MSG, PromptConstant.FAIL);
                result.put(CommonConstant.DATA, null);
            } else if (proceed instanceof JsonResponse) {
                JsonResponse jsonResponse = (JsonResponse) proceed;
                return jsonResponse;
            } else if (proceed instanceof ResponseEntity) {
                return proceed;
            } else {
                result.put(CommonConstant.CODE, 0);
                result.put(CommonConstant.MSG, PromptConstant.SUCCESS);
                result.put(CommonConstant.DATA, proceed);
            }
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error(PromptConstant.AOP_JSON_ERROR, throwable);
            result.put(CommonConstant.CODE, -1);
            result.put(CommonConstant.MSG, PromptConstant.SYSTEM_ERROR);
            result.put(CommonConstant.DATA,null);
            return result;
        }
    }

}
