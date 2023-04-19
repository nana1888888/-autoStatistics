package com.example.excelapplication.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface baseMapper {

    public int selectedCount();


}
