package com.xtel.vngolf.api.model;

import com.tbv.utils.db.cmd.DbPagingCmd;
import com.tbv.utils.db.entities.PagingEntity;
import com.xtel.vngolf.api.listener.entities.GolfTour;

import java.util.ArrayList;
import java.util.List;

public class DbGolfTourGetListCmd extends DbPagingCmd {
    private int page_index;
    private int page_size;
    private String order_by;
    private String order_type;
    private String tour_name;
    private float duration_time;
    private String address;
    private double price;
    private List<GolfTour> list;

    public DbGolfTourGetListCmd(String transid, String channel,int page_index,int page_size,String order_by,
                                String order_type,String tour_name,float duration_time,String address,double price) {
        super(transid, channel);
        this.page_index = page_index;
        this.page_size = page_size;
        this.order_by = order_by;
        this.order_type = order_type;
        this.tour_name  = tour_name;
        this.duration_time = duration_time;
        this.address = address;
        this.price = price;
    }

    @Override
    protected void setSqlCommand() throws Exception {
        getOutputResult();
    }

    @Override
    protected void setSqlParameter() throws Exception {

    }

    public void execute() throws Exception{
        getOutputResult();
    };

    @Override
    protected void getOutputResult() throws Exception {
        code = 0;
        message = "SUCCESS";

        List<GolfTour> listxx = new ArrayList<>();
        for(int i=0;i<20;i++){
            GolfTour obj = new GolfTour();
            obj.setId(i+1);
            obj.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSpr5-dDWtskAAr8poSJ-sXbTeZFg4kDDdrg5ANMlXIFvkLiKjw");
            obj.setTour_name("Tour "+(i+1));
            obj.setDuration_time((i+1));
            obj.setAddress("Nam Định "+(i+1));
            obj.setRound(i+1);
            obj.setPrice((i+1)*1000);
            listxx.add(obj);
        }

        list = new ArrayList<>();
        int offset = (page_index -1)*page_size;
        for(int i=offset;i<offset+page_size;i++){
            if(listxx.get(i)!=null) list.add(listxx.get(i));
        }

        pageInfo = new PagingEntity(page_index,page_size,list.size(),(int) Math.ceil((double)listxx.size()/page_size),offset+1,
                offset+page_size);
    }

    public List<GolfTour> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "DbGolfTourGetListCmd{" +
                "page_index=" + page_index +
                ", page_size=" + page_size +
                ", order_by='" + order_by + '\'' +
                ", order_type='" + order_type + '\'' +
                ", tour_name='" + tour_name + '\'' +
                ", duration_time=" + duration_time +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", list=" + list +
                '}';
    }
}
