package schedulerservice.scheduler;

import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

@Slf4j
public class HingeTask extends TimerTask {
    @Override
    public void run() {
        log.info("Executing Hinge task");
        retrieveSmartHingeData();
        retrieveSmartShareData();
        generateJoinedData();
        updateDatabase();
    }

    private void retrieveSmartHingeData(){
        log.info("retrieve SmartHinge Data");

    }

    private void retrieveSmartShareData(){
        log.info("retrieve SmartShare Data");

    }

    private void generateJoinedData(){
        log.info("generate Joined Data");

    }

    private void updateDatabase(){
        log.info("update Database");

    }
}
