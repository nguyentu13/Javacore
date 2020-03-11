package com.xtel.vngolf.api.model;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.tbv.utils.db.cmd.DbPagingCmd;
import com.tbv.utils.db.entities.PagingEntity;
import com.xtel.vngolf.api.listener.entities.CmsUser;


public class DbCmsUserCmsGetListCmd extends DbPagingCmd {

	private int user_id;
	private int pageIdx;
	private int pageSize;
	private String order_by;
	private String order_type;
	
	private List<CmsUser> lsUser = new ArrayList<CmsUser>();

	public DbCmsUserCmsGetListCmd(String transid, String channel, int user_id, int pageIdx, int pageSize,
			String order_by,String order_type) {
		super(transid, channel);
		this.user_id = user_id;
		this.pageIdx = pageIdx;
		this.pageSize = pageSize;
		this.order_by = order_by;
		this.order_type = order_type;

	}

	@Override
	protected void setSqlCommand() throws Exception {
		setProc("user_get_list", 10);
	}

	@Override
	protected void setSqlParameter() throws Exception {
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.VARCHAR);
		setNull(pageIdx);
		setNull(pageSize);
		cst.setString(idx++,order_by);
		cst.setString(idx++,order_type);
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.INTEGER);
	}

	@Override
	protected void getOutputResult() throws Exception {
		code = cst.getInt(1);
		message = cst.getString(2);

		if(code !=0){
			return;
		}

		pageInfo = new PagingEntity(pageIdx,pageSize,cst.getInt(8),cst.getInt(7),cst.getInt(9),cst.getInt(10));

		lsUser = new ArrayList<>();
		ResultSet rs = cst.getResultSet();
		if(rs!=null){
			try{
				while (rs.next()){
					CmsUser obj = new CmsUser();
					obj.setId(rs.getInt("id"));
					obj.setUser_name(rs.getString("user_name"));
					obj.setPassword(rs.getString("password"));
					obj.setFull_name(rs.getString("full_name"));
					obj.setBirth_day(rs.getDate("birth_day"));
					obj.setEmail(rs.getString("email"));
					obj.setPhone_number(rs.getString("phone_number"));
					obj.setMobile_number(rs.getString("mobile_number"));
					obj.setGender(rs.getInt("gender"));
					obj.setAddress(rs.getString("address"));
					obj.setUnit(rs.getString("unit"));
					obj.setStatus(rs.getInt("status"));
					obj.setIs_admin(rs.getInt("is_admin"));
					obj.setCreate_time(rs.getDate("create_time"));
					obj.setCreate_by(rs.getString("create_by"));
					obj.setUpdate_time(rs.getDate("update_time"));
					obj.setUpdate_by(rs.getString("update_by"));
					lsUser.add(obj);
				}
			}
			finally {
				rs.close();
			}
		}
	}

	public int getUser_id() {
		return user_id;
	}

	public int getPageIdx() {
		return pageIdx;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getOrder_by() {
		return order_by;
	}

	public List<CmsUser> getLsUser() {
		return lsUser;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	
	private void setNull(int param) throws Exception{
		if(param==0){
			cst.setNull(idx++,Types.INTEGER);
		}
		else{
			cst.setInt(idx++,param);
		}
	}

}
