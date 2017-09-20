package uet.hungnh.ci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uet.hungnh.ci.common.exception.ServiceException;
import uet.hungnh.ci.persistence.entity.AppUser;
import uet.hungnh.ci.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public AppUser getById(@PathVariable("id") Long id) throws ServiceException {
        return userService.getById(id);
    }

    @PostMapping
    public AppUser create(@RequestBody AppUser user) {
        return userService.create(user);
    }
}
