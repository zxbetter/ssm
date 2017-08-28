package devin.spittr.data;

import devin.spittr.dto.Spittle;

import java.util.List;

/**
 * @author devin
 */
public interface SpittleRepository {
    /**
     * 查询 ID 小于 max 的 count 条 spittle
     * @param max
     * @param count
     * @return
     */
    List<Spittle> findSpittles(Long max, int count);

    /**
     * 根据 ID 查询 spittle 的信息
     * @param spittleId
     * @return
     */
    Spittle findOne(Long spittleId);
}
