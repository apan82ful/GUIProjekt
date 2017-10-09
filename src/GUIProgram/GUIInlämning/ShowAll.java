package GUIProgram.GUIInlämning;

public class ShowAll implements FilterStrategy {
    @Override
    public boolean filter(Movie m) {
        return true;
    }
}
