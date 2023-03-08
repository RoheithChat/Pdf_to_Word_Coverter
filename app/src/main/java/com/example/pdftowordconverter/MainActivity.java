package com.example.pdftowordconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Environment;
import android.util.Log;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SELECT_PDF_FILE = 1;
    private static final int REQUEST_CODE_SELECT_DESTINATION_FOLDER = 2;

    private File selectedPdfFile;
    private File selectedDestinationFolder;

    public void SelectPdfFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, REQUEST_CODE_SELECT_PDF_FILE);
    }

    public void SelectDestinationFolder(View view) {
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
            selectedPdfFile = new File(pdfUri.getPath());
        } else if (requestCode == REQUEST_CODE_SELECT_DESTINATION_FOLDER && resultCode == RESULT_OK) {
            Uri destinationUri = data.getData();
            selectedDestinationFolder = new File(destinationUri.getPath());
        }
        Button Convert = findViewById(R.id.button3);
        Convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PDDocument pdf = PDDocument.load(new FileInputStream(selectedPdfFile));
                    PDFTextStripper stripper = new PDFTextStripper();
                    String text = stripper.getText(pdf);
                    XWPFDocument word = new XWPFDocument();
                    XWPFParagraph paragraph = word.createParagraph();
                    XWPFRun run = paragraph.createRun();
                    run.setText(text);
                    FileOutputStream out = new FileOutputStream(new File(String.valueOf(selectedDestinationFolder)));
                    word.write(out);
                    out.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }}


