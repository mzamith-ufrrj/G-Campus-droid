package tn743.ufrrj.gcampus.j_g_campus_test.gui.frag;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import tn743.ufrrj.gcampus.j_g_campus_test.R;
import tn743.ufrrj.gcampus.j_g_campus_test.logic.BagResources;

public class QRCodeReaderFragment extends Fragment {
    private Button mBtnSave = null;
    private EditText mEdtContent = null;
    private EditText mEdtFormat = null;

    private String mInContent = null;
    private String mInFormat = null;
    public QRCodeReaderFragment() {
        mInContent = new String("");
        mInFormat = new String ("");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        if (getArguments() != null) {
            mInContent = new String(getArguments().getString("Content"));
            mInFormat = new String(getArguments().getString("FormatName"));
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_r_code_reader, container, false);
        mBtnSave = view.findViewById(R.id.btnGuardar);
        mBtnSave.setOnClickListener(v -> storeQRCode());
        mEdtContent = view.findViewById(R.id.edtContent);
        mEdtFormat = view.findViewById(R.id.edtFormat);
        mEdtContent.setText(mInContent);
        mEdtFormat.setText(mInFormat);

        return view;
    }

    private void storeQRCode(){
        String message = new String(mEdtContent.getText().toString());
        BagResources.getInstance().setAndInsertAdd(message);
        Toast.makeText(getContext(), "String armazenada", Toast.LENGTH_SHORT).show();
        getActivity().onBackPressed();
    }
}