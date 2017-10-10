package GUIProgram.GUIInlämning;

import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class FilteredListModel extends MyListModel {

    ArrayList<Movie> model = new ArrayList<>();
    transient FilterStrategy filter = new ShowAll();
    transient ArrayList<ListDataListener> listener = new ArrayList<>();





    public void filter(FilterStrategy filterStrategy) {
        filter = filterStrategy;
        super.clear();  //Rensar hela listan, prestandan?

        for (Movie m : model) {
            //if (text == null || text.isEmpty() || o.contains(filter) ) {
            if( filterStrategy.filter(m)){
                super.add(m);
            }
        }
    }


    @Override
    public void add(Movie m) {
        model.add(m);
        filter(filter);
    }

    @Override
    public void remove(int index) {
        Movie obj = getElementAt(index);
        model.remove(obj);
        filter(filter);
    }

    @Override
    public void clear() {
        model.clear();
        filter(filter);
    }


}
