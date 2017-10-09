package GUIProgram;

import GUIProgram.GUIInlämning.FilterStrategy;
import GUIProgram.GUIInlämning.Movie;

public class StartsWith implements FilterStrategy {

    private String filtertext;

    public StartsWith(String filtertext){
        this.filtertext = filtertext;
    }

    @Override
    public boolean filter(Movie m) {
        return m.getName().startsWith(filtertext);
    }
}
