package tn743.ufrrj.gcampus.j_g_campus_test.gui.frag;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import tn743.ufrrj.gcampus.j_g_campus_test.R;

public class QRCodeWriterFragment extends Fragment {

    public final static int WIDTH = 512;
    public final static int HEIGHT = 512;
    private Button mBtnGenerate = null;
    private EditText mText = null;
    private ImageView mImgQRCode = null;
    public QRCodeWriterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

 
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_r_code_writer, container, false);
        mBtnGenerate = view.findViewById(R.id.btnGenerate);
        mText = view.findViewById(R.id.edtString);
        mImgQRCode =view.findViewById(R.id.imgQRCode);
        mBtnGenerate.setOnClickListener(v -> generateQRCode());
        return view;
    }

    private void generateQRCode(){
        String text = new String(mText.getText().toString());
        try {
            Bitmap bitmap = encodeAsBitmap(text);
            mImgQRCode.setImageBitmap(bitmap);
        } catch (WriterException ex) {
            ex.printStackTrace();
        }
    }

    private Bitmap encodeAsBitmap(@NonNull String str) throws WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(str, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);

        int w = bitMatrix.getWidth();
        int h = bitMatrix.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                pixels[y * w + x] = bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
        return bitmap;
    }

}//public class QRCodeWriterFragment extends Fragment {