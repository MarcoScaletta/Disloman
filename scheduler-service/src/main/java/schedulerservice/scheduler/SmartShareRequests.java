package schedulerservice.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import schedulerservice.model.smartshareobject.ListaCommesse;
import schedulerservice.model.smartshareobject.ListaODL;

@Component
public class SmartShareRequests {

    @Autowired
    private HttpEntity<String> httpEntitySmartShare;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public String smartshareAddress;

    public SmartShareRequests(){
    }

    public Object getObject(String path, Class<?> c){
        ResponseEntity<?> response = restTemplate.exchange(
                smartshareAddress + path,
                HttpMethod.GET,
                httpEntitySmartShare,c);
        return response.getBody();

    }

//    public ListaCommesse getListaCommesse1(){
//        ListaCommesse
//    }

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




}
