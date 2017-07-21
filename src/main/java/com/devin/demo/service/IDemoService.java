package com.devin.demo.service;


import com.devin.demo.model.Demo;

/**
 * Demo 相关的操作
 *
 * @author devin
 */
public interface IDemoService {

    /**
     * 根据用户的主键 ID，查询用户的信息
     * @param id 主键 ID
     * @return 用户对象
     */
    Demo selectByPrimaryKey(Long id);
}
