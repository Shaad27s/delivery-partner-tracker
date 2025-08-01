package shaadProject.example.delivery.partner.app.JTWconfigruation;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import shaadProject.example.delivery.partner.app.securityConfig.UserDtl;

import java.io.IOException;

@Component
public class jtwFilter extends OncePerRequestFilter {

    @Autowired
    private jtwService jtwservice;

   @Autowired
   private UserDtl userDtl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String userName;
        String Token;


        if(authHeader!=null && authHeader.startsWith("Bearer")){
            Token = authHeader.substring(7);
            userName = jtwservice.extractUsername(Token);
            if(userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null){

                UserDetails userDetails = userDtl.loadUserByUsername(userName);

                if(jtwservice.validToken(Token,userDetails)){
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
