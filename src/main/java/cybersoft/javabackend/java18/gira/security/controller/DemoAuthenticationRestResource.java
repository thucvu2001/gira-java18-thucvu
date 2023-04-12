package cybersoft.javabackend.java18.gira.security.controller;

import cybersoft.javabackend.java18.gira.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/demo")
public class DemoAuthenticationRestResource {
    private final AuthenticationService authenticationService;

    @GetMapping("/test")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Authentication and Authorization is succeed");
    }
}
