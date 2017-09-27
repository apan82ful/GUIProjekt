package GUIProgram.GUIInlämning;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class MyListModel<T> implements ListModel<T> {

    ArrayList<T> listItems = new ArrayList<>();
    ArrayList<ListDataListener> listener = new ArrayList<>();

    @Override
    public int getSize() {
        return listItems.size();
    }

    @Override
    public T getElementAt(int index) {
        return listItems.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listener.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listener.remove(l);
    }

    public void update(int index, T o) {
        listItems.set(index, o);
        for (ListDataListener l : listener ) {
            l.contentsChanged(new ListDataEvent(this,ListDataEvent.CONTENTS_CHANGED,index,index));
        }
    }

    public void add(T o) {
        listItems.add(o);
        for (ListDataListener l : listener ) {
            l.intervalAdded(new ListDataEvent(this,ListDataEvent.INTERVAL_ADDED,listItems.size()-1,listItems.size()-1));
        }
    }

    public void remove(int index){
        listItems.remove(index);
        for (ListDataListener l : listener ) {
            l.intervalRemoved(new ListDataEvent(this,ListDataEvent.INTERVAL_REMOVED,index, index));
        }
    }
}