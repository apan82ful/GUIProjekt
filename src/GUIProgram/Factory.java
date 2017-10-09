package GUIProgram;

import GUIProgram.GUIInlämning.FilteredListModel;
import GUIProgram.GUIInlämning.IMyListModel;

public class Factory {

    public static IMyListModel createListModel(){
        return new FilteredListModel();
    }
}
