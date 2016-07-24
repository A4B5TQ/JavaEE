package forum.configs;

import forum.auth.CurrentUser;
import forum.entitys.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class RequestProcessingInterceptor extends HandlerInterceptorAdapter {

  /*  private static final Logger logger = LoggerFactory
            .getLogger(RequestProcessingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info("Request URL::" + request.getRequestURL().toString()
                + ":: Start Time=" + System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
        //if returned false, we need to make sure 'response' is sent
        return true;
    }*/

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        SecurityContext sc = SecurityContextHolder.getContext();

        Boolean isAdmin = false;
        String userName = "";
        if (sc != null) {
            Authentication auth = sc.getAuthentication();
            if (auth != null) {
                Object principal = auth.getPrincipal();
                if (principal != null && principal instanceof CurrentUser) {
                    CurrentUser user = (CurrentUser) principal;
                    userName = user.getUsername();
                    isAdmin = Arrays.stream(user.getRoles())
                            .anyMatch(r -> r.equals(Role.ADMIN));
                }
            }
        }
        modelAndView.getModelMap().addAttribute("isAdmin", isAdmin);
        modelAndView.getModelMap().addAttribute("userName", userName);

    }

    /*@Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        logger.info("Request URL::" + request.getRequestURL().toString()
                + ":: End Time=" + System.currentTimeMillis());
        logger.info("Request URL::" + request.getRequestURL().toString()
                + ":: Time Taken=" + (System.currentTimeMillis() - startTime));
    }*/

}