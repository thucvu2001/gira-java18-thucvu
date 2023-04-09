package cybersoft.javabackend.java18.gira.security.service;


import cybersoft.javabackend.java18.gira.role.model.Role;
import cybersoft.javabackend.java18.gira.role.repository.RoleRepository;
import cybersoft.javabackend.java18.gira.security.dto.AuthRequestDTO;
import cybersoft.javabackend.java18.gira.security.dto.AuthResponseDTO;
import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDTO.getUsername(),
                        authRequestDTO.getPassword()
                )
        );

        User user = userRepository.findByUsername(authRequestDTO.getUsername()).orElseThrow();

        Set<Role> roleSet = new HashSet<>();

        if (user != null) {
            roleRepository.findAllRolesByUsername(user.getUsername())
                    .forEach(role -> roleSet.add(role));
        }
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roleSet.stream().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        String jwtToken = jwtService.generateToken(user, authorities);
        String jwtRefreshToken = jwtService.generateRefreshToken(user, authorities);

        return AuthResponseDTO.builder()
                .token(jwtToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }
}
