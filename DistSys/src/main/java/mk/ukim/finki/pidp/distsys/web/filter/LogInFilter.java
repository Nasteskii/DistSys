//package mk.ukim.finki.pidp.distsys.web.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.pidp.distsys.model.Client;
//
//import java.io.IOException;
//
//@WebFilter
//public class LogInFilter  implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        Client client = (Client) request.getSession().getAttribute("user");
//        String path = request.getServletPath();
//
//        if (!"/login".equals(path) &&
//                !"/h2".equals(path) &&
//                !"/register".equals(path) &&
//                !"/main.css".equals(path) && client==null) {
//            response.sendRedirect("/login");
//        } else {
//            filterChain.doFilter(servletRequest,servletResponse);
//        }
//
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
