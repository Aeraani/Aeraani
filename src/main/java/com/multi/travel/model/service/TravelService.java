package com.multi.travel.model.service;

import com.multi.travel.model.bean.TravelVO;

import java.util.List;

public interface TravelService {
    List<TravelVO> searchListAll(int page, int pageSize);
    int countAll();
    List<TravelVO> searchListByDistrict(String district, int page, int pageSize);
    int countByDistrict(String district);
    List<TravelVO> searchListByTitle(String title,int page, int pageSize);
    int countByTitle(String title);
}
