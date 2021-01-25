/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList = new ArrayList<String>();
    private HashSet<String> wordSet =new HashSet<String>();
    private HashMap<String, ArrayList<String>> letterstoWord = new HashMap<String, ArrayList<String>>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
           wordList.add(word);
           wordSet.add(word);
           if(letterstoWord.containsKey(sortLetters(word))){

             ArrayList<String> newList = letterstoWord.get(sortLetters(word));
             newList.add(word);
             letterstoWord.put(sortLetters(word), newList);

           }
           else{
               ArrayList<String> newestList= new ArrayList<String>();
               newestList.add(word);
               letterstoWord.put(sortLetters(word), newestList);
           }

        }
    }

    public boolean isGoodWord(String word, String base) {
        if(wordSet.contains(word)){
            if(word.contains(base)){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        for(int i =0; i<wordList.size();i++){
            if(wordList.get(i).length() == targetWord.length()){
                if(sortLetters(targetWord).equals(sortLetters(wordList.get(i)))){
                    result.add(wordList.get(i));
                }
            }

        }
        return result;
    }

    public String sortLetters (String word){
        char[] characters = word.toCharArray();
        Arrays.sort(characters);
        String result = Arrays.toString(characters);
        return result;

    }
    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for(int i =0; i< alphabet.length;i++){
                if(letterstoWord.containsKey(sortLetters(word+alphabet[i]))){
                    result.addAll(letterstoWord.get(sortLetters(word+alphabet[i])));
                }

        }

        return result;
    }

    public String pickGoodStarterWord() {

        int randNumber = random.nextInt(wordList.size());
        for(int i =randNumber; i<wordList.size();i++){
           if(getAnagramsWithOneMoreLetter(wordList.get(i)).size() == MIN_NUM_ANAGRAMS){
               return wordList.get(i);
           }
        }
        return "";
    }


}
