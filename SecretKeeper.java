package com.example.thegame;
import java.util.ArrayList;
import java.util.Scanner;

public class SecretKeeper
{
    private int numGuesses;
    private String secretWord;
    private static final String[] vowels = {"a", "e", "i", "o", "u", "y"};

    public SecretKeeper(WordList dictionary)
    {
        String[] words = dictionary.getList();
        while(!containsVowel(secretWord))
        {
            int index = (int)(Math.random()* words.length);
            secretWord = words[index];
        }

    }

    public boolean containsVowel(String str)
    {
        if (str==null) return false;

        int vowelCount =0;
        for (String vowel: vowels)
        {
            for (int i=0; i<3; i++)
            {
                if (str.substring(i, i+1).equals(vowel))
                {
                    vowelCount++;
                }
            }
        }
        return (vowelCount>1);
    }

    public String takeGuess(String n)
    {
        numGuesses++;
        int count=0;
        for (int i=0; i<3; i++)
        {
            if (n.substring(i,i+1).equals(secretWord.substring(i,i+1)))
            {count++;}
        }
        if (count==0 || count==2) {return "e";}
        else if (count==1) {return "o";}
        else return "found";
    }

    public int getNumGuesses()
    {
        return numGuesses;
    }

    public void reveal()
    {
        System.out.println("I am the SecretKeeper. The word was "+secretWord);
        System.out.println();
    }
}
