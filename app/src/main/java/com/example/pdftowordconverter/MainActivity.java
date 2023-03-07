package com.example.pdftowordconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SELECT_PDF_FILE = 1;
    private static final int REQUEST_CODE_SELECT_DESTINATION_FOLDER = 2;

    public void SelectPdfFile(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, REQUEST_CODE_SELECT_PDF_FILE);
    }

    public void SelectDestinationFolder(View view){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        startActivityForResult(intent, REQUEST_CODE_SELECT_DESTINATION_FOLDER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SELECT_PDF_FILE && resultCode == RESULT_OK) {
            Uri pdfUri = data.getData();
            // Do something with the PDF file URI
        }
        else if (requestCode == REQUEST_CODE_SELECT_DESTINATION_FOLDER && resultCode == RESULT_OK) {
            Uri DestinationUri = data.getData();
            // Do something with the selected folder URI
        }
    }
}
