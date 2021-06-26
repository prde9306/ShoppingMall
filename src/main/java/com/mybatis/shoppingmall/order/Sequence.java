package com.mybatis.shoppingmall.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sequence implements Serializable {

    private static final long serialVersionUID = 8278780133180137281L;

    private String name;

    private int nextId;
}
