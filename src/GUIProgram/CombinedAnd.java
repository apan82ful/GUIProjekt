package GUIProgram;

import GUIProgram.GUIInlämning.FilterStrategy;
import GUIProgram.GUIInlämning.Movie;

public class CombinedAnd implements FilterStrategy {

    private final FilterStrategy f1;
    private final FilterStrategy f2;

    public CombinedAnd(FilterStrategy f1, FilterStrategy f2) {
        this.f1 = f1;
        this.f2 = f2;
    }


    @Override
    public boolean filter(Movie m) {
        return f1.filter(m) && f2.filter(m);
    }
}
