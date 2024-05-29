package tn743.ufrrj.gcampus.j_g_campus_test.menumain;

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
        //functions.add(new Function("Login", true));
        //functions.add(new Function("Autenticação", false));
        functions.add(new Function("Desafios", true));
        functions.add(new Function("Desafios incompletos", true));
        functions.add(new Function("Ranqueamento", false));
        functions.add(new Function("Desafio GPS", false));
        functions.add(new Function("Ler QRCode", true));
        functions.add(new Function("Gerar QRCode", true));
        functions.add(new Function("Baixar desafios", false));
        //functions.add(new Function("Forca", true));


        return functions;
    }
}//public class Function {