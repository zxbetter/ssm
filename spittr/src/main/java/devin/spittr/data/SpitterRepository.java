package devin.spittr.data;

import devin.spittr.dto.Spitter;

/**
 * @author devin
 */
public interface SpitterRepository {
    int save(Spitter spitter);
    Spitter findByUsername(String username);
}
