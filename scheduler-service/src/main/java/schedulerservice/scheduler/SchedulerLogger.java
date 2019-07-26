package schedulerservice.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Slf4j
@Component
public class SchedulerLogger {
    private final String RESET = "\u001B[0m";
    private final String BLACK = "\u001B[30m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String YELLOW = "\u001B[33m";
    private final String BLUE = "\u001B[34m";
    private final String PURPLE = "\u001B[35m";
    private final String CYAN = "\u001B[36m";
    private final String WHITE = "\u001B[37m";


    private final String SPLIT = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";


    public void logFirst(String logString){

        System.out.println( BLUE + time() + logString + RESET);
    }

    public void logSecond(String logString){

        System.out.println( SPLIT + YELLOW + time() + logString + RESET);
    }

    public void err(String logString){

        System.out.println( RED + time() + logString + RESET);
    }

    private String time(){
        return new Timestamp(System.currentTimeMillis()).toString() + " ";
    }
}
