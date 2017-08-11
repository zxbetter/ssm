package devin.demo.service.impl;

import devin.demo.mapper.DemoMapper;
import devin.demo.model.Demo;
import devin.demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * IDemoService 接口的实现类
 *
 * @author devin
 */
@Service("demoService")
public class DemoServiceImpl implements IDemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public Demo selectByPrimaryKey(Long id) {
        return demoMapper.selectByPrimaryKey(id);
    }
}
