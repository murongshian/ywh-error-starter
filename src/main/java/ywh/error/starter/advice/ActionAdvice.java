package ywh.error.starter.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ywh.error.starter.controller.ErrorController;

import javax.sql.DataSource;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@ControllerAdvice
public class ActionAdvice {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private Environment environment;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public void handleException(Exception ex) throws UnknownHostException {
        String hostAddress = Inet4Address.getLocalHost().getHostAddress();

        String applicationName = environment.getProperty("spring.application.name");
        if(null == applicationName){
            applicationName = "未设置应用名";
        }
        String port = environment.getProperty("server.port");
        if(null == port){
            port = "8080";
        }
        String hostName = hostAddress+":"+port;
        Class mainClassName = getMainClassName();

        ErrorController.class.getPackage().getName();
        String packageName = mainClassName.getPackage().getName();

        StackTraceElement ste = ex.getStackTrace()[0];
        String fileName = ste.getFileName();
        String className = ste.getClassName();
        String methodName = ste.getMethodName();
        int lineNumber = ste.getLineNumber();
        String message = ex.getMessage();

        for(StackTraceElement s : ex.getStackTrace()){
            if(s.getClassName().startsWith(packageName)){
                fileName = s.getFileName();
                className = s.getClassName();
                methodName = s.getMethodName();
                lineNumber = s.getLineNumber();
                message = ex.getMessage();
                break;
            }
        }
        if(null == message){
            message = "没有信息";
        }
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time =  ft.format(dNow);
        PreparedStatement ps;
        try {
            String sql = "insert into errors(host_name,application_name,file_name,class_name,method_name,line_number,message,create_time,update_time) values(?,?,?,?,?,?,?,?,?)";
            ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1,hostName);
            ps.setString(2,applicationName);
            ps.setString(3, fileName);
            ps.setString(4, className);
            ps.setString(5,methodName);
            ps.setInt(6,lineNumber);
            ps.setString(7,message);
            ps.setString(8,time);
            ps.setString(9,time);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Class getMainClassName(){
        Map<String, Object> annotatedBeans = context.getBeansWithAnnotation(SpringBootApplication.class);
        return annotatedBeans.isEmpty() ? null : annotatedBeans.values().toArray()[0].getClass();
    }
}
