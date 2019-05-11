package schedulerservice.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import schedulerservice.model.records.RecordsTappatrice;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Component
public class Scheduler {

    private int counter = 2;

    @Autowired
    private DataSource datasource;

    @Autowired
    private SmartShareRequests s;

    @Autowired
    private CassandraRequests cassandraRequests;


    @Scheduled(fixedRate = 5000)
    @Autowired
    @SuppressWarnings("Duplicates")
    public void t(){
        log.info("Exec scheduled task");
        if(counter-- >0) {
            log.info("exec POST");
            cassandraRequests.testPostTappatriceRecord();
        }else
            log.info("no POST");
//        log.info("token " + s.token);
//        for (Commessa c : s.getListaCommesse().getLista_commesse())
//            log.info(c.toString());
//        log.info("reading day " + firstDay);
//        firstDay = firstDay.plusDays(1);


        try {
            Connection conn = datasource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM dbo.T_IN_MSG where msgin like '%DI12:%' and sn like '%780118'");
            int i=0;
            while (rs.next()) {
                String msg = rs.getString("msgin");
                String di12 = RecordsTappatrice.parser.findVal("DI12",msg);
                String di13 = RecordsTappatrice.parser.findVal("DI13",msg);
                if(!di12.equals("0") || !di13.equals("0")) {
                    System.out.println("DI12=" + di12);
                    System.out.println("DI13=" + di13);
                }
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void executeHingeTask(int millis) throws InterruptedException {
//        timer.schedule(hingeTask,0, millis);
//    }

    public void testDB(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.err.println(datasource==null);
        try {
            Connection conn = datasource.getConnection();
            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(
//                    "SELECT * FROM dbo.T_IN_MSG where msgin like '%DI12:%' and sn like '%780118'");
//            int i=0;
//            while (rs.next()) {
//                String msg = rs.getString("msgin");
//                String di12 = RecordsTappatrice.parser.findVal("DI12",msg);
//                String di13 = RecordsTappatrice.parser.findVal("DI13",msg);
////                if(!di12.equals("0") || !di13.equals("0")) {
//                    System.out.println("DI12=" + di12);
//                    System.out.println("DI13=" + di13);
////                }
//            }
//            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




}
