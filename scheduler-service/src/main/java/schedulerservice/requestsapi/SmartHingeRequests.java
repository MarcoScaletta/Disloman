package schedulerservice.requestsapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import schedulerservice.model.records.Records;
import schedulerservice.model.records.RecordsList;
import schedulerservice.model.smartshareobject.Monitor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class SmartHingeRequests {

    @Autowired
    private String tappatriceRecordsTableName;

    @Autowired
    private String incartonatriceRecordsTableName;

    @Autowired
    private String etichettatriceRecordsTableName;

    @Autowired
    private String bilanciaRecordsTableName;

    @Autowired
    public String smartHingeAPIServiceAddress;

    public List<Records> getTappatriceRecord(Date start, Date stop){
        return getRecords(start, stop, tappatriceRecordsTableName);
    }

    public List<Records> getEtichettatriceRecord(Date start, Date stop){
        return getRecords(start, stop, etichettatriceRecordsTableName);
    }

    public List<Records> getIncartonatriceRecord(Date start, Date stop){
        return getRecords(start, stop, incartonatriceRecordsTableName);
    }

    public List<Records> getBilanciaRecord(Date start, Date stop){

        return getRecords(start, stop, bilanciaRecordsTableName);
    }


    public List<Records> getTappatriceRecord(Monitor monitor){
        return getRecords(monitor, tappatriceRecordsTableName);
    }

    public List<Records> getEtichettatriceRecord(Monitor monitor){
        return getRecords(monitor, etichettatriceRecordsTableName);
    }

    public List<Records> getIncartonatriceRecord(Monitor monitor){
        return getRecords(monitor, incartonatriceRecordsTableName);
    }

    public List<Records> getBilanciaRecord(Monitor monitor){
        return getRecords(monitor, bilanciaRecordsTableName);
    }

    public List<Records> getRecords(Monitor monitor, String urlMachine){

        return getRecords(
                new Timestamp(Long.parseLong(monitor.getTimeStart())),
                new Timestamp(Long.parseLong(monitor.getTimeStop())),
                        urlMachine);
    }

    public List<Records> getRecords(Date start, Date stop, String urlMachine){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ResponseEntity<RecordsList> response = new RestTemplate().exchange(
                smartHingeAPIServiceAddress + "/" + urlMachine +
                        "/monitor?start="+
                        sdf.format(start)+
                        "&stop="+
                        sdf.format(stop),
                HttpMethod.GET,
                null,new ParameterizedTypeReference<RecordsList>(){});
        if(response.getBody() != null)
            log.info("Dimensione dati ottenuti per [" + urlMachine + "] : "
                    + response.getBody().getRecordsList().size());
        else
            return null;
        return response.getBody().getRecordsList();
    }



}
