package com.xtel.vngolf.api.main;



import com.tbv.utils.textbase.StringUtils;
import com.xtel.vngolf.api.config.CoreConfig;

import oracle.jdbc.OracleTypes;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.*;
import java.util.Scanner;

public class SupportSelectQueryTool {

    static String url = "jdbc:mysql://222.252.16.140:10306/truong_public";
    static String user = "truong_nguyen";
    static String password = "truong@123@";

    // init here to start
    public static void setSql(CallableStatement cst) throws Exception{
        int idx=1;
        cst.setInt(idx++, Types.INTEGER);
        cst.setInt(idx++,Types.VARCHAR);
        cst.setInt(idx++,1);
        cst.setInt(idx++,10);
        cst.setString(idx++,null);
        cst.setString(idx++,null);
        cst.setNull(idx++,Types.INTEGER);
        cst.setNull(idx++,Types.DATE);
        cst.setString(idx++,null);
        cst.setInt(idx++,1);
        cst.setInt(idx++, Types.INTEGER);
        cst.setInt(idx++, Types.INTEGER);
        cst.setInt(idx++, Types.INTEGER);
        cst.setInt(idx++, Types.INTEGER);
    }

    public static void main(String[] args) throws Exception{
        // init test
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter procedure name :");
        String proc_name = sc.nextLine();

        System.out.print("Enter number of param :");
        int number_param = Integer.parseInt(sc.nextLine());

        System.out.print("Enter ResultSet index : ");
        int i = Integer.parseInt(sc.nextLine());

        // start test
        System.out.println("-------------------START TEST MAIN-------------------");
        Connection conn = DriverManager.getConnection(url,user,password);
        String query = setQuery(proc_name,number_param);
        CallableStatement cst = conn.prepareCall(query);
        System.out.println("Set sql parameter :");
        setSql(cst);
        //cst.setQueryTimeout(600000);
        //setSqlParameter(cst,number_param);
        cst.execute();
        ////
        ResultSet rs = null;
        for(int x=0;x<i-1;x++) {
        	ResultSet rsxx = cst.getResultSet();
        	rsxx.close();
        	cst.getMoreResults();
        }
        rs = cst.getResultSet();
        if(rs!=null){
            try{
                if (rs.next()){

                    ResultSetMetaData meta = rs.getMetaData();
                    int index = 1;
                    System.out.println("--------Start object attribute------------");
                    while (true){
                        try{
                            System.out.println("private "+convertClassName(meta.getColumnClassName(index))+" "+meta.getColumnName(index).toLowerCase()+";");
                        }
                        catch (Exception ex){
                            System.out.println("--------End object attribute------------");
                            break;
                        }

                        index++;
                    }

                    System.out.println("--------Start set object attribute------------");
                    int index2 = 1;
                    while (true){
                        try{
                            String class_name = convertClassName(meta.getColumnClassName(index2)).toLowerCase();
                            class_name = class_name.substring(0,1).toUpperCase()+class_name.substring(1);
                            String att = meta.getColumnName(index2).toLowerCase().substring(0,1).toUpperCase()+meta.getColumnName(index2).toLowerCase().substring(1);
                            System.out.println("obj.set"+att+"(rs.get"+class_name+"(\""+meta.getColumnName(index2)+"\"));");
                        }
                        catch (Exception ex){
                            System.out.println("--------End set object attribute------------");
                            break;
                        }
                        index2++;
                    }
                }
            }
            finally {
                rs.close();
            }
        }
        else{
            System.out.println("[ rs = null ]");

        }
        conn.close();

        System.out.println("-----------------END TEST MAIN---------------");
    }

    public static String setQuery(String proc_name,int param_size){
        String params = "?";
        if (param_size > 1) {
            for(int i = 1; i < param_size; ++i) {
                params = params + ",?";
            }
        }

        String query = "{call "+proc_name+"("+params+")}";
        System.out.println(query);
        return query;
    }

    public static void setSqlParameter(CallableStatement cst,int param_size) throws Exception {
        Scanner sc  = new Scanner(System.in);
        for(int i=1;i<param_size+1;i++){
            System.out.print("Param "+i+" (IN or OUT) ?: ");
            String type_param = sc.nextLine();
            if(type_param.equalsIgnoreCase("in")){
                System.out.print("Type value (int ,String ,float ,Date ?: ");
                String type_value = sc.nextLine();
                switch (type_value.trim()){
                    case "int":
                        System.out.print("Value: ");
                        int value = Integer.parseInt(sc.nextLine());
                        if(value == 0){
                            cst.setNull(i,OracleTypes.INTEGER);
                        }
                        else{
                            cst.setInt(i,value);
                        }
                        break;
                    case "String":
                        System.out.print("Value: ");
                        String value1 = sc.nextLine();
                        cst.setString(i,value1);
                        break;
                    case "float":
                        System.out.print("Value: ");
                        Float value2 = Float.parseFloat(sc.nextLine());
                        if(value2 == 0){
                            cst.setNull(i,OracleTypes.FLOAT);
                        }
                        else{
                            cst.setFloat(i,value2);
                        }
                        break;
                    case "Date":
                        System.out.print("Value 'dd-mm-yyyy': ");
                        String input_date = sc.nextLine();
                        java.util.Date date = parseToDate(input_date) == null ? null:parseToDate(input_date);
                        if(date == null){
                            cst.setNull(i,OracleTypes.DATE);
                        }
                        else{
                            Date value3 = new Date(date.getTime());
                            cst.setDate(i,value3);
                        }
                        break;
                }
            }
            else{
                System.out.print("Type value (int ,String ,float ,Date,cursor) ?: ");
                String type_value = sc.nextLine();
                switch (type_value.trim()){
                    case "int":
                        cst.registerOutParameter(i,OracleTypes.INTEGER);
                        break;
                    case "String":
                        cst.registerOutParameter(i,OracleTypes.VARCHAR);
                        break;
                    case "float":
                        cst.registerOutParameter(i,OracleTypes.FLOAT);
                        break;
                    case "Date":
                        cst.registerOutParameter(i,OracleTypes.DATE);
                        break;
                    case "cursor":
                        cst.registerOutParameter(i,OracleTypes.CURSOR);
                        break;
                }
            }

        }
    }

    public static String convertClassName(String clazz){
        if(clazz.equals("java.math.BigDecimal")){
            return "int";
        }
        if(clazz.equals("java.lang.String")){
            return "String";
        }
        if(clazz.equals("java.sql.Timestamp")|| clazz.equals("java.sql.Date")){
            return "Date";
        }
        if(clazz.equals("java.lang.Integer")){
            return "int";
        }
        if(clazz.equals("java.lang.Float")){
            return "float";
        }
        if(clazz.equals("java.lang.Double")){
            return "double";
        }
        return "";
    }

    public static java.util.Date parseToDate(String birthDay) {
        java.util.Date result = null;

        if (StringUtils.isNullOrEmpty(birthDay)) {
            return null;
        } else {
            try {
                result = DateUtils.parseDate(birthDay, CoreConfig.API_DAY_INPUT_FORMAT);
            } catch (Exception ex) {
                return null;
            }
        }
        return result;
    }


}
