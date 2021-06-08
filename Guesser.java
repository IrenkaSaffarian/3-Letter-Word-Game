package com.example.thegame;
import java.util.ArrayList;

public class Guesser {

    private LetterSpot[] word = new LetterSpot[3];
    private static final String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private static final String[] consonants = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z"};
    private static final String[] vowels = {"a", "o", "i", "e", "u", "y"};
    SecretKeeper keeper;
    private boolean notRevealed = true;

    public Guesser(WordList dictionary)
    {
        word[0] = new LetterSpot();
        word[1] = new LetterSpot();
        word[2] = new LetterSpot();
        keeper= new SecretKeeper(dictionary);
        keeper.reveal();
    }

    /*
     *
     */
    public void takeGuess()
    {
        int knownCount=0;
        int unknownIndex =-1;//we only kneed to know unknownIndex if there is one unknown
        for (int i=0; i<3; i++)
        {
            if (word[i].isKnown())
            {
                knownCount++;
            }
            else{unknownIndex=i;}
        }

        if (knownCount==2)
        {
            changeOne(unknownIndex);//changeOne() will guess from the alphabet array until word[unknownIndex] is found
            takeGuess();// Now we have found the last LetterSpot, so takeGuess() will stop running.
        }
        else if(knownCount==1)
        {
            changeTwo();//changeTwo calls itself recursively until it has found & updated one LetterSpot in word
            takeGuess();
        }
        else if (knownCount==0)
        {
            //for every index that is possibly a vowel, run findVowel(). One of them will be a vowel.
            int j=0;
            while (j<3)
            {
                boolean vowelFound = findVowel(j);
                if (vowelFound)
                {
                    break;
                }
                else
                {
                    j++;
                }
            }
            takeGuess();
        }
        else if (knownCount==3 && notRevealed)
        {
            System.out.println("I am the Guesser. The word was "+ word[0]+word[1]+word[2]+
                    " and it took "+keeper.getNumGuesses()+" guesses.\n");
            notRevealed= !notRevealed;//so that the recursive method doesn't print three times
        }
    }

    public void changeTwo()
    {
        int knownIndex =-1;//recognize the index that is already known so that we don't change it
        for (int i=0; i< 3; i++)
        {
            if(word[i].isKnown()){knownIndex=i;}
        }

        //make two indexes for the unknown letters
        int indexA= knownIndex+1;
        int indexB= knownIndex-1;
        if (indexA>2) {indexA=0;}
        else if(indexB<0) {indexB=2;}

        //randomly guess two new chars, charA and charB,
        //which aren't impossible at their respective locations
        String charA = "";
        boolean isTested= true;
        while(isTested)
        {
            charA=alphabet[(int)(Math.random()*alphabet.length)];
            isTested=word[indexA].getImpossibleVals().contains(charA);
        }

        String charB = "";
        isTested= true;
        while(isTested)
        {
            charB=alphabet[(int)(Math.random()*alphabet.length)];
            isTested=word[indexB].getImpossibleVals().contains(charB);
        }

        //create new guess using the known chars and the new guesses at the proper indexes
        StringBuilder newWord = new StringBuilder("   ");
        newWord.replace(indexA, indexA+1, charA);
        newWord.replace(indexB, indexB+1, charB);
        newWord.replace(knownIndex, knownIndex+1, word[knownIndex].getValue());

        if (keeper.takeGuess(newWord.toString()).equals("o"))//if nothing has changed, neither new letter is correct
        {
            word[indexA].getImpossibleVals().add(charA);
            word[indexB].getImpossibleVals().add(charB);
            changeTwo();
        }
        else if (keeper.takeGuess(newWord.toString()).equals("e"))//assuming one of the vals is correct if we get "e"
        {
            newWord.replace(indexA, indexA+1, " ");//spaces are never allowed
            if (keeper.takeGuess(newWord.toString()).equals("o"))//basically, we change one val to see if the output changes. If it does, we know that that guess was correct.
            {word[indexA].setVal(charA);}
            else
            {word[indexB].setVal(charB);}
        }
        else if (keeper.takeGuess(newWord.toString()).equals("found"))
        {
            word[indexA].setVal(charA);
            word[indexB].setVal(charB);
        }
    }

    public void changeOne(int index)
    {
        StringBuilder newWord = new StringBuilder("   ");

        String randomGuess = "";
        boolean isTested=true;
        while(isTested)
        {
            randomGuess=alphabet[(int)(Math.random()*alphabet.length)];
            isTested=word[index].getImpossibleVals().contains(randomGuess);
        }

        int knownIndexA= index-1;
        int knownIndexB= index+1;
        if (knownIndexB>2) {knownIndexB=0;}
        else if(knownIndexA<0) {knownIndexA=2;}

        newWord.replace(knownIndexA, knownIndexA+1, word[knownIndexA].getValue());
        newWord.replace(knownIndexB, knownIndexB+1, word[knownIndexB].getValue());
        newWord.replace(index, index+1, randomGuess);

        if (keeper.takeGuess(newWord.toString()).equals("found"))//since we already know two, if our guess is correct keeper will return "found"
        {
            word[index].setVal(randomGuess);
        }
        else
        {
            word[index].addImpossibleVal(randomGuess);
            changeOne(index);
        }
    }


    public boolean findVowel(int index)
    {
        boolean found=false;
        while (!found)
        {
            if (!word[index].mayBeVowel())//quits and returns false if impossibleVals for word[index] contains every vowel
            {
                return found;
            }

            int indexA=index-1;
            int indexB =index+1;
            if (indexA<0) {indexA=2;}
            if (indexB>2) {indexB=0;}

            String randomVowelGuess = vowels[(int)(Math.random()*vowels.length)];
            while(word[index].getImpossibleVals().contains(randomVowelGuess))//error warning: infinite loop if impossibleVals contains every vowel
            {
                randomVowelGuess = vowels[(int)(Math.random()*vowels.length)];
            }

            StringBuilder newWord = new StringBuilder("   ");
            newWord.replace(indexA, indexA+1, " ");
            newWord.replace(indexB, indexB+1, " ");
            newWord.replace(index, index+1, randomVowelGuess);

            if (keeper.takeGuess(newWord.toString()).equals("o"))//the spaces will both be wrong, so if the guess is right takeGuess() returns "o"
            {
                word[index].setVal(randomVowelGuess);
                found=true;
                return found;
            }
            else
            {
                word[index].addImpossibleVal(randomVowelGuess);
            }
        }
        System.out.println("flag");
        return false;
    }


}
