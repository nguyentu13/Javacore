package com.xtel.vngolf.api.model;

import com.tbv.utils.db.cmd.DbPagingCmd;
import com.tbv.utils.db.entities.PagingEntity;
import com.xtel.vngolf.api.listener.response.data.EventServiceGetListResponse;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbEventGetListXXXCmd extends DbPagingCmd {
    private int page_index;
    private int page_size;
    private String order_by;
    private String order_type;
    private int category_id;
    private String author;
    private Date date;
    private int language_id;
    private List<EventServiceGetListResponse> list;
    public DbEventGetListXXXCmd(String transid, String channel,int page_index,int page_size,String order_by,
                                String order_type,int category_id,String author,Date date,int language_id) {
        super(transid, channel);
        this.page_index = page_index;
        this.page_size = page_size;
        this.order_by = order_by;
        this.order_type = order_type;
        this.category_id = category_id;
        this.date = date;
        this.author = author;
        this.language_id = language_id;
    }

    @Override
    protected void setSqlCommand() throws Exception {
        setProc("event_get_list",14);
    }

    @Override
    protected void setSqlParameter() throws Exception {
        cst.setInt(idx++, Types.INTEGER);
        cst.setInt(idx++,Types.VARCHAR);
        cst.setInt(idx++,page_index);
        cst.setInt(idx++,page_size);
        cst.setString(idx++,null);
        cst.setString(idx++,null);

        if(category_id == 0){
            cst.setNull(idx++,Types.INTEGER);
        }
        else{
            cst.setInt(idx++,category_id);
        }

        if(date == null){
            cst.setNull(idx++,Types.DATE);
        }
        else{
            cst.setDate(idx++, new java.sql.Date(date.getTime()));
        }

        cst.setString(idx++,null);
        cst.setInt(idx++,language_id);
        cst.setInt(idx++, Types.INTEGER);
        cst.setInt(idx++, Types.INTEGER);
        cst.setInt(idx++, Types.INTEGER);
        cst.setInt(idx++, Types.INTEGER);
    }

    @Override
    protected void getOutputResult() throws Exception {
        code = cst.getInt(1);
        message =  cst.getString(2);
        if(code != 0) return;
        pageInfo = new PagingEntity(page_index,page_size,cst.getInt(14),cst.getInt(13),cst.getInt(11),cst.getInt(12));
        ResultSet rs = cst.getResultSet();
        list = new ArrayList<>();
        if(rs!=null){
            try{
                while (rs.next()){
                    EventServiceGetListResponse obj = new EventServiceGetListResponse();
                    obj.setId(rs.getInt("event_id"));
                    obj.setCategory_id(rs.getInt("category_id"));
                    obj.setCategory_name(rs.getString("category_name"));
                    obj.setEvent_date(rs.getDate("event_date"));
                    obj.setEvent_author(rs.getString("event_author"));
                    obj.setEvent_priority(rs.getInt("event_priority"));
                    obj.setEvent_title(rs.getString("event_title"));
                    obj.setEvent_content(rs.getString("event_content"));
                    obj.setLanguage_id(rs.getInt("language_id"));
                    obj.setLanguage_name(rs.getString("language_name"));
                    obj.setLanguage_sign(rs.getString("language_sign"));
                    obj.setLanguage_description(rs.getString("language_description"));
                    obj.setStatus(rs.getInt("status"));
                    list.add(obj);
                }
            }
            finally {
                rs.close();
            }
        }
    }

    public List<EventServiceGetListResponse> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "DbEventGetListXXXCmd{" +
                "page_index=" + page_index +
                ", page_size=" + page_size +
                ", order_by='" + order_by + '\'' +
                ", order_type='" + order_type + '\'' +
                ", category_id=" + category_id +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", language_id=" + language_id +
                ", list=" + list +
                '}';
    }
}
