package com.ilyozzz.novelsbio.security;

import com.ilyozzz.novelsbio.repository.RoleRep;
import com.ilyozzz.novelsbio.repository.UserRep;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService implements UserDetailsService {
    private final UserRep userRepository;

    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;
    private final RoleRep roleRepository;

    public AuthService(@Lazy UserRep userRepository, MessageSource messageSource, PasswordEncoder passwordEncoder, RoleRep roleRepository) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumberOrEmail) throws UsernameNotFoundException {
        return userRepository.findByUserName(phoneNumberOrEmail).orElseThrow(() -> new UsernameNotFoundException(phoneNumberOrEmail));
    }

    public UserDetails loadUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User id not found: " + userId));
    }
}
