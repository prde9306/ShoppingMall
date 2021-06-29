package com.mybatis.shoppingmall.order;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SequenceRepository {
    Sequence getSequence(Sequence sequence);

    void updateSequence(Sequence sequence);
}
