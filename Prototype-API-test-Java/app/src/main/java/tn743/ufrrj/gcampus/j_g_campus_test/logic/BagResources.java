package tn743.ufrrj.gcampus.j_g_campus_test.logic;

import java.util.HashMap;
/*
- Implementing an algorithm to keep saving all collected resources - May, 9th 2024
 */
public class BagResources {
    private HashMap<Integer, Integer> mCharacterBag = null;
    private static volatile BagResources INSTANCE = null;

    public BagResources(){
        mCharacterBag = new HashMap<Integer, Integer>();
    }
    public static BagResources getInstance() {
        // Check if the instance is already created
        if(INSTANCE == null) {
            // synchronize the block to ensure only one thread can execute at a time
            synchronized (BagResources.class) {
                // check again if the instance is already created
                if (INSTANCE == null) {
                    // create the singleton instance
                    INSTANCE = new BagResources();
                }
            }
        }
        // return the singleton instance
        return INSTANCE;
    }
    public boolean setAndInsertAdd(String intext) {
        String txt = new String(intext.toUpperCase()) ;
        boolean ret = false;
        for (int i = 0; i < txt.length(); i++) {
            int key = (int) txt.charAt(i);

            if (!mCharacterBag.containsKey(key)) {
                mCharacterBag.put(key, 1);
                ret = true;
            }else {
                mCharacterBag.put(key, mCharacterBag.get(key) + 1);
                ret = true;
            }//if (!mCharacterBag.containsKey(ascii))
        }//for (int i = 0; i < txt.length(); i++) {
        return ret;
    }//private static void setAndInsertAdd(String txt) {

    public boolean getAndSub(char[] cAnswer, char[] inout_answer ) {
        boolean flag = false;
        for (int i = 0; i < cAnswer.length; i++) {
            if (inout_answer[i] == '#') {
                int key =  (int) cAnswer[i];
                if (mCharacterBag.containsKey(key)) {
                    int num = mCharacterBag.get(key);
                    if (num > 0) {
                        inout_answer[i] = cAnswer[i];
                        mCharacterBag.put(key, mCharacterBag.get(key) - 1);
                        flag = true;
                    }//if (num > 0) {
                }//if (mCharacterBag.containsKey(key)) {
            }//if (inout_answer[i] == '_') {
        }//for (int i = 0; i < cAnswer.length; i++) {
        return flag;
    }

    public String getAllCharacter(){
        String output = new String("[");
        for (HashMap.Entry<Integer, Integer> entry: mCharacterBag.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            output += "[" + (char)(key) + "," + Integer.toString(value) + "];";
        }

        output = output.substring(0, output.length()-1) + "]";
        return output;
    }
}//public class BagResources {
