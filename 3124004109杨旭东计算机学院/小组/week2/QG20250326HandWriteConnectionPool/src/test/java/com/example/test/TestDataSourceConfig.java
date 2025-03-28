package com.example.test;

import com.example.connectionpool.ConnectionPool;
import com.example.connectionpool.DataSourceConfig;
import com.example.connectionpool.DataSourceManager;
import org.junit.Test;

import java.sql.Connection;

public class TestDataSourceConfig {

    public static void main(String[] args) {
        Thead t = new Thead();
        for(int i=0;i<10;i++){
            new Thread(t,"线程"+i).start();
        }
    }





}
//局部内部类
class Thead implements Runnable{
    public void run(){
        try {
            Connection conn = DataSourceManager.getConn();
            //模拟正在拿到这个连接执行数据库操作
            Thread.sleep(1000);
            DataSourceManager.close(conn);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}