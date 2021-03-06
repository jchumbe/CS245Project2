import com.opencsv.CSVReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, FileNotFoundException {
        Graph graph = new Graph();

        try(FileReader fileReader = new FileReader("/Users/jacd123/IdeaProjects/Project2/src/main/java/tmdb_5000_credits.csv");
            CSVReader csvReader = new CSVReader(fileReader)) {
            String[] line;
            csvReader.readNext();
            int count = 0;

            while ((line = csvReader.readNext()) != null && count < 64) {

                JSONParser pars = new JSONParser();
                Object JObj = pars.parse(line[2]);
                JSONArray jArray = (JSONArray) JObj;
                Object movID = pars.parse(line[0]);
                for (int x = 0; x < jArray.size(); x++) {
                    graph.add((String) ((JSONObject)jArray.get(x)).get("name"), (Long) movID);
                }
                count++;
            }
            csvReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        List<String> path = graph.kevinBacon("Sam Worthington","Michelle Rodriguez");
        System.out.println(Arrays.toString(path.toArray()));
        System.out.println(graph.getMovies());
//        System.out.println(graph.kevinBacon("Sam Worthington","Michelle Rodriguez"));
        // read in file
        // read in credits file for line in movie
        // for object in file for movies
        //
        // actor is a string found in the tmbd_5000_credits.csv
        // and the id comes from the tmdb_5000_movies.csv then maps to the above file
        // graph.add(actor, movie);
    }
}
