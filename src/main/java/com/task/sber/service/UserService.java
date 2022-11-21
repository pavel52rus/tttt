package com.task.sber.service;

import com.task.sber.entity.Permission;
import com.task.sber.entity.User;
import com.task.sber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Set;

@RequiredArgsConstructor
@Component("userDetailsService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with login '%s' not found", username)));

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                mapCheckPermissions(user.getCheckPermissions())
        );
    }

    private Collection<? extends GrantedAuthority> mapCheckPermissions(Set<Permission> CheckPermissions) {
        return CheckPermissions.stream().map(p -> new SimpleGrantedAuthority(p.getName())).toList();
    }
}
