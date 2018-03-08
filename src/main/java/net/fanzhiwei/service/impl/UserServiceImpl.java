package net.fanzhiwei.service.impl;

import net.fanzhiwei.dao.domain.mbg.User;
import net.fanzhiwei.dao.domain.mbg.UserExample;
import net.fanzhiwei.dao.mapper.mbg.UserMapper;
import net.fanzhiwei.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fanzhiwei
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserModelByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> list = userMapper.selectByExample(example);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
