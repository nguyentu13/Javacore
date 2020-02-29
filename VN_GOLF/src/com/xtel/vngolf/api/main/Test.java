package com.xtel.vngolf.api.main;

import com.tbv.utils.db.entities.PagingEntity;
import com.xtel.vngolf.api.listener.entities.CmsHotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception{
        Connection conn = DriverManager.getConnection("jdbc:mysql://222.252.16.140:10306/truong_public","truong_nguyen","truong@123@");
//        CallableStatement cst = conn.prepareCall("{call `PKG_HOTEL.HOTEL_GET_LIST`(?, ?, ?, ?,? , ?, ?, ?, ?, ?,?,?)}");
        CallableStatement cst = conn.prepareCall("{call test(?)}");
//        cst.registerOutParameter(1,Types.INTEGER);
            cst.setInt(1,Types.INTEGER);
//        int idx=1;
//        cst.setInt(idx++, 0);
//        cst.setInt(idx++, 0);
//        cst.registerOutParameter(idx++,Types.INTEGER);
//        cst.registerOutParameter(idx++,Types.VARCHAR);
//        cst.setInt(idx++,1);
//        cst.setInt(idx++,3);
//        cst.setString(idx++,null);
//        cst.setString(idx++,null);
//        cst.registerOutParameter(idx++, Types.INTEGER);
//        cst.registerOutParameter(idx++, Types.INTEGER);
//        cst.registerOutParameter(idx++, Types.INTEGER);
//        cst.registerOutParameter(idx++, Types.INTEGER);
        cst.execute();
        int code = cst.getInt(1);
        System.out.println(code);
//        String message = cst.getString(2);
//
//        if(code !=0){
//            return;
//        }
//
//
//        List<CmsHotel> list = new ArrayList<>();
//        ResultSet rs = cst.getResultSet();
//        if(rs!=null){
//            try{
//                while (rs.next()){
//                    CmsHotel obj = new CmsHotel();
//                    obj.setId(rs.getInt("id"));
//                    obj.setName(rs.getString("name"));
//                    obj.setDescription(rs.getString("description"));
//                    list.add(obj);
//                }
//            }
//            finally {
//                rs.close();
//            }
//        }
    }
}
