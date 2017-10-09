package GUIProgram.GUIInlämning;

import javax.swing.*;

public interface IMyListModel extends ListModel <Movie> {
    void update(int index, Movie movie);

    void add(Movie movie);

    void remove(int index);

    void clear();

    void filter(FilterStrategy strategy);
}
