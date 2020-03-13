package com.xtel.vngolf.api.model;


import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.tbv.utils.db.cmd.DbCallableCmd;
import com.tbv.utils.db.cmd.DbPagingCmd;
import com.xtel.vngolf.api.listener.entities.CmsGolfCourse;

public class DbCmsGolfCourseGetListCmd extends DbPagingCmd{

	 private int page_index;
	    private int page_size;
	   private List<CmsGolfCourse> list;
	   private int total_page;
	   private int lang_id;




	public DbCmsGolfCourseGetListCmd(String transid, String channel, int page_index, int page_size, int lang_id) {
		super(transid, channel);
		this.page_index = page_index;
		this.page_size = page_size;
		this.lang_id = lang_id;
	}

	@Override
	protected void getOutputResult() throws Exception {
		code = cst.getInt(1);
		message = cst.getString(2);
		total_page = cst.getInt(6);
		list = new ArrayList<CmsGolfCourse>();
		ResultSet rs = null;
		try {
			rs = cst.getResultSet();
			while(rs.next()) {
				CmsGolfCourse golfCourse = new CmsGolfCourse();
				golfCourse.setId(rs.getInt(1));
				golfCourse.setAvatar_url(rs.getString(2));
				golfCourse.setTitle(rs.getString(4));
				golfCourse.setDiscount(rs.getDouble(5));
				golfCourse.setPlace(rs.getString(6));
				golfCourse.setOriginal_price(rs.getDouble(7));
				golfCourse.setIn_week_price(rs.getDouble(8));
				golfCourse.setWeekend_price(rs.getDouble(9));
				golfCourse.setCurrency_unit(rs.getString(10));
				list.add(golfCourse);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	protected void setSqlCommand() throws Exception {
		setProc("golf_course_get_list", 7);
	}

	@Override
	protected void setSqlParameter() throws Exception {
		cst.setInt(idx++, Types.INTEGER);
        cst.setInt(idx++, Types.VARCHAR);
        if(page_index == 0){
            cst.setNull(idx++,Types.INTEGER);
        }
        else{
            cst.setInt(idx++,page_index);
        }
        if(page_size == 0){
            cst.setNull(idx++,Types.INTEGER);
        }
        else{
            cst.setInt(idx++,page_size);
        }
        cst.setInt(idx++, lang_id);
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.INTEGER);
	
	}

	public List<CmsGolfCourse> getList() {
		return list;
	}

	
}
