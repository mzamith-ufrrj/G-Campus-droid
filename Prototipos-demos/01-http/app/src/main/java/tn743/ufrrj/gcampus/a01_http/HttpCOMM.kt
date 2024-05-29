package tn743.ufrrj.gcampus.a01_http

import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL


class HttpCOMM {
    var mSTURL:String = ""
    var mUSER_AGENT:String = "Mozilla/5.0"
    var mTimeoutCOMM:Int = 5000
    var mTimeoutREAD:Int = 5000
    var mResponseData: String? = null
    var mResponseCode:Int = -1
    var mParams: HashMap<String, String>? = null

    init{ mParams = HashMap<String, String>()  }

    fun sendGET(){
        var params = ""
        if (mParams!!.size > 0) {
            params = "?"
            for ((key, value) in mParams!!) {
                params += "$key=$value&"
            }
            params = params.substring(0, params.length - 1)
        }//if (mParams!!.size > 0) {
        val conn = URL(mSTURL+params).openConnection() as HttpURLConnection
        conn.readTimeout = mTimeoutREAD
        conn.connectTimeout = mTimeoutCOMM
        conn.setRequestProperty("User-Agent", mUSER_AGENT)
        conn.requestMethod = "GET"
        conn.doInput = true
        val responseCode: Int = conn.responseCode // To Check for 200
        mResponseData = String()

        if (responseCode == HttpURLConnection.HTTP_OK){
            val data = conn.inputStream.bufferedReader().readText()
            mResponseData = data

            //https://stackoverflow.com/questions/29802323/android-with-kotlin-how-to-use-httpurlconnection
            //val connection = URL("http://www.android.com/").openConnection() as HttpURLConnection
            //val data = connection.inputStream.bufferedReader().readText()
            // http is not allowed by default on android. Take a look in this doc, special case solution 3 -> just Manifest file parameter
            //https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
            //Copy data to string
            //return data //"Terminamos de processar"
        }
            //In this case, the data string must be empty

    }//fun sendGET(){

    fun sendPOST(){
        val conn = URL(mSTURL).openConnection() as HttpURLConnection
        var params = ""
        conn.readTimeout = mTimeoutREAD
        conn.connectTimeout = mTimeoutCOMM
        conn.setRequestProperty("User-Agent", mUSER_AGENT)
        conn.requestMethod = "POST"
        conn.doInput = true

        if (mParams!!.size > 0) {
            conn.doOutput = true
            for ((key, value) in mParams!!) {
                params += "$key=$value&"
            }
            params = params.substring(0, params.length - 1)

            val os: OutputStream = conn.getOutputStream()
            os.write(params.toByteArray())
            os.flush()
            os.close()
        }//if (mParams!!.size > 0) {





        val responseCode: Int = conn.responseCode // To Check for 200
        mResponseData = String()

        if (responseCode == HttpURLConnection.HTTP_OK){
            val data = conn.inputStream.bufferedReader().readText()
            mResponseData = data

            //https://stackoverflow.com/questions/29802323/android-with-kotlin-how-to-use-httpurlconnection
            //val connection = URL("http://www.android.com/").openConnection() as HttpURLConnection
            //val data = connection.inputStream.bufferedReader().readText()
            // http is not allowed by default on android. Take a look in this doc, special case solution 3 -> just Manifest file parameter
            //https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
            //Copy data to string
            //return data //"Terminamos de processar"
        }
        //In this case, the data string must be empty

    }//fun sendPOST(){
}