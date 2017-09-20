package uet.hungnh.ci.service;

import uet.hungnh.ci.common.exception.ServiceException;
import uet.hungnh.ci.persistence.entity.AppUser;

import java.util.List;

public interface IUserService {
    AppUser getById(Long id) throws ServiceException;

    AppUser create(AppUser user);

    List<AppUser> getAllUsers();
}
