package com.beneu.beneuprod.core.repository.impl;

import com.beneu.common.core.model.BaseModel;
import com.beneu.common.core.repository.impl.BaseRepositoryImpl;
import com.beneu.common.dal.entity.BaseDo;
import java.io.Serializable;

/**
 * <Description>:
 *
 * @author beneu
 * @version 1.0
 * @createDate 2022/5/22 16:41
 */
public abstract class BaseBizRepositoryImpl<M extends BaseModel<ID>, D extends BaseDo<ID>, ID extends Serializable> extends BaseRepositoryImpl<M, D, ID> {

    @Override
    protected String[] getIgnoreProperties() {
        return new String[]{"extendField", "deleted", "attachment"};
    }
}
