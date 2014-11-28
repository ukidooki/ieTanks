package pl.edu.agh.ietanks.gameplay.testutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResourceUtils {
    public static String loadResourceFromFile(String filename) {
        try {
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(ResourceUtils.class.getClassLoader().getResourceAsStream(filename)));
            String line;
            while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception!";
        }
    }
}
