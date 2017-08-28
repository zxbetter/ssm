package devin.spittr.data.impl;

import devin.spittr.data.SpitterRepository;
import devin.spittr.dto.Spitter;
import devin.spittr.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author devin
 */
@Repository("spitterRepository")
public class JdbcSpitterRepositoryImpl implements SpitterRepository {

    /** 使用 jdbcOptions 使 repository 与具体 jdbcTemplate 保持松耦合. */
    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    /** 标准的数据库列 */
    private static final String BASE_COLUMN_LIST =
            "FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL, UPDATE_BY_EMAIL";

    /** 插入的 SQL 语句 */
    private static final String INSERT_SQL = "INSERT INTO spitter " +
            "(" + BASE_COLUMN_LIST + ") VALUES " +
            "(:firstName, :lastName, :username, :password, :email, :updateByEmail)";

    @Override
    public int save(Spitter spitter) {
        return jdbcOperations.update(INSERT_SQL, BeanUtils.convertToMap(spitter));
    }

    /** 根据 username 查询的 SQL 语句 */
    private static final String FIND_BY_USERNAME_SQL = "SELECT ID, " + BASE_COLUMN_LIST + " FROM spitter " +
            "WHERE USERNAME = :username";

    @Override
    public Spitter findByUsername(String username) {
        Map<String, String> param = new HashMap<>();
        param.put("username", username);

        return jdbcOperations.query(FIND_BY_USERNAME_SQL, param, rs -> {
            List<Spitter> spitters = BeanUtils.resultSetToBean(rs, Spitter.class);
            if (null != spitters && spitters.size() > 0) {
                return spitters.get(0);
            }
            return null;
        });
    }
}
