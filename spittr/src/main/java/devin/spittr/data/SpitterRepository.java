package devin.spittr.data;

import devin.spittr.dto.Spitter;

/**
 * @author devin
 */
public interface SpitterRepository {

    /**
     * 插入用户
     * @param spitter
     * @return
     */
    int save(Spitter spitter);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    Spitter findByUsername(String username);
}
