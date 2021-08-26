import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static Document getPage() throws IOException {
        String url = "http://pogoda.msk.ru/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    private static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    private static String getDateFromString(String stringDate) throws Exception {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new Exception("Cant extract date from string!");
    }

    private static int printFourValues(Elements values, int index) {
        int iterationCount = 4;
        if (index == 0){
            Element valueLn = values.get(3);
            boolean isMorning = valueLn.text().contains("Утро");
            if (isMorning){
                iterationCount = 3;
            }
        }
        for (int i = 0; i < iterationCount; i++){
            Element valueLine = values.get(index + i);
            for (Element td : valueLine.select("td")){
                System.out.println(td.text() + "    ");
            }
            System.out.println();
        }
        return iterationCount;
    }
        public static void main (String[]args) throws Exception {
            Document page = getPage();
            Element tableWth = page.select("table[class=wt]").first();
            System.out.println(tableWth);
            Elements names = tableWth.select("tr[class=wth]");
            Elements values = tableWth.select("tr[valign=top]");
            int index = 0;
            for (Element name : names) {
                String dateString = name.select("th[id=dt]").text();
                String date = getDateFromString(dateString);
                System.out.println(date + "   Явление       Температура      Давление     Влажность    Ветер");
               int iterationCount = printFourValues(values, index);
                printFourValues(values, index);
            }


        }
    }

