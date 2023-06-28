package com.example.dietApplication.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // セッションのチェックを実装する
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        if (user == null) {
            // ログインしていない場合はリダイレクトなどの処理を行う
            response.sendRedirect("/user-login");
            return false;
        }

        return true;
    }
}
