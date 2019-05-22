package smarthingeapiservice;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private final HashMap<String, String> map;

    public Parser(String [] dataIDs, String[]regexes) throws Exception {
        if(dataIDs.length != regexes.length)
            throw new Exception("Data Ids and Regexes must have same lenght");
        map = new HashMap<>();
        for (int i = 0; i < dataIDs.length; i++) {
            map.put(dataIDs[i],regexes[i]);
        }
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public String findVal(String dataID, String toFind){
        Matcher matcher = Pattern.compile(map.get(dataID)).matcher(toFind);
        if(matcher.find())
            return matcher.group(1);
        else
            return null;

    }
}
