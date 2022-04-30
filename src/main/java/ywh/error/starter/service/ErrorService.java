package ywh.error.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ywh.error.starter.model.Error;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class ErrorService {

    @Autowired
    private DataSource dataSource;

    public List<Error> listErrors() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement cs = connection.createStatement();
        ResultSet executeQuery = cs.executeQuery("SELECT id as id,host_name as hostName,application_name as applicationName,file_name as fileName,class_name as className,method_name as methodName,line_number as lineNumber,create_time as createTime FROM errors order by id desc");
        List<Error> errorList = new ArrayList<>();
        while (executeQuery.next()) {
            Error error = new Error();
            error.setId(executeQuery.getInt("id"));
            error.setHostName(executeQuery.getString("hostName"));
            error.setApplicationName(executeQuery.getString("applicationName"));
            error.setFileName(executeQuery.getString("fileName"));
            error.setClassName(executeQuery.getString("className"));
            error.setMethodName(executeQuery.getString("methodName"));
            error.setLineNumber(executeQuery.getInt("lineNumber"));
            error.setCreateTime(executeQuery.getString("createTime"));
            errorList.add(error);
        }
        cs.close();
        connection.close();
        return errorList;
    }

    public void deleteError(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement cs = connection.createStatement();
        cs.executeUpdate("DELETE FROM errors WHERE id=" + id);
        cs.close();
        connection.close();
    }

    public Error getError(int id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement cs = connection.createStatement();
        ResultSet e = cs.executeQuery("SELECT id as id,host_name as hostName,application_name as applicationName,file_name as fileName,class_name as className,method_name as methodName,line_number as lineNumber,message as message,create_time as createTime FROM errors WHERE id=" + id);
        Error error = new Error();
        if(e.next()){
            error.setId(e.getInt("id"));
            error.setHostName(e.getString("hostName"));
            error.setApplicationName(e.getString("applicationName"));
            error.setFileName(e.getString("fileName"));
            error.setClassName(e.getString("className"));
            error.setMethodName(e.getString("methodName"));
            error.setLineNumber(e.getInt("lineNumber"));
            error.setMessage(e.getString("message"));
            error.setCreateTime(e.getString("createTime"));
        }
        cs.close();
        connection.close();
        return error;
    }
}
