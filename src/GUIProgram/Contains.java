package GUIProgram;

import GUIProgram.GUIInlämning.FilterStrategy;
import GUIProgram.GUIInlämning.Movie;

public class Contains implements FilterStrategy {

    private String text;

    public Contains(String text){
        this.text = text;

    }

    @Override
    public boolean filter(Movie m) {
        return m.getName().contains(text);
    }
}
