package com.wesley.bean.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.wesley.bean.dto.GoodInfoDTO;
import com.wesley.bean.pojo.GoodInfoBean;
import com.wesley.bean.pojo.GoodTypeBean;

@Mapper(componentModel = "spring")
public interface GoodInfoMapper
{
  @Mappings({
          @Mapping(source = "type.name",target = "typeName"),
          @Mapping(source = "good.id",target = "goodId"),
          @Mapping(source = "good.title",target = "goodName"),
          @Mapping(source = "good.price",target = "goodPrice")
  })
  public GoodInfoDTO from(GoodInfoBean good, GoodTypeBean type);

}
