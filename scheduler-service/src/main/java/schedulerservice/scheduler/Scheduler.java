package schedulerservice.scheduler;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;

@Slf4j
public class Scheduler {


    private static Timer timer = new Timer();
    private static HingeTask hingeTask = new HingeTask();

    public static void executeHingeTask(int millis) throws InterruptedException {
        timer.schedule(hingeTask,0, millis);

    }


}
