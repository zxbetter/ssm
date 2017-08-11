package devin.demo.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * baseMapper
 * <p> 这里定义了通用 Mapper 的方法
 *
 * @author devin
 */
public interface BaseMapper<T> {

    /**
     * 插入单条记录
     * @param t 要插入的对象
     * @return 受影响行数
     */
    int insert(T t);

    /**
     * 删除单条记录
     * @param t 要删除的对象
     * @return 受影响的行数
     */
    int delete(T t);

    /**
     * 更新单条记录
     * @param t 要更新的对象
     * @return 受影响的行数
     */
    int update(T t);

    /**
     * 通过主键 ID 查询单条记录
     * @param id 主键 ID
     * @return 查询到的记录
     */
    T selectById(Long id);
}
