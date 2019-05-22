package smarthingeapiservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class QueryTappatrice {

//    public static String id = "78";
//
//    public static Parser parser;

    @Autowired
    DataSource datasource;


    private String select = "SELECT ts_srv, msgin FROM dbo.T_IN_MSG where sn like '%780118'";

//    static {
//        try {
//            parser = new Parser(
//                    new String[]{"DI12","DI13"},
//                    new String[]{"DI12:(.*?),", "DI13:(.*?),"});
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public List<Record> getRecordsFromMonitor(Monitor monitor){
//        log.info("Monitor sent to get records from TAPPATRICE is: " + monitor);
//        // ESECUZIONE DELLA
//        return new ArrayList<>();
//    }

    public String getQueryByMonitor(Monitor monitor){
        String newQuery = select;
        newQuery +=
                " and ts_srv >= \'" + monitor.getTimeStart() +"\'" +
                        " and ts_srv <= \'" + monitor.getTimeStop()+"\'" ;
        return newQuery;
    }



    public List<Record> dummyRecordsFromMonitor(Monitor monitor){
        List<Record> records = new ArrayList<>();
        if(monitor.getTimeStart() != null && monitor.getTimeStop()!=null)
            try {
                Connection conn = datasource.getConnection();
                log.info("Query " + getQueryByMonitor(monitor));
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(getQueryByMonitor(monitor));
                int i=0;
                while (rs.next()) {
                    String time = rs.getString("ts_srv");
                    records.add(new Record(time,0,"null_cod"));
//                String msg = rs.getString("msgin");
//                String di12 = parser.findVal("DI12",msg);
//                String di13 = parser.findVal("DI13",msg);
////                if(!di12.equals("0") || !di13.equals("0")) {
//                System.out.println("DI12=" + di12);
//                System.out.println("DI13=" + di13);
//                }
                }
                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        return records;
    }
}
