package devin.spittr.data.impl;

import devin.spittr.data.SpittleRepository;
import devin.spittr.dto.Spittle;
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
@Repository("spittleRepository")
public class JdbcSpittleRepositoryImpl implements SpittleRepository {

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    /** 标准的数据库列 */
    private static final String BASE_COLUMN_LIST =
            "MESSAGE, TIME, LATITUDE, LONGITUDE";

    /** 查询 ID 小于 max 的 count 条 spittle */
    private static final String FIND_SPITTLES =
            "SELECT ID, " + BASE_COLUMN_LIST +
                    " FROM spittle WHERE ID < :max ORDER BY ID DESC LIMIT 0, :count";

    @Override
    public List<Spittle> findSpittles(Long max, int count) {
        Map<String, Object> param = new HashMap<>();
        param.put("max", max);
        param.put("count", count);

        return jdbcOperations.query(FIND_SPITTLES, param, rs -> {
            return BeanUtils.resultSetToBean(rs, Spittle.class);
        });
    }

    /** 根据 id 查询的 SQL 语句 */
    private static final String FIND_ONE =
            "SELECT ID, " + BASE_COLUMN_LIST + " FROM spittle WHERE ID = :id";

    @Override
    public Spittle findOne(Long spittleId) {
        Map<String, Long> param = new HashMap<>();
        param.put("id", spittleId);

        return jdbcOperations.query(FIND_ONE, param, rs -> {
            List<Spittle> spittles = BeanUtils.resultSetToBean(rs, Spittle.class);
            if (null != spittles && spittles.size() > 0) {
                return spittles.get(0);
            }
            return null;
        });
    }
}
