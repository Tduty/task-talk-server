package info.tduty.typetalkserver.service.rest;

import info.tduty.typetalkserver.data.dto.UserDTO;
import info.tduty.typetalkserver.domain.interactor.UserInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/principal")
public class UserController {

    private UserInteractor userInteractor;

    @Autowired
    public UserController(UserInteractor userInteractor) {
        this.userInteractor = userInteractor;
    }

    @GetMapping
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }

    @GetMapping(value = "/auth")
    public UserDTO getUser(Principal principal) {
        return userInteractor.getUser(principal.getName());
    }
}
