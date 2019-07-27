package schedulerservice.requestsapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import schedulerservice.model.smartshareobject.*;

@Slf4j
@Component
public class SmartShareRequests {

    @Autowired
    private HttpEntity<String> httpEntitySmartShare;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public String smartshareAPIAddress;



    public ListaODL getListaODL(){
        ResponseEntity<ListaODL> response = restTemplate.exchange(
                smartshareAPIAddress + "/odl/",
                HttpMethod.GET,
                httpEntitySmartShare,ListaODL.class);

        return response.getBody();
    }

    public ListaMonitor getListaMonitor(){
        ResponseEntity<ListaMonitor> response = restTemplate.exchange(
                smartshareAPIAddress + "/monitor/",
                HttpMethod.GET,
                httpEntitySmartShare,ListaMonitor.class);
        return response.getBody();
    }

    public Monitor getMonitor(String codMonitor){
        try{
            ResponseEntity<Monitor> response = restTemplate.exchange(
                    smartshareAPIAddress + "/monitor/"+codMonitor,
                    HttpMethod.GET,
                    httpEntitySmartShare,Monitor.class);
            return response.getBody();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Fase getSingleFase(String codODL, String codFase){
        ResponseEntity<Fase> response = restTemplate.exchange(
                smartshareAPIAddress +
                        "/odl/" +
                        codODL+
                        "/fasi/"+
                        codFase,
                HttpMethod.GET,
                httpEntitySmartShare,Fase.class);
        return response.getBody();

    }

//    public ODL getSingleODL(String codODL){
//        ResponseEntity<ODL> response = restTemplate.exchange(
//                smartshareAPIAddress +
//                        "/odl/" +
//                        codODL,
//                HttpMethod.GET,
//                httpEntitySmartShare,ODL.class);
//        return response.getBody();
//    }

//    public ListaFasi getListaFasi(){
//        ResponseEntity<ListaFasi> response = restTemplate.exchange(
//                smartshareAPIAddress + "/odl/" +"/fasi/",
//                HttpMethod.GET,
//                httpEntitySmartShare,ListaFasi.class);
//        return response.getBody();
//    }

    public ListaFasi getListaFasi(String odlCode){
        ResponseEntity<ListaFasi> response = restTemplate.exchange(
                smartshareAPIAddress + "/odl/"+odlCode+"/fasi/",
                HttpMethod.GET,
                httpEntitySmartShare,ListaFasi.class);
        return response.getBody();
    }




}
