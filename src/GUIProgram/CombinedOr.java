package GUIProgram;

import GUIProgram.GUIInlämning.FilterStrategy;
import GUIProgram.GUIInlämning.Movie;

public class CombinedOr implements FilterStrategy {

    private final FilterStrategy f1;
    private final FilterStrategy f2;

    public CombinedOr(FilterStrategy f1, FilterStrategy f2) {
        this.f1 = f1;
        this.f2 = f2;
    }


    @Override
    public boolean filter(Movie s) {
        return f1.filter(s) || f2.filter(s);
    }
}
