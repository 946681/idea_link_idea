package service;

import test.Test;
import utils.ConfigDb;
import vo.Record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordService {
    ConfigDb configDb = new ConfigDb();

    private Connection DB = configDb.connectionMysql();

    //插入记录
    public void insertRecord( String op) throws SQLException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = df.format(date);
        String sql = "INSERT INTO record (time,operation) VALUES (?,?)";
        PreparedStatement ptmt = this.DB.prepareStatement(sql);
        ptmt.setString(1,time);
        ptmt.setString(2,op);
        boolean res = ptmt.execute();
    }
    //显示所有记录
    public void displayRecord() throws SQLException, ParseException {
        String sql = "SELECT * FROM record;";
        PreparedStatement ptmt = this.DB.prepareStatement(sql);

        ResultSet res = ptmt.executeQuery(sql);
        List<Record> recordList = new ArrayList<>();

        while (res.next()) {
            Record record = new Record();
            record.setTime(res.getString("time"));
            record.setOperation(res.getString("operation"));
            System.out.println(record);
            recordList.add(record);
        }
        Test.start();
    }
}
