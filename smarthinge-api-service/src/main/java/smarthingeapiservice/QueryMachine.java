package smarthingeapiservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class QueryMachine {

    // il parametro 1 e' il codice della macchina
    // il parametro 2 e' la data d'inizio
    // il parametro 3 e' la data di fine
    // il parametro 4 e' il codice del sensore per il conteggio

    private String select = "SELECT OAMT.ASS_MSTR_TRACE_time [TIME]," +
            "OAMT.ASS_MSTR_TRACE_value [VAR_VALUE] " +
            "FROM ORC_roh_ODM.dbo.ORC_ASSET_MASTER_TRACING [OAMT] " +
            "JOIN ORC_roh_ODM.dbo.ORC_ASSET_MASTER [OAM] ON OAM.ASS_MASTR_ID = OAMT.ASS_MSTR_ID " +
            "JOIN ORC_roh_ODM.dbo.ORC_ASSET_GROUP_PARAM [OAGP] ON OAGP.ASS_GR_PARAM_id = " +
            "OAMT.ASS_GR_PARAM_id " +
            "WHERE OAM.ASS_MASTR_code = ? " +
            "AND OAMT.ASS_MSTR_TRACE_time >= ? AND OAMT.ASS_MSTR_TRACE_time < ? AND OAGP.ASS_GR_PARAM_desc = ?";

    private final String machineCode;

    private final String outputCode;

    public QueryMachine(String machineCode, String outputCode) {
        this.machineCode = machineCode;
        this.outputCode = outputCode;
    }

    @Autowired
    DataSource datasource;

    @SuppressWarnings("Duplicates")
    public List<Record> queryToMachine(Timestamp startTime, Timestamp stopTime) {
        List<Record> records = new ArrayList<>();
        try {
            int stringSize;
            Connection conn = datasource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(select);
            pstmt.setString(1, machineCode);
            pstmt.setTimestamp(2, startTime);
            pstmt.setTimestamp(3, stopTime);
            pstmt.setString(4, outputCode);

            log.info("Executing query on " + machineCode);
            log.info("START: "  + startTime);
            log.info("END  : "   + stopTime);
            System.out.println(pstmt.toString());

            ResultSet rs = pstmt.executeQuery();
            System.out.print("\t\tRECORD LETTI:            0");
            String backspace;
            while (rs.next()) {
                backspace = "";
                String time = rs.getString("TIME");
                int pzz = rs.getInt("VAR_VALUE");
                records.add(new Record(time,pzz));
                stringSize = (records.size() + "").length();
                for (int j = 0; j < stringSize; j++) {
                    backspace += "\b";
                }
                System.out.print( backspace + records.size());
            }
            System.out.println();
            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("Numero totale di record estratti " + records.size() +"\n" );
        return records;
    }
}
