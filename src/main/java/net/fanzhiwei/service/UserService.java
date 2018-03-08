package net.fanzhiwei.service;

import net.fanzhiwei.dao.domain.mbg.User;

/**
 * @author fanzhiwei
 */
public interface UserService {
    /**
     * 取用户信息
     * @return
     */
    User getUserModelByUsername(String user);
}
