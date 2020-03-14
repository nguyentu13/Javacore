package com.xtel.vngolf.api.model;

import com.tbv.utils.db.cmd.DbPagingCmd;
import com.tbv.utils.db.entities.PagingEntity;
import com.xtel.vngolf.api.listener.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class DbProductGetListCmd extends DbPagingCmd {
    private int index;
    private int size;
    private int id;
    private List<Product> data_fake;
    private List<Product> list;
    public DbProductGetListCmd(String transid, String channel,int index,int size,int id) {
        super(transid, channel);
        this.index = index;
        this.size = size;
        this.id = id;
    }

    @Override
    protected void setSqlCommand() throws Exception {
        //xxxx
    }

    @Override
    protected void setSqlParameter() throws Exception {
        //xxxxx
    }

    public void execute() throws Exception{
        // fake data xxx
        data_fake = new ArrayList<>();

        for(int i=0;i<20;i++){
            Product product = new Product();
            product.setId(i+1);
            if(i%2==0){
                product.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRa5K1thT7xsKFgzKW5JZwYFM7xcEPZme9WWsG9erx6vz2K_wxv");
                product.setName("Trophy "+(i+1));
                product.setCategory_id(1);
            }
            else{
                product.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSRJlzInvmF3kxrVI_9fo-TmzBDogacIfIBznGgiWUdI5lxDVIb");
                product.setName("Bags "+(i+1));
                product.setCategory_id(2);
            }
            data_fake.add(product);
        }

        Product product = new Product();
        product.setId(data_fake.size()+1);
        product.setName("Other Product 1");
        product.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSiNvmluMMzIqMEYNfZKcgi6qzHPtD5Xkb3dPxyg2l99RsWgc5s");
        product.setCategory_id(3);
        data_fake.add(product);
        Product product2 = new Product();
        product.setId(data_fake.size()+1);
        product.setName("Other Product 2");
        product.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSiNvmluMMzIqMEYNfZKcgi6qzHPtD5Xkb3dPxyg2l99RsWgc5s");
        product.setCategory_id(3);
        data_fake.add(product2);
        getOutputResult();
    }

    @Override
    protected void getOutputResult() throws Exception {
        code = 0;
        message = "SUCCESS";

        list = new ArrayList<>();
        int offset = (index -1)*size;
        int begin_index = offset+1;
        int end_index = offset+size;

        if(id != 0){
            List<Product> temp = new ArrayList<>();
            for(Product p : data_fake){
                if(p.getCategory_id()==id){
                    temp.add(p);
                }
            }
            data_fake = temp;
        }

        for (int i = offset;i<offset+size;i++){
            list.add(data_fake.get(i));
        }

        int total_record = data_fake.size();
        int total_page = (int)Math.ceil((double)total_record/size);
        pageInfo = new PagingEntity(index,size,total_record,total_page,begin_index,end_index);
    }

    public List<Product> getList() {
        return list;
    }


}
