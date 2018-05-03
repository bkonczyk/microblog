package pl.bbkon.microblog.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/unauth/credentials")
    public String getCredentials() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String result = authentication.getName() + ", roles: ";
        result += authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .reduce("", (a, b) -> a + ", " + b);
        return result;
    }

    @PostMapping("/auth/login")
    public ResponseEntity login() {
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/auth/login")
    public ResponseEntity checkLogin() {
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/unauth/register")
    public ResponseEntity<User> register(@RequestBody @Valid CreatePersonRequest request) {
        return ResponseEntity.ok(userService.create(request));
    }
}