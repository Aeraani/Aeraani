package com.multi.travel.model.service;

import com.multi.travel.model.bean.TravelVO;
import com.multi.travel.model.dao.TravelDaoImpl;

import java.util.List;

public class TravelServiceImpl implements TravelService {
    private static TravelService travelService=new TravelServiceImpl();

    public static TravelService getTravelService() {
        return travelService;
    }

    @Override
    public List<TravelVO> searchListAll(int page, int pageSize) {
        return TravelDaoImpl.getTravelDao().searchListAll(page,pageSize);
    }

    @Override
    public int countAll() {
        return TravelDaoImpl.getTravelDao().countAll();
    }

    @Override
    public List<TravelVO> searchListByDistrict(String district, int page, int pageSize) {
        return TravelDaoImpl.getTravelDao().searchListByDistrict(district,page,pageSize);
    }

    @Override
    public int countByDistrict(String district) {
        return TravelDaoImpl.getTravelDao().countByDistrict(district);
    }

    @Override
    public List<TravelVO> searchListByTitle(String title, int page, int pageSize) {
        return TravelDaoImpl.getTravelDao().searchListByTitle(title,page,pageSize);
    }

    @Override
    public int countByTitle(String title) {
        return TravelDaoImpl.getTravelDao().countByTitle(title);
    }
}
