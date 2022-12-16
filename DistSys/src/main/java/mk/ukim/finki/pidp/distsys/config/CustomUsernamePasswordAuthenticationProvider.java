package mk.ukim.finki.pidp.distsys.config;//package mk.ukim.finki.pidp.distsys.config;
//
//import mk.ukim.finki.pidp.distsys.model.Client;
//import mk.ukim.finki.pidp.distsys.service.ClientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
//@Component
//public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
//
//    private final ClientService clientService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Lazy
//    public CustomUsernamePasswordAuthenticationProvider(ClientService clientService, PasswordEncoder passwordEncoder) {
//        this.clientService = clientService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        if ("".equals(username) || "".equals(password)) {
//            throw new BadCredentialsException("Invalid Credentials");
//        }
//
//        Client userDetails = this.clientService.loadUserByUsername(username);
//
//        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//            throw new BadCredentialsException("Password is incorrect!");
//        }
//        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), (Collection<? extends GrantedAuthority>) userDetails.getRole());
//
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return aClass.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
