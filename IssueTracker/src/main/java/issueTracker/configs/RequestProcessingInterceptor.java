package issueTracker.configs;

import issueTracker.auth.CurrentUser;
import issueTracker.entities.User;
import issueTracker.enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class RequestProcessingInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        SecurityContext sc = SecurityContextHolder.getContext();

        Boolean isAdmin = false;
        User user = null;
        if (sc != null) {
            Authentication auth = sc.getAuthentication();
            if (auth != null) {
                Object principal = auth.getPrincipal();
                if (principal != null && principal instanceof CurrentUser) {
                    CurrentUser currentUser = (CurrentUser) principal;
                    user = currentUser.getUser();
                    isAdmin = Arrays.stream(currentUser.getRoles())
                            .anyMatch(r -> r.equals(Role.ADMIN));
                }
            }
        }
        if (user != null && modelAndView != null) {
            modelAndView.addObject("isAdmin", isAdmin);
            modelAndView.addObject("username", user.getUsername());
            String viewName = modelAndView.getViewName();
            if (viewName.contains("redirect")) {
                int redirectIndex = viewName.indexOf(":");
                String redirectLink = viewName.substring(redirectIndex + 1);
                RedirectView rv = new RedirectView(redirectLink, true);
                rv.setExposeModelAttributes(false);
                modelAndView.setView(rv);
            }
        }

    }
}