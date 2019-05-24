package schedulerservice.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import schedulerservice.model.records.Record;
import schedulerservice.model.smartshareobject.odl.fasi.Monitor;

import java.util.ArrayList;
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
    public void t(){
        Monitor m1 = new Monitor(
                "1",
                "2019-05-22 06:30:00",
                "2019-05-22 07:00:00");
        Monitor m2 = new Monitor(
                "2",
                "2019-05-22 07:01:00",
                "2019-05-22 07:30:00");
        Monitor m3 = new Monitor(
                "3",
                "2019-05-22 07:31:00",
                "2019-05-22 08:00:00");
        List<Monitor> monitors = new ArrayList<>();
        monitors.add(m1);
        monitors.add(m2);
        monitors.add(m3);
//
        saveRecords("null_cod",filterMonitor(monitors));
        System.exit(0);
//
//        saveRecords("null_cod",m1);
//        System.exit(0);

//        for (Commessa c : s.getListaCommesse().getLista_commesse())
//            log.info(c.toString());
//        List<ODL> l = s.getListaODL().getLista_odl();
//        for (ODL c : s.getListaODL().getLista_odl()){
//            log.info("In " + c.toString());
//            for (Fase f : s.getListaFasi(c.getCodODL()).getListaFasi()){
//                log.info("  FASE  :"+ f.getCodFase());
//                log.info("  ATTIVA:"+ f.isActive());
//                for(ProdottoLavorato p : f.getListaProdottiLavorati())
//                    log.info("    ->"+ p.getCodProdotto());
//            }
//
//        }
    }
    public void saveRecords(String cod_prodotto, List<Monitor> allMonitor){
        for (Monitor m : allMonitor)
            saveRecords(cod_prodotto, m);

    }

    public void saveRecords(String cod_prodotto, Monitor monitor){
        List<Record> records = smartHingeRequests.getRecords(monitor);
        for(Record r : records){
            r.setCodiceProdotto(cod_prodotto);
            cassandraRequests.postTappatriceRecord(r);
        }
        cassandraRequests.postMonitor(monitor);
    }

    public List<Monitor> filterMonitor(List<Monitor> monitors){
        List<Monitor> savedMonitor = cassandraRequests.getSavedMonitors();
        log.info("OLD SIZE MONITOR LIST: " + monitors.size());
        monitors.removeAll(savedMonitor);
        log.info("NEW SIZE MONITOR LIST: " + monitors.size());
        return monitors;
    }



}
