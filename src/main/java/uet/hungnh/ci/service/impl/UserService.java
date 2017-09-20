package uet.hungnh.ci.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uet.hungnh.ci.common.exception.ExceptionMessage;
import uet.hungnh.ci.common.exception.ServiceException;
import uet.hungnh.ci.persistence.entity.AppUser;
import uet.hungnh.ci.persistence.repository.AppUserRepository;
import uet.hungnh.ci.service.IUserService;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserService implements IUserService {

    private final AppUserRepository userRepository;

    @Autowired
    public UserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AppUser getById(Long id) throws ServiceException {
        AppUser user = userRepository.findOne(id);
        if (user == null) {
            throw new ServiceException(ExceptionMessage.USER_NOT_FOUND);
        }
        return user;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public AppUser create(AppUser user) {
        return userRepository.save(user);
    }
}
