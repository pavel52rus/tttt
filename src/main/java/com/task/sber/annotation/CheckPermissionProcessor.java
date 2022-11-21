package com.task.sber.annotation;

import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CheckPermissionProcessor extends WebRequestHandlerInterceptorAdapter {

    public CheckPermissionProcessor(WebRequestInterceptor requestInterceptor) {
        super(requestInterceptor);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            List<CheckPermission> CheckPermissions = new ArrayList<>();

            CheckPermission CheckPermissionMethodAnnotation = handlerMethod.getMethodAnnotation(CheckPermission.class);
            if (CheckPermissionMethodAnnotation != null) {
                CheckPermissions.add(CheckPermissionMethodAnnotation);
            }


            Class<?> controllerClass = handlerMethod.getBeanType();
            CheckPermission CheckPermissionClassAnnotation = controllerClass.getAnnotation(CheckPermission.class);
            if (CheckPermissionClassAnnotation != null) {
                CheckPermissions.add(CheckPermissionClassAnnotation);
            }

            if (CheckPermissions.isEmpty()) {
                return true;
            } else {
                // @todo check with handle, connect to mvc config
//                return CheckPermissionManager.handle(request, response, CheckPermissions);
            }
        }
        return true;
    }
}
