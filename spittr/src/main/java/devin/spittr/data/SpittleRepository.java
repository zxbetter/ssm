package devin.spittr.data;

import devin.spittr.dto.Spittle;

import java.util.List;

/**
 * @author devin
 */
public interface SpittleRepository {
    List<Spittle> findSpittles(Long max, int count);
    Spittle findOne(Long spittleId);
}
