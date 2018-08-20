package com.wesley.bean.dto;
import lombok.Data;

@Data
public class GoodInfoDTO
{
    //商品编号
    private String goodId;
    //商品名称
    private String goodName;
    //商品价格
    private double goodPrice;
    //类型名称
    private String typeName;
}
