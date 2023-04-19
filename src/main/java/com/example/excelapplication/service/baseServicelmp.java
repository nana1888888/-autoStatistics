package com.example.excelapplication.service;

import com.example.excelapplication.mapper.baseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class baseServicelmp implements  baseService{

    @Autowired
    private baseMapper basemapper;
    @Override
    public int selectedCount() {
        return basemapper.selectedCount();
    }

}
