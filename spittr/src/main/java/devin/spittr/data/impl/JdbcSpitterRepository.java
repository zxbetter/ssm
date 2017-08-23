package devin.spittr.data.impl;

import devin.spittr.data.SpitterRepository;
import devin.spittr.dto.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

/**
 * @author devin
 */
@Repository("spitterRepository")
public class JdbcSpitterRepository implements SpitterRepository {

    // 使用 jdbcOptions 使 repository 与具体 jdbcTemplate 保持松耦合.
    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    @Override
    public int save(Spitter spitter) {
        // jdbcOperations.update();
        return 0;
    }

    @Override
    public Spitter findByUsername(String username) {
        return null;
    }
}
