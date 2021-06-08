# 3-Letter-Word-Game
An optimized solution for guessing a three-letter word randomly chosen from a list of words.
Solution uses four classes: WordList, Guesser, SecretKeeper, and LetterSpot. 

LetterSpot represents a character at a certain index of the unknown word. Stores characters which are known NOT to be at that index in the secret word. When character is found, stores value.

WordList contains a basic list of three-letter words. Contains test method for game. 

SecretKeeper chooses a random word from a given WordList. takeGuess(String guess) method returns "o" if guess has an odd number of letters (at correct indexes) in common with secret word, "e" if even, and "found" if three. 0 is an even number. Counts number of guesses taken. 

Guesser creates a SecretKeeper object and three LetterSpot objects stored in an array. takeGuess() method runs recursively until all three LetterSpots have stored correct values (game finished). 
