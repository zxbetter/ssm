package devin.spittr.data.impl;

import devin.spittr.data.SpittleRepository;
import devin.spittr.dto.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author devin
 */
@Repository("spittleRepository")
public class jdbcSpittleRepository implements SpittleRepository {

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    @Override
    public List<Spittle> findSpittles(Long max, int count) {
        return null;
    }

    @Override
    public Spittle findOne(Long spittleId) {
        return null;
    }
}
