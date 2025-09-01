package com.multi.travel.model.dao;

import com.multi.travel.model.bean.TravelVO;

import java.util.List;

public interface TravelDao { // DB에 접근해서 아래 기능 수행
    List<TravelVO> searchListAll(int page, int pageSize);
    int countAll();
    List<TravelVO> searchListByDistrict(String district, int page, int pageSize);
    int countByDistrict(String district);
    List<TravelVO> searchListByTitle(String Title,int page, int pageSize);
    int countByTitle(String title);

}
