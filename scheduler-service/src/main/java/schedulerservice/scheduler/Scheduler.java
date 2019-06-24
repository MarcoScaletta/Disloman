package schedulerservice.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import schedulerservice.model.records.Record;
import schedulerservice.model.smartshareobject.odl.fasi.Monitor;
import schedulerservice.requestsapi.CassandraRequests;
import schedulerservice.requestsapi.SmartHingeRequests;
import schedulerservice.requestsapi.SmartShareRequests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@Component
public class Scheduler {

    @Autowired
    private SmartShareRequests s;

    @Autowired
    private CassandraRequests cassandraRequests;

    @Autowired
    private SmartHingeRequests smartHingeRequests;


    @Scheduled(fixedRate = 5000, initialDelay = 5000)
    @Autowired
    @SuppressWarnings("Duplicates")
    public void t() throws ParseException {
        Monitor myMonitor = new Monitor(
                "mon_week_17_21_june",
                new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2019-06-17 00:00").getTime() + "",
                new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2019-06-22 00:00").getTime() + "",
                "odl_week_17_21_june");
        saveRecords(myMonitor);
        System.exit(0);
    }

    public void saveRecords(Monitor monitor){
        List<Record> recordsTappatrice = smartHingeRequests.getTappatriceRecord(monitor);
        List<Record> recordsEtichettatrice= smartHingeRequests.getEtichettatriceRecord(monitor);
        List<Record> recordsIncartonatrice = smartHingeRequests.getIncartonatriceRecord(monitor);
        List<Record> recordsBilancia = smartHingeRequests.getBilanciaRecord(monitor);

        addProdCodeSaveRecords(recordsTappatrice, monitor.getCodODL());
        addProdCodeSaveRecords(recordsEtichettatrice, monitor.getCodODL());
        addProdCodeSaveRecords(recordsIncartonatrice, monitor.getCodODL());
        addProdCodeSaveRecords(recordsBilancia, monitor.getCodODL());

        cassandraRequests.postRecordsTappatrice(recordsTappatrice);
        cassandraRequests.postRecordsEtichettatrice(recordsEtichettatrice);
        cassandraRequests.postRecordsIncartonatrice(recordsIncartonatrice);
        cassandraRequests.postRecordsBilancia(recordsBilancia);

        cassandraRequests.postMonitor(monitor);
    }

    private void addProdCodeSaveRecords(List<Record> records, String prodCode){
        for(Record r : records)
            r.setCodiceProdotto(prodCode);

    }

    public List<Monitor> filterMonitor(List<Monitor> monitors){
        List<Monitor> savedMonitor = cassandraRequests.getSavedMonitors();
        log.info("OLD SIZE MONITOR LIST: " + monitors.size());
        monitors.removeAll(savedMonitor);
        log.info("NEW SIZE MONITOR LIST: " + monitors.size());
        return monitors;
    }



}
