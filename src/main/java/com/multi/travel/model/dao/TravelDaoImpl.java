package com.multi.travel.model.dao;

import com.multi.travel.model.bean.TravelVO;
import com.multi.travel.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TravelDaoImpl implements TravelDao {
    private static TravelDao travelDao = new TravelDaoImpl();

    public static TravelDao getTravelDao() {
        return travelDao;
    }

    @Override
    public List<TravelVO> searchListAll(int page, int pageSize) {
        List<TravelVO>list=new ArrayList<TravelVO>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getInstance().getConnection();
            StringBuilder sql = new StringBuilder("Select no,district,title,description,address,phone\n");
            sql.append("FROM travel \n")
                    .append("ORDER BY no ASC \n")
                    .append("LIMIT ?, ? \n");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, (page - 1) * pageSize);
            pstmt.setInt(2, pageSize);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TravelVO vo = new TravelVO();
                vo.setNo(rs.getInt("no"));
                vo.setDistrict(rs.getString("district"));
                vo.setTitle(rs.getString("title"));
                vo.setDescription(rs.getString("description"));
                vo.setAddress(rs.getString("address"));
                vo.setPhone(rs.getString("phone"));
                list.add(vo);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.getInstance().close(rs,pstmt,conn);
        }
        return list;
    }

    @Override
    public int countAll() {
        int count=0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = DBUtil.getInstance().getConnection();
            String sql="SELECT COUNT(*) FROM travel";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                count =rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
        DBUtil.getInstance().close(rs,pstmt,conn);
        }
        return count;
    }

    @Override
    public List<TravelVO> searchListByDistrict(String district, int page, int pageSize) {
        List<TravelVO>list=new ArrayList<TravelVO>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{ conn = DBUtil.getInstance().getConnection();
            StringBuilder sql = new StringBuilder("Select no,district,title,description,address,phone\n");
            sql.append("FROM travel \n")
                    .append("WHERE DISTRICT LIKE ? \n")
                    .append("ORDER BY NO ASC \n")
                    .append("LIMIT ?, ? \n");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1,"%"+district+"%");
            pstmt.setInt(2, (page - 1) * pageSize);
            pstmt.setInt(3, pageSize);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TravelVO vo = new TravelVO();
                vo.setNo(rs.getInt("no"));
                vo.setDistrict(rs.getString("district"));
                vo.setTitle(rs.getString("title"));
                vo.setDescription(rs.getString("description"));
                vo.setAddress(rs.getString("address"));
                vo.setPhone(rs.getString("phone"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.getInstance().close(rs,pstmt,conn);
        }
        return list;
    }

    @Override
    public int countByDistrict(String district) {
        int count=0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = DBUtil.getInstance().getConnection();
            String sql="SELECT COUNT(*) FROM travel WHERE DISTRICT LIKE ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%"+district+"%");
            rs = pstmt.executeQuery();
            if(rs.next()){
                count =rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.getInstance().close(rs,pstmt,conn);
        }
        return count;
    }

    @Override
    public List<TravelVO> searchListByTitle(String title, int page, int pageSize) {
        List<TravelVO>list=new ArrayList<TravelVO>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{ conn = DBUtil.getInstance().getConnection();
            StringBuilder sql = new StringBuilder("Select no,district,title,description,address,phone\n");
            sql.append("FROM travel \n")
                    .append("WHERE TITLE LIKE ? \n")
                    .append("ORDER BY NO ASC\n")
                    .append("LIMIT ?, ? \n");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1,"%"+title+"%");
            pstmt.setInt(2, (page - 1) * pageSize);
            pstmt.setInt(3, pageSize);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TravelVO vo = new TravelVO();
                vo.setNo(rs.getInt("no"));
                vo.setDistrict(rs.getString("district"));
                vo.setTitle(rs.getString("title"));
                vo.setDescription(rs.getString("description"));
                vo.setAddress(rs.getString("address"));
                vo.setPhone(rs.getString("phone"));
                list.add(vo);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.getInstance().close(rs,pstmt,conn);
        }
        return list;
    }

    @Override
    public int countByTitle(String title) {
        int count=0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = DBUtil.getInstance().getConnection();
            String sql="SELECT COUNT(*) FROM travel WHERE TITLE LIKE ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + title + "%");
            rs = pstmt.executeQuery();
            if(rs.next()){
                count =rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.getInstance().close(rs,pstmt,conn);
        }
        return count;
    }
}
