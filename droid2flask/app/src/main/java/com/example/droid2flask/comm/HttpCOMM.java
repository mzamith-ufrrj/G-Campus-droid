package com.example.droid2flask.comm;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map;
public class HttpCOMM {
    private String mSTURL = "";
    private String mUSER_AGENT = "Mozilla/5.0";

    private String mBuffer = null;
    private int mTimeoutCOMM = 5000;
    private int mTimeoutREAD = 5000;
    private StringBuffer mResponseData = null;
    private int mResponseCode = -1;

    public Map<String, String> mParams = null;

    public HttpCOMM() { mParams = new HashMap<>(); }
    public StringBuffer getResponseData() { return mResponseData; }
    public int getResponseCode() { return mResponseCode; }
    public void setURL(String surl) { this.mSTURL = new String(surl) ;}
    public void setCOMMTimeOut(int to) {this.mTimeoutCOMM = to; }
    public void setREADTimeOut(int to) {this.mTimeoutREAD = to; }
    public void setUserAgent(String ua) {this.mUSER_AGENT = new String(ua);}

    public String getBuffer() {return this.mBuffer ; }
    public void sendGET() throws IOException {
        //?namegetfname=234&namegetlname=45646
        //System.out.println("GET: Estabelecendo conexão com a URL: " + mSTURL);

        String params = "";
        if (mParams.size() > 0) {
            params = "?";
            for (Map.Entry<String, String> entry: mParams.entrySet()) {
                params += entry.getKey() + "=" + entry.getValue() +  "&";
            }
            params = params.substring(0, params.length() - 1);
        }

        URL obj = new URL(mSTURL+params);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //System.out.println("Informando parâmetros da conexão");

        con.setConnectTimeout(mTimeoutCOMM);
        con.setReadTimeout(mTimeoutREAD);
        con.setRequestProperty("User-Agent", mUSER_AGENT);
        con.setRequestMethod("GET");

        //System.out.println("Obtendo código de resposta da página");
        mResponseCode = con.getResponseCode();
        //System.out.println("\t Código da página: " + Integer.toString(mResponseCode));
        //System.out.println("Lendo a página...");
        if (mResponseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append("\t" + inputLine + "\n");
            }
            in.close();

            // print result
            System.out.println("Imprimindo:");
            System.out.println(response.toString());
        }else//if (responseCode == HttpURLConnection.HTTP_OK) {
            System.err.println("GET request did not work.");
        con.disconnect();
        System.out.println("Desconectado");
    }//public void sendGET() throws IOException {

    public void sendPOST() throws IOException {
        //System.out.println("POST: Estabelecendo conexão com a URL: " + mSTURL);
        URL obj = new URL(mSTURL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //System.out.println("Informando parâmetros da conexão");
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", mUSER_AGENT);


        if (mParams.size() > 0) {
            String params = "";
            for (Map.Entry<String, String> entry: mParams.entrySet()) {
                params += entry.getKey() + "=" + entry.getValue() +  "&";
            }
            params = params.substring(0, params.length() - 1);
            // For POST only - START
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(params.getBytes());
            os.flush();
            os.close();
            // For POST only - END

        }



        //System.out.println("Obtendo código de resposta da página");
        mResponseCode = con.getResponseCode();
        //System.out.println("\t Código da página: " + Integer.toString(mResponseCode));
        //System.out.println("Lendo a página...");
        if (mResponseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            mBuffer = new String();


            while ((inputLine = in.readLine()) != null) {
                mBuffer += inputLine + "\n";

            }
            in.close();

            // print result
            //System.out.println("Imprimindo:");
            //System.out.println(response.toString());
        } else {
            //System.out.println("POST request did not work.");
        }
        con.disconnect();
        //System.out.println("Desconectado");
    }//sendPost
}
