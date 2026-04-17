import java.io.*;
import java.net.*;

public class GoogleTranslate {

    public static String translate(String text, String apiKey, String targetLang) {
        try {
            String urlStr = "https://translation.googleapis.com/language/translate/v2"
                    + "?key=" + apiKey
                    + "&q=" + URLEncoder.encode(text, "UTF-8")
                    + "&target=" + targetLang;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // crude parsing (works for assignment)
            String result = response.toString();
            int start = result.indexOf("translatedText") + 18;
            int end = result.indexOf("\"", start);

            return result.substring(start, end);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error in translation";
        }
    }
}