package tn743.ufrrj.gcampus.j_g_campus_test.gui.acti;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.integration.android.IntentIntegrator;
import tn743.ufrrj.gcampus.j_g_campus_test.R;
import tn743.ufrrj.gcampus.j_g_campus_test.gui.frag.QRCodeWriterFragment;
import tn743.ufrrj.gcampus.j_g_campus_test.gui.frag.QRCodeReaderFragment;

public class QRCodeActivity extends AppCompatActivity {
    static private final String TAG = "QRCodeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        int opt = -1;
        Intent indent = getIntent();
        if (indent != null) {
            opt = indent.getIntExtra("type", -1);
            if (opt == MainActivity.QRCODE_W){
                QRCodeWriterFragment writeQRCode = new QRCodeWriterFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.qrcode_fragmentView, writeQRCode)
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
                return;
            }

            if (opt == MainActivity.QRCODE_R){

                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.setPrompt("Scan a barcode");
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setOrientationLocked(false);
                integrator.setBeepEnabled(true);
                //integrator.setCaptureActivity(this);

                integrator.initiateScan();
                return;
            }

        }//if (indent != null) {

    }//protected void onCreate(Bundle savedInstanceState) {

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //FragmentManager fm = getSupportFragmentManager();
        //fm.popBackStack();
        getSupportFragmentManager().popBackStack();
        finish();
    }
    //Return of camera qrcode capture
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                this.finish();
            } else {
                QRCodeReaderFragment reader = new QRCodeReaderFragment();
                Bundle args = new Bundle();

                args.putString("Content", intentResult.getContents());
                args.putString("FormatName", intentResult.getFormatName());
                reader.setArguments(args);
                    /*
                    ViewGroup.LayoutParams params = config.getView().getLayoutParams();
                    params.width = width;
                    params.height = height;
                    config.getView().setLayoutParams(params);
                    */
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.qrcode_fragmentView, reader)
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();

                // if the intentResult is not null we'll set
                // the content and format of scan message
                Toast.makeText(getBaseContext(), intentResult.getContents(), Toast.LENGTH_SHORT).show();
                //messageText.setText(intentResult.getContents());
                //messageFormat.setText(intentResult.getFormatName());
            }
        } /*else {
            super.onActivityResult(requestCode, resultCode, data);
        }*/
    }//protected void onActivityResult(int requestCode, int resultCode, Intent data) {
}//public class QRCodeActivity extends AppCompatActivity {