package com.xtel.vngolf.api.model;

import java.sql.Types;
import java.util.ArrayList;

import com.tbv.utils.db.cmd.DbCallableCmd;
import com.xtel.vngolf.api.listener.entities.CmsGolfCourseDetail;

public class DbCmsGolfCourseDetailCmd extends DbCallableCmd{
	private int course_id;
	private int lang_id;
	private int max_img;
	CmsGolfCourseDetail golf_course;
	ArrayList<String> servicesList;
	ArrayList<String> imgList;
	public DbCmsGolfCourseDetailCmd(String transid, String channel, int course_id, int lang_id, int max_img) {
		super(transid, channel);
		this.course_id = course_id;
		this.lang_id = lang_id;
		this.max_img = max_img;
	}
	@Override
	protected void getOutputResult() throws Exception {
		code = cst.getInt(1);
		message = cst.getString(2);
		servicesList = new ArrayList<String>();
		imgList = new ArrayList<String>();
		servicesList.add("fake services");
		imgList.add("fake img");
		int id = 1;
		String title = "fake title";
		double discount = 0.3;
		double original = 550;
		double in_week = 500;
		double weeken = 600;
		String unit = "VND";
		String placeName = "Bana";
		String address = "Ha noi";
		golf_course = new CmsGolfCourseDetail(id, title, discount, original, in_week, weeken, unit, placeName, address, false, imgList, servicesList);
		
	}
	@Override
	protected void setSqlCommand() throws Exception {
		setProc("golf_course_detail", 5);
		
	}
	@Override
	protected void setSqlParameter() throws Exception {
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.VARCHAR);
		cst.setInt(idx++, code);
		cst.setInt(idx++, max_img);
		cst.setInt(idx++, lang_id);
		
	}
	public CmsGolfCourseDetail getGolf_course() {
		return golf_course;
	}
	public void setGolf_course(CmsGolfCourseDetail golf_course) {
		this.golf_course = golf_course;
	}
	
	
}
