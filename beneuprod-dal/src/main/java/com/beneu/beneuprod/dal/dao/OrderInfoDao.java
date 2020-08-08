package com.beneu.beneuprod.dal.dao;

import com.beneu.beneuprod.dal.entity.OrderInfoDo;
import com.beneu.common.dal.dao.IBaseDeletedDao;
import org.apache.ibatis.annotations.Update;

import java.io.Serializable;

/**
 * <Description>: 自动生成的Do实体
 *
 * @author sdalgen
 * @version 1.0
 * @createDate 2020-5-31 21:18:11
 */
public interface OrderInfoDao extends IBaseDeletedDao<OrderInfoDo> {

    @Override
    @Update("update order_info set modify_time=now(3), deleted='T' where id=#{id}")
    public int logicDeleteById(Serializable id);

}
