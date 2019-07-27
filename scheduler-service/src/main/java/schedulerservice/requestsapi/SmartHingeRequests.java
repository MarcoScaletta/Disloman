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
    public String smartHingeAPIServiceAddress;

    public List<Records> getTappatriceRecord(Date start, Date stop){
        return getRecords(start, stop, "tappatrice");
    }

    public List<Records> getEtichettatriceRecord(Date start, Date stop){
        return getRecords(start, stop, "etichettatrice");
    }

    public List<Records> getIncartonatriceRecord(Date start, Date stop){
        return getRecords(start, stop, "incartonatrice");
    }

    public List<Records> getBilanciaRecord(Date start, Date stop){
        return getRecords(start, stop, "bilancia");
    }


    public List<Records> getTappatriceRecord(Monitor monitor){
        return getRecords(monitor, "tappatrice");
    }

    public List<Records> getEtichettatriceRecord(Monitor monitor){
        return getRecords(monitor, "etichettatrice");
    }

    public List<Records> getIncartonatriceRecord(Monitor monitor){
        return getRecords(monitor, "incartonatrice");
    }

    public List<Records> getBilanciaRecord(Monitor monitor){
        return getRecords(monitor, "bilancia");
    }

    public List<Records> getRecords(Monitor monitor, String urlMachine){

        return getRecords(
                new Timestamp(Long.parseLong(monitor.getTimeStart())),
                new Timestamp(Long.parseLong(monitor.getTimeStop())),
                        urlMachine);
    }

    public List<Records> getRecords(Date start, Date stop, String macchina){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ResponseEntity<RecordsList> response = new RestTemplate().exchange(
                smartHingeAPIServiceAddress + "/records/macchina/" + macchina +
                        "?start="+
                        sdf.format(start)+
                        "&stop="+
                        sdf.format(stop),
                HttpMethod.GET,
                null,new ParameterizedTypeReference<RecordsList>(){});
        if(response.getBody() != null)
            log.info("Dimensione dati ottenuti per [" + macchina + "] : "
                    + response.getBody().getRecordsList().size());
        else
            return null;
        return response.getBody().getRecordsList();
    }



}
