package com.xtel.vngolf.api.main;

import java.sql.Date;

public class SupportInsertQueryTool {
    static String url = "jdbc:oracle:thin:@222.252.16.140:15211:orclxtdb";
    static String user = "pmgw_cms";

    static String password = "pmgw_cms";

    // init here to start
    public static String init(){
        String input =


            // copy obj to here

            "   	private int id;\r\n" + 
            "	private String user_name;\r\n" + 
            "	private String password;\r\n" + 
            "	private String full_name;\r\n" + 
            "	private Date birth_day;\r\n" + 
            "	private String email;\r\n" + 
            "	private String phone_number;\r\n" + 
            "	private String mobile_number;\r\n" + 
            "	private int gender;\r\n" + 
            "	private String address;\r\n" + 
            "	private String unit;\r\n" + 
            "	private int status;\r\n" + 
            "	private int is_admin;\r\n" + 
            "	private Date create_time;\r\n" + 
            "	private String create_by;\r\n" + 
            "	private Date update_time;\r\n" + 
            "	private String update_by;";




        return input;
    }

    public static void main(String[] args) {
        execute(init());
    }

    public static void execute(String str){
        String[] arrStr = null;
        arrStr = str.split(";");
        for(int i=0;i<arrStr.length;i++){
            String[] temp = arrStr[i].trim().split(" ");
            String c = temp[1];
            c = c.substring(0,1).toUpperCase()+ c.substring(1);
            String s = temp[2];
            s = s.substring(0,1).toUpperCase()+s.substring(1);
            s = "obj.get"+s+"()";
            System.out.println("cst.set"+c+"("+s+");");
        }
    }
}
