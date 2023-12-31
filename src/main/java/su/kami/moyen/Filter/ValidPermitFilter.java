package su.kami.moyen.Filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import su.kami.moyen.Helper.TokenHelper;
import su.kami.moyen.Model.Return;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidPermitFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        if ("/login".equals(httpServletRequest.getRequestURI())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        UsernamePasswordAuthenticationToken authentication;
        if(httpServletRequest.getHeader("token") == null) authentication = null;
                else authentication = TokenHelper.Verify(httpServletRequest.getHeader("token"), "genshin") ?
                        new UsernamePasswordAuthenticationToken("root", null, null) : null; // ??? todo ???
//        System.out.println("Received new request with: " + httpServletRequest.getHeader("token"));
        if (null != authentication) {
            // 授权通过 放行
//            authentication.setAuthenticated(true);
//            System.out.println("auth: of: " + authentication + "   Validation: " + TokenHelper.Verify(httpServletRequest.getHeader("token"), "genshin"));

            SecurityContextHolder.getContext().setAuthentication(authentication );
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            // 授权不通过 返回权限不足
            new Return<>().New(400, "Unable to reach");
        }
    }
}
