package AuthSystem.Config;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
   private final JwtUtil jwtUtil;
    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader=request.getHeader("Authorization");
        if(authHeader==null||!authHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }
        String token=authHeader.substring(7);
        if(jwtUtil.validateToken(token)&&SecurityContextHolder.getContext().getAuthentication()==null){
            String username=jwtUtil.extractUsername(token); 
            String role=jwtUtil.extractRole(token);
            UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(username,null,List.of(new SimpleGrantedAuthority("Role_"+role)));
            authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);

             }
             filterChain.doFilter(request, response);
    }
    
}
