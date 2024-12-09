import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    static String infoPath ="./src/test/resources/customers_info.json";
    public static void setEnvVar(String key ,String value) throws ConfigurationException {
        PropertiesConfiguration pc = new PropertiesConfiguration("./src/test/resources/config.properties");
        pc.setProperty(key,value);
        pc.save();
    }
    public static int randomNumber(int max,int min)
    {
        double random = Math.random()*(max-min)+min;
        return (int) random;
    }
public  static void setInfo(JSONObject object) throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray userinfo = (JSONArray) parser.parse(new FileReader(infoPath));
    userinfo.add(object);
    FileWriter fw = new FileWriter(infoPath);
    fw.write(String.valueOf(userinfo));
    fw.flush();
    fw.close();
}
    public  static void resetUserFile() throws IOException, ParseException {

        FileWriter fw = new FileWriter(infoPath);
        fw.write("[]");
        fw.flush();
        fw.close();
    }

    public  static JSONObject  getInfo(int index) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray userinfo = (JSONArray) parser.parse(new FileReader(infoPath));
        JSONObject object = (JSONObject) userinfo.get(index);
        return object;
    }

}
