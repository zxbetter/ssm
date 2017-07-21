package com.devin.demo.controller;

import com.devin.demo.model.Demo;
import com.devin.demo.model.ResponseData;
import com.devin.demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * DemoController
 * <p> 提供Demo API
 *
 * @author devin
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private IDemoService demoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseData<Demo> selectByPrimaryKey(@PathVariable("id") Long id) {
        Demo demo = demoService.selectByPrimaryKey(id);
        return new ResponseData<>().setData(demo);
    }
}
