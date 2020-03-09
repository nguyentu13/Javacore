package com.xtel.vngolf.api.model;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.tbv.utils.db.cmd.DbPagingCmd;
import com.tbv.utils.db.entities.PagingEntity;
import com.xtel.vngolf.api.listener.entities.CmsHotel;

public class DbCmsHotelGetListCmd extends DbPagingCmd {
	private int page_index;
	private int page_size;
	private String order_by;
	private String order_type;
	private String keyword;
	private List<CmsHotel> list;
	
	public DbCmsHotelGetListCmd(String transid, String channel,int page_index,int page_size,
								String order_by,String order_type,String keyword) {
		super(transid, channel);
		this.page_index = page_index;
		this.page_size = page_size;
		this.order_by = order_by;
		this.order_type = order_type;
		this.keyword = keyword;
	}

	@Override
	protected void setSqlCommand() throws Exception {
		setProc("hotel_search", 11);
	}

	@Override
	protected void setSqlParameter() throws Exception {
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.VARCHAR);
		setNull(page_index);
		setNull(page_size);
		cst.setString(idx++,order_by);
		cst.setString(idx++,order_type);
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.INTEGER);
		cst.setString(idx++,keyword);
	}

	@Override
	protected void getOutputResult() throws Exception {
		code = cst.getInt(1);
		message = cst.getString(2);

		if(code !=0){
			return;
		}

		pageInfo = new PagingEntity(page_index,page_size,cst.getInt(8),cst.getInt(7),cst.getInt(9),cst.getInt(10));

		list = new ArrayList<>();
		ResultSet rs = cst.getResultSet();
		if(rs!=null){
			try{
				while (rs.next()){
					CmsHotel obj = new CmsHotel();
					obj.setId(rs.getInt("id"));
					obj.setName(rs.getString("name"));
					obj.setDescription(rs.getString("description"));
					list.add(obj);
				}
			}
			finally {
				rs.close();
			}
		}
	}

	@Override
	public String toString() {
		return "DbCmsHotelGetListCmd [list=" + list + "]";
	}

	public List<CmsHotel> getList() {
		return list;
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
