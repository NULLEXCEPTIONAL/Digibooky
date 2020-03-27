//package com.nullexceptional.digibooky.domain.security.authentication.external;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class UserAuthenticationProvider implements AuthenticationProvider {
//    private final FakeAuthenticationService fakeAuthenticationService;
//
//    public UserAuthenticationProvider(FakeAuthenticationService fakeAuthenticationService) {
//        this.fakeAuthenticationService = fakeAuthenticationService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String inss=authentication.getPrincipal().toString();
//        String email=authentication.getCredentials().toString();
//
//        ExternalAuthentication user=fakeAuthenticationService
//                .getUser(inss,email)
//                .orElseThrow(()-> new BadCredentialsException("The provided credentials are invalid"));
//
//        return new UsernamePasswordAuthenticationToken(user.getInss(),user.getEmail(),rolesToGrantedAuthorities(user.getRoles()));
//    }
//
//    private Collection<? extends GrantedAuthority> rolesToGrantedAuthorities(List<String> roles) {
//        return roles.stream()
//                .map(SimpleGrantedAuthority::new) //for every roles convert it in grantedauthority
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
