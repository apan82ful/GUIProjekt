package GUIProgram;

import GUIProgram.GUIInlämning.*;

public class Factory {

    public static IMyListModel createListModel(){
        return new FilteredListModel();
    }

    public static FilterStrategy createShowAll(){
        return new ShowAll();
    }

    public static FilterStrategy createCombinedOr(FilterStrategy f1, FilterStrategy f2){
        return new CombinedOr(f1, f2);
    }

    public static FilterStrategy createStartsWith(String s){
        return new StartsWith(s);
    }

    public static FilterStrategy createEndsWith(String s){
        return new EndsWith(s);
    }

    public static FilterStrategy createContains(String s){
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
    public static FilterStrategy createFilter(String text, boolean startsWith, boolean endsWith){
        if (text.isEmpty())
            return createShowAll();
        else if (startsWith && endsWith)
            return createCombinedOr(createStartsWith(text),createEndsWith(text));

        else if (startsWith)
            return createStartsWith(text);

        else if (endsWith)
            return createEndsWith(text);

        else
            return createContains(text);

    }


}
