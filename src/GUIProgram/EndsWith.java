package GUIProgram;

import GUIProgram.GUIInlämning.FilterStrategy;
import GUIProgram.GUIInlämning.Movie;

public class EndsWith implements FilterStrategy{

    private final String filterText;

    public EndsWith(String filterText){
        this.filterText = filterText;

    }

    @Override
    public boolean filter(Movie m) {
        return m.getName().endsWith(filterText);
    }
}
