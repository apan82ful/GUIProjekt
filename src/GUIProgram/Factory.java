package GUIProgram;

import GUIProgram.GUIInlämning.*;

public class Factory {

    public static IMyListModel createListModel(){
        return new FilteredListModel();
    }

    public static FilterStrategy createShowAll(){
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


    public static Movie createMovie(String name, String genre, String yearString) throws IllegalArgumentException, NumberFormatException{

        if (name.isEmpty())
            throw new IllegalArgumentException("Name is empty");
        if (genre.isEmpty())
            throw new IllegalArgumentException("genre is empty");
        if(yearString.isEmpty())
            throw new IllegalArgumentException("Year is empty");

        return new Movie(name,genre,Integer.parseInt(yearString));
    }
}
