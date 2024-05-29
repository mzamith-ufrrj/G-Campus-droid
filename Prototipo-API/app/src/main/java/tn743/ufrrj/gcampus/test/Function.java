package tn743.ufrrj.gcampus.test;
import java.util.ArrayList;
public class Function {
    private String mName;
    private boolean mOnline;

    public Function(String name, boolean online) {
        mName = name;
        mOnline = online;
    }//public Function(String name, boolean online) {
    public String getName() {return mName;}
    public boolean isOnline() {return mOnline;}

    private static int lastFunctionId = 0;

    public static ArrayList<Function> createFunctionList() {
        ArrayList<Function> functions = new ArrayList<Function>();
        functions.add(new Function("Configuração", true));
        functions.add(new Function("Login", true));
        functions.add(new Function("Autenticação", true));
        functions.add(new Function("Desafios", true));
        functions.add(new Function("Ranqueamento", true));
        functions.add(new Function("Desafio GPS", false));


        return functions;
    }
}//public class Function {
