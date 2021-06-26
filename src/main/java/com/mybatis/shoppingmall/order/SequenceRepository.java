package com.mybatis.shoppingmall.order;

public interface SequenceRepository {
    Sequence getSequence(Sequence sequence);

    void updateSequence(Sequence sequence);
}
