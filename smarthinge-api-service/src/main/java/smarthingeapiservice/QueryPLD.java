package smarthingeapiservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class QueryPLD {

    @Autowired
    private DataSource datasource;


    private String activityQuery = "SELECT\n" +
            "ASS_MASTR_code [MACHINE],\n" +
            "CASE\n" +
            "  WHEN ASS_MASTR_server_time IS NULL THEN 'OFFLINE'\n" +
            "  ELSE\n" +
            "    CASE\n" +
            "      WHEN DATEDIFF(S, ASS_MASTR_server_time, GETDATE()) > 30 THEN 'OFFLINE WARNING'\n" +
            "      ELSE 'ONLINE'\n" +
            "    END\n" +
            "END [STATUS]\n" +
            "FROM ORC_roh_ODM.dbo.ORC_ASSET_MASTER\n" +
            "WHERE ASS_MASTR_code IN ('PRCS-AS-8')";

    public Boolean isActive() {
        List<Records> records = new ArrayList<>();
        try {
            int stringSize;
            Connection conn = datasource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(activityQuery);

            log.info("Executing activity query");

            ResultSet rs = pstmt.executeQuery();
            String backspace;
            if (rs.next()) {
                String res = rs.getString("STATUS");
                log.info("PLD is: " + res);
                return res.equals("ONLINE");
            }else {
                log.error("NO RESULT");
            }
            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
