package pl.edu.agh.ietanks.server;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @RequestMapping(value = "/auth/github", method = RequestMethod.POST)
    public void loginWithGithub(@RequestBody AuthResult authResult) {
        // TODO: Generate and save token
    }
}
