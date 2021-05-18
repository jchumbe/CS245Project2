import java.util.*;

public class Graph {

    // movie is based on the movie id
    // map of movie ids to list of actors
    private Map<Long, Set<String>> movies;
    // map of actors to movies they have been in
    private Map<String, Set<Long>> actors;

    public Graph() {
        movies = new HashMap<Long, Set<String>>();
        actors = new HashMap<String, Set<Long>>();
    }

    public void add(String actor, Long movie) {
        if (!this.movies.containsKey(movie)) {
            this.movies.put(movie, new HashSet<String>());
        }

        if (!this.actors.containsKey(actor)) {
            this.actors.put(actor, new HashSet<Long>());
        }

        this.movies.get(movie).add(actor.toLowerCase());
        this.actors.get(actor).add(movie);
    }

    // number of degrees is path.size() - 1, if path.size() == 1 then no connection was found
    // should contain end, if it doesn't then no path was found
    public List<String> kevinBacon(String start, String end) {
        HashSet<String> seen = new HashSet<String>();
        seen.add(start);
        List<String> path = new ArrayList<String>();
        path.add(start);
        return kevinBacon(start.toLowerCase(), end.toLowerCase(), seen, path);
    }

    // this is based off of dfs, if this doesn't work then implement bfs instead
    private List<String> kevinBacon(String start, String end, HashSet<String> seen, List<String> path) {
        if (!this.actors.containsKey(start) || !this.actors.containsKey(end)) {
            return path;
        }

        for (Long movie : this.actors.get(start)) {
            if (this.movies.get(movie).contains(end)) {
                path.add(end);
                return path;
            }
            for (String actor : this.movies.get(movie)) {
                if (!seen.contains(actor)) {
                    seen.add(actor);
                    path.add(actor);
                    kevinBacon(actor, end, seen, path);
                    if (path.get(path.size() - 1).equals(end)) {
                        return path;
                    }
                    path.remove(actor);
                }
            }
        }
        return path;
    }
}
