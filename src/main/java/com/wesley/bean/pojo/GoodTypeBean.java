package com.wesley.bean.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "good_types")
@Data
public class GoodTypeBean
{
    @Id
    @Column(name = "tgt_id")
    private Long id;

    @Column(name = "tgt_name")
    private String name;
    @Column(name = "tgt_is_show")
    private int show;
    @Column(name = "tgt_order")
    private int order;
}

