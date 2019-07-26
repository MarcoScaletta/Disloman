package schedulerservice.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SchedulerLogger {
    public final String RESET = "\u001B[0m";
    public final String BLACK = "\u001B[30m";
    public final String RED = "\u001B[31m";
    public final String GREEN = "\u001B[32m";
    public final String YELLOW = "\u001B[33m";
    public final String BLUE = "\u001B[34m";
    public final String PURPLE = "\u001B[35m";
    public final String CYAN = "\u001B[36m";
    public final String WHITE = "\u001B[37m";


    private final String SPLIT = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";


    public void logFirst(String logString){

        System.out.println(BLUE + logString + RESET);
    }

    public void logSecond(String logString){

        System.out.println(SPLIT + YELLOW + logString + RESET);
    }

    public void err(String logString){

        System.out.println(RED + logString + RESET);
    }
}
