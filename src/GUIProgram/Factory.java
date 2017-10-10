package GUIProgram;

import GUIProgram.GUIInlämning.FilterStrategy;
import GUIProgram.GUIInlämning.FilteredListModel;
import GUIProgram.GUIInlämning.IMyListModel;
import GUIProgram.GUIInlämning.ShowAll;

public class Factory {

    public static IMyListModel createListModel(){
        return new FilteredListModel();
    }

    public static ShowAll createShowAll(){
        return new ShowAll();
    }

    public static CombinedOr createCombinedOr(FilterStrategy f1, FilterStrategy f2){
        return new CombinedOr(f1, f2);
    }

    public static StartsWith createStartsWith(String s){
        return new StartsWith(s);
    }

    public static EndsWith createEndsWith(String s){
        return new EndsWith(s);
    }

    public static Contains createContains(String s){
        return new Contains(s);
    }


}
