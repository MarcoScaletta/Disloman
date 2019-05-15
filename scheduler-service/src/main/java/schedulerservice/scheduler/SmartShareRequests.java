package schedulerservice.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import schedulerservice.model.smartshareobject.commesse.ListaCommesse;
import schedulerservice.model.smartshareobject.odl.fasi.ListaFasi;
import schedulerservice.model.smartshareobject.odl.ListaODL;

@Slf4j
@Component
public class SmartShareRequests {

    @Autowired
    private HttpEntity<String> httpEntitySmartShare;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public String smartshareAddress;

    public ListaCommesse getListaCommesse(){
        ResponseEntity<ListaCommesse> response = restTemplate.exchange(
                smartshareAddress + "/commesse/",
                HttpMethod.GET,
                httpEntitySmartShare,ListaCommesse.class);
        return response.getBody();
    }

    public ListaODL getListaODL(){
        ResponseEntity<ListaODL> response = restTemplate.exchange(
                smartshareAddress + "/odl/",
                HttpMethod.GET,
                httpEntitySmartShare,ListaODL.class);

        return response.getBody();
    }

    public ListaFasi getListaFasi(String odlCode){
        ResponseEntity<ListaFasi> response = restTemplate.exchange(
                smartshareAddress + "/odl/"+odlCode+"/fasi/",
                HttpMethod.GET,
                httpEntitySmartShare,ListaFasi.class);
        return response.getBody();
    }




}
