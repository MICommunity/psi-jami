package psidev.psi.mi.jami.bridges.imex;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by anjali on 29/11/18.
 */
public class ImexCentralUtility {

    public static Set<String> initializeImexDBGroups(String endPoint) throws Exception{

            Constants.IMEX_PARTNERS =null; // for garbage collection
            Constants.IMEX_PARTNERS = new HashSet<>();
            //String endPoint = "https://imexcentral.org/icentraltest/ws-v20";
            endPoint = endPoint.substring(0, endPoint.lastIndexOf("/"));
            InputStream is = null;
            String imexPartnersUrl = endPoint + "/groupmgr?op.view=json&opp.role=IMEx%20partner";

            try {
                URL url = new URL(imexPartnersUrl);
                URLConnection yc = url.openConnection();
                BufferedReader rd = new BufferedReader(new InputStreamReader(yc.getInputStream()));

                String jsonText = readAll(rd);
                JSONObject json = new JSONObject(jsonText);
                if (json != null) {
                    JSONArray jSonArray = json.getJSONArray("groupList");
                    int countElements = jSonArray.length();
                    int counter = 0;
                    while (counter < countElements) {
                        Constants.IMEX_PARTNERS.add((String) jSonArray.getJSONObject(counter).get("label"));
                        counter++;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        return Constants.IMEX_PARTNERS;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
