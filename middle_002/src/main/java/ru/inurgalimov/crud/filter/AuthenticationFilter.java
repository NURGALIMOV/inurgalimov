package ru.inurgalimov.crud.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (!req.getRequestURI().contains("/signin")) {
            if (Objects.isNull(req.getSession().getAttribute("login"))) {
                ((HttpServletResponse) response).sendRedirect(String.format("%s/signin", req.getContextPath()));
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
