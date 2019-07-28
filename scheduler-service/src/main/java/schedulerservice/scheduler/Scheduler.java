package schedulerservice.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import schedulerservice.model.Turno;
import schedulerservice.model.cassandraobjects.OpenMonitor;
import schedulerservice.model.records.Records;
import schedulerservice.model.records.RecordsList;
import schedulerservice.model.smartshareobject.Fase;
import schedulerservice.model.smartshareobject.ListaMonitor;
import schedulerservice.model.smartshareobject.Monitor;
import schedulerservice.model.smartshareobject.ProdottoLavorato;
import schedulerservice.requestsapi.CassandraRequests;
import schedulerservice.requestsapi.SmartHingeRequests;
import schedulerservice.requestsapi.SmartShareRequests;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class Scheduler {

    @Autowired
    private SmartShareRequests smartShareRequests;

    @Autowired
    private CassandraRequests cassandraRequests;

    @Autowired
    private SmartHingeRequests smartHingeRequests;

    @Autowired
    private SchedulerLogger logger;

    private final long SECONDS_ALARM_SLEEP = 30000;

    @PostConstruct
    public void task(){

        logger.logFirst("FIRST SCHEDULER");
        logger.logSecond("SECOND SCHEDULER");
        Runnable newMonitor = () -> {
            try {

                while(true){
                    logger.logFirst("Executing task...");
                    if(!newMonitor()){
                        logger.logFirst("ERROR DETECTED -> GOING TO SLEEP");
                        Thread.sleep(SECONDS_ALARM_SLEEP/2);
                        logger.logFirst("               " +
                                "-> WAKE UP IN " + SECONDS_ALARM_SLEEP/2000 +" SECONDS");
                        Thread.sleep(SECONDS_ALARM_SLEEP/2);

                    }
                    Thread.sleep(5000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Runnable oldMonitor = () -> {
            try {
                while(true){

                    logger.logSecond("Executing task...");
                    if(!oldMonitor()){
                        logger.logSecond("ERROR DETECTED -> GOING TO SLEEP");
                        Thread.sleep(SECONDS_ALARM_SLEEP/2);
                        logger.logSecond("               " +
                                "-> WAKE UP IN " + SECONDS_ALARM_SLEEP/2000 +" SECONDS");
                        Thread.sleep(SECONDS_ALARM_SLEEP/2);
                    }
                    Thread.sleep(5000);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Thread threadNewMonitor = new Thread(newMonitor);
        threadNewMonitor.start();
        Thread threadOldMonitor = new Thread(oldMonitor);
        threadOldMonitor.start();

    }

    public boolean oldMonitor() {
        try{
            Map<String, Map<String, Fase>> odlFasiMap = new HashMap<>();
            List<Monitor> monitors = smartShareRequests.getListaMonitor().getListaMonitor();
            List<Monitor> filteredMonitor = filterMonitor(monitors, false);
            if(filteredMonitor == null)
                return false;
            for (Monitor m : filteredMonitor) {
                if (m.getTimeStop() != null)
                    if(! saveMonitor(m, true, odlFasiMap, false)){
                        return false;
                    }

            }
            return true;
        }catch (HttpServerErrorException e){
            logger.err("HTTP ERROR: " + e.getStatusCode());
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean newMonitor() {
        Monitor monitor;
        boolean isToBeClosed;
        try{
            OpenMonitor openMonitor = cassandraRequests.getOpenMonitor();

            if(openMonitor == null){
                logger.logFirst("No old open monitor");
                monitor = getOpenMonitor();
                if(monitor != null){
                    openMonitor = new OpenMonitor();
                    openMonitor.setCodMonitor(monitor.getCodMonitor());
                    openMonitor.setStartTime(monitor.getTimeStart());
                    cassandraRequests.postOpenMonitor(openMonitor);
                }else{
                    logger.logFirst("No new open monitor");
                    return false;
                }
            }
            logger.logFirst("old monitor: " +openMonitor.getCodMonitor());
            monitor = smartShareRequests.getMonitor(openMonitor.getCodMonitor());
            monitor.setTimeStart(openMonitor.getStartTime());

            if(monitor.getTimeStop() == null){
                logger.logFirst("INFO: Il monitor ["+ monitor.getCodMonitor()+"] e' ancora aperto.");
                logger.logFirst("INFO: Nuovo intervallo parziale"
                        +"\n      Inizio: "+ new Timestamp(Long.parseLong(openMonitor.getStartTime()))
                        +"\n      Fine: "  + new Timestamp(System.currentTimeMillis()));
                monitor.setTimeStop(System.currentTimeMillis()+"");
                isToBeClosed = false;
            }else{
                logger.logFirst("INFO: Il monitor ["+ monitor.getCodMonitor()+"] e' stato chiuso.");
                logger.logFirst("\nINFO: Nuovo intervallo parziale"
                        +"\n      Inizio: " + new Timestamp(Long.parseLong(openMonitor.getStartTime()))
                        +"\n      Fine: " + new Timestamp(Long.parseLong(monitor.getTimeStop())));
                isToBeClosed = true;
            }
            if(!saveMonitor(monitor, isToBeClosed,null, true)) {
                return false;
            }else{
                openMonitor.setStartTime(monitor.getTimeStop());
                if(isToBeClosed){
                    cassandraRequests.deleteOpenMonitor(monitor.getCodMonitor());
                }else{
                    cassandraRequests.postOpenMonitor(openMonitor);

                }
            }
            return true;

        }catch (HttpServerErrorException e){
            logger.err("HTTP ERROR: " + e.getStatusCode());
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Monitor getOpenMonitor(){
        ListaMonitor ms = smartShareRequests.getListaMonitor();
        for(Monitor m : ms.getListaMonitor())
            if(m.getTimeStop() == null)
                return m;
        return null;
    }

    public boolean saveMonitor(Monitor m, boolean closeMonitor, Map<String, Map<String, Fase>> odlFasiMap, boolean first) throws Exception {
        String codODL, codFase, codCommessa,codProdotto, nomeProdotto;
        Date startDate,stopDate;
        ProdottoLavorato prodottoLavorato;
        Fase fase = null;
        String turno;

        if (m.getTimeStop() != null) {
            codODL = m.getCodODL();
            codFase = m.getCodFase();
            startDate = new Date(Long.parseLong(m.getTimeStart()));
            stopDate = new Date(Long.parseLong(m.getTimeStop()));

            if(odlFasiMap==null){
                fase = smartShareRequests.getSingleFase(codODL, codFase);
            }else{

                if (!odlFasiMap.containsKey(m.getCodODL()))
                    odlFasiMap.put(m.getCodODL(), new HashMap<>());

                if (!odlFasiMap.get(m.getCodODL()).containsKey(codFase)) {
                    fase = smartShareRequests.getSingleFase(codODL, codFase);
                    odlFasiMap.get(m.getCodODL()).put(codFase, fase);
                } else{
                    try {
                        fase = odlFasiMap.get(m.getCodODL()).get(codFase);
                    }catch(Exception e){
                        logger.err(codFase);
                        logger.err(odlFasiMap.toString());
                    }
                }
            }
            if(fase != null) {

                if (fase.getListaProdottiLavorati().size() < 1) {
                    String log = "ERR: Lista prodotti lavorati vuota.";
                    if(first)
                        logger.logFirst(log);
                    else
                        logger.logSecond(log);
                    return false;
                }

                prodottoLavorato = fase.getListaProdottiLavorati().get(0);
                codCommessa = prodottoLavorato.getCodCommessa();
                codProdotto = prodottoLavorato.getCodProdotto();
                nomeProdotto = prodottoLavorato.getNomeProdotto();
                turno = Turno.getTurno(m.getTimeStart()) + "";


                String [] logs =new String[] {"Monitor: " + m.getCodMonitor(),
                        ">>     Start: " + startDate,
                        ">>      Stop: " + stopDate,
                        ">>      Fase: " + codFase,
                        ">>       ODL: " + codODL,
                        ">>  Commessa: " + codCommessa,
                        ">>  Prodotto: " + codProdotto};
                if(first)
                    for(String log : logs)
                        logger.logFirst(log);
                else
                    for(String log : logs)
                        logger.logSecond(log);
                boolean savedRecords = saveRecords(m,
                        codProdotto,
                        nomeProdotto,
                        codCommessa,
                        codODL,
                        turno,
                        closeMonitor);
                if(!savedRecords)
                    if(first)
                        logger.logFirst("Error saving records");
                    else
                        logger.logSecond("Error saving records");
                return savedRecords;
            }
        }else
            throw new Exception("Stop time of monitor is NULL");
        return true;
    }



    public boolean saveRecords(Monitor monitor,
                               String codiceProdotto,
                               String nomeProdotto,
                               String codiceCommessa,
                               String codiceODL,
                               String turno,
                               boolean closeMonitor){
        Date start,stop;
        if(monitor.getTimeStop() != null){
            start = new Date(Long.parseLong(monitor.getTimeStart()));
            stop = new Date(Long.parseLong(monitor.getTimeStop()));

            RecordsList recordsTappatrice = new RecordsList();
            RecordsList recordsEtichettatrice= new RecordsList();
            RecordsList recordsIncartonatrice = new RecordsList();
            RecordsList recordsBilancia = new RecordsList();

            Records genericRecords = new Records();

            genericRecords.setCodiceProdotto(codiceProdotto);
            genericRecords.setNomeProdotto(nomeProdotto);
            genericRecords.setCodiceCommessa(codiceCommessa);
            genericRecords.setCodiceODL(codiceODL);
            genericRecords.setTurno(turno);

            try {
                recordsTappatrice.getRecordsList().addAll(smartHingeRequests.getTappatriceRecord(start, stop));
                recordsEtichettatrice.getRecordsList().addAll(smartHingeRequests.getEtichettatriceRecord(start, stop));
                recordsIncartonatrice.getRecordsList().addAll(smartHingeRequests.getIncartonatriceRecord(start, stop));
                recordsBilancia.getRecordsList().addAll(smartHingeRequests.getBilanciaRecord(start, stop));

                setCommonProperties(recordsTappatrice, genericRecords);
                setCommonProperties(recordsEtichettatrice, genericRecords);
                setCommonProperties(recordsIncartonatrice, genericRecords);
                setCommonProperties(recordsBilancia, genericRecords);

                cassandraRequests.postRecordsTappatrice(recordsTappatrice);
                cassandraRequests.postRecordsEtichettatrice(recordsEtichettatrice);
                cassandraRequests.postRecordsIncartonatrice(recordsIncartonatrice);
                cassandraRequests.postRecordsBilancia(recordsBilancia);
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            if(closeMonitor)
                cassandraRequests.postMonitor(monitor);

            return true;

        }
        return false;

    }

    private void setCommonProperties(RecordsList records, Records genericRecords){
        for(Records r : records.getRecordsList())
            setCommonProperties(r, genericRecords);

    }

    private void setCommonProperties(Records toSet, Records toCopy){
        toSet.setCodiceProdotto(toCopy.getCodiceProdotto());
        toSet.setNomeProdotto(toCopy.getNomeProdotto());
        toSet.setCodiceCommessa(toCopy.getCodiceCommessa());
        toSet.setCodiceODL(toCopy.getCodiceODL());
        toSet.setTurno(toCopy.getTurno());

    }

    public List<Monitor> filterMonitor(List<Monitor> monitors, boolean first){
        List<Monitor> savedMonitor = cassandraRequests.getClosedMonitors();
        OpenMonitor openMonitors = cassandraRequests.getOpenMonitor();
        if(openMonitors != null){
            Monitor monitor = new Monitor();
            monitor.setCodMonitor(openMonitors.getCodMonitor());
            monitors.remove(monitor);
        }
        if(savedMonitor == null)
            return null;
        monitors.removeAll(savedMonitor);

        String log = ("Number of missed monitor: " + monitors.size());
        if(first)
            logger.logFirst(log);
        else
            logger.logSecond(log);
        return monitors;
    }



}
