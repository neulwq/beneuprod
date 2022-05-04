package com.beneu.beneuprod.dal.dao;

import com.beneu.common.dal.dao.IBaseDeletedDao;
import com.beneu.beneuprod.dal.entity.PayTcTradeDo;
import org.apache.ibatis.annotations.Update;
import java.io.Serializable;

/**
* <Description>: 自动生成的Do实体
*
* @author sdalgen
* @version 1.0
* @createDate 2022-4-28 23:53:53
*/
public interface PayTcTradeDao extends IBaseDeletedDao<PayTcTradeDo> {

    @Override
    @Update("update pay_tc_trade set modify_time=now(3), deleted='T' where id=#{id}")
    public int logicDeleteById(Serializable id);

}
