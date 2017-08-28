package devin.spittr.service.impl;

import devin.spittr.data.SpitterRepository;
import devin.spittr.dto.Spitter;
import devin.spittr.service.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author devin
 */
@Service("spitterService")
public class SpitterServiceImpl implements SpitterService {

    @Autowired
    private SpitterRepository spitterRepository;

    @Override
    public int save(Spitter spitter) {
        return spitterRepository.save(spitter);
    }

    @Override
    public Spitter findByUsername(String username) {
        return spitterRepository.findByUsername(username);
    }
}
