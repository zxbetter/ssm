package devin.spittr.service.impl;

import devin.spittr.data.SpittleRepository;
import devin.spittr.dto.Spittle;
import devin.spittr.service.SpittleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author devin
 */
@Service("spittleService")
public class SpittleServiceImpl implements SpittleService {

    @Autowired
    private SpittleRepository spittleRepository;

    @Override
    public List<Spittle> findSpittles(Long max, int count) {
        return spittleRepository.findSpittles(max, count);
    }

    @Override
    public Spittle findOne(Long spittleId) {
        return spittleRepository.findOne(spittleId);
    }
}
