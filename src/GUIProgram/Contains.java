package GUIProgram;

import GUIProgram.GUIInlämning.FilterStrategy;
import GUIProgram.GUIInlämning.Movie;

public class Contains implements FilterStrategy {

    private String text;

    public Contains(String text){
        this.text = text.toLowerCase();


    }

    @Override
    public boolean filter(Movie m) {
        return m.getName().toLowerCase().contains(text) ||
                m.getGenre().toLowerCase().contains(text) ||
                (""+m.getYear()).contains(text);
    }
}
