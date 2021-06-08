package com.example.thegame;
import java.util.ArrayList;

public class LetterSpot {
    private String value;
    private ArrayList<String> impossibleVals= new ArrayList<String>();
    private static final String[] vowels = {"a", "e", "i", "o", "u", "y"};

    /**
     * Constructor for objects of class LetterSpot
     */
    public LetterSpot()
    {
    }

    public void setVal(String n)
    {
        if (!isPossible(n)) {System.out.println("The letter "+n+" is in the list of impossibles!");}
        else if (value==null)//only updates value once
        {value = n;}
    }

    public void addImpossibleVal(String n)
    {
        boolean isFound = false;
        for (String impossibleVal: impossibleVals)
        {
            if(impossibleVal.equals(n)) {isFound=true;}
        }
        if(!isFound) {impossibleVals.add(n);} //if the impossible value is already in the impossibles list,
        //don't add it
    }

    public boolean isPossible(String n)
    {
        boolean isFound = false;
        for (String impossibleVal: impossibleVals)
        {
            if(impossibleVal.equals(n)) {isFound=true;}
        }
        return !isFound;
    }

    public boolean isKnown()
    {
        if (value==null || impossibleVals.size()==26) {return false;}
        return true;
    }

    public boolean mayBeVowel()
    {
        int impossibleVowelCount=0;
        for (String v: vowels)
        {
            if (!isPossible(v))
            {impossibleVowelCount++;}
        }
        return (impossibleVowelCount<vowels.length);
    }

    public String getValue()
    {
        if (value==null)
            return "";
        else {return value;}
    }

    public ArrayList<String> getImpossibleVals()
    {
        return impossibleVals;
    }

    public String toString()
    {
        return value;
    }
}
