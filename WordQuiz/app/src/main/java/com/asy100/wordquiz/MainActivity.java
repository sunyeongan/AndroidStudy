package com.asy100.wordquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {


    //이미지 인식
    TessBaseAPI tessBaseAPI;
    String dataPath = "";
    String langData = "kor";

    //사진 가져오기
    Uri photoUri;
    private static final int PICK_FROM_ALBUM = 2; // [앨범에서 사진 가져오기]
    private static final int CROP_FROM_CAMERA = 3; // [가져온 사진을 자르기 위한 변수]

    String result_image_string;
    ImageView imageView;
    TextView OCRTextView; //OCR 결과뷰
    Intent intent = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.main_image);
        OCRTextView = findViewById(R.id.OCRTextView);

        Button imageButton = findViewById(R.id.main_button);
        imageButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {


                goGallary();
            }
        }) ;

        //TessBaseAPI 객체 초기화 수행 실시
        try {
            dataPath = getFilesDir() + "/tesseract/";
            checkFile(new File(dataPath + "tessdata/"), "kor");


            tessBaseAPI = new TessBaseAPI();
            tessBaseAPI.init(dataPath, langData);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        // 글자 추출하기 버튼을 눌렀을 때
        Button findText = findViewById(R.id.text_load);
        findText.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOcrConvert(imageView);
                OCRTextView.setText(result_image_string);
            }
        }) ;




    }

    public void goGallary(){
        Log.d("---","---");
        Log.d("//===========//","================================================");
        Log.d("","\n"+"[A_ScopePicture > goGallary() 메소드 : 갤러리 인텐트 이동 실시]");
        Log.d("//===========//","================================================");
        Log.d("---","---");
        try {
            //Intent intent = new Intent(Intent.ACTION_PICK); //[기존]
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //[변경]
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            startActivityForResult(intent, PICK_FROM_ALBUM);
            overridePendingTransition(0, 0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //OCR 인식 위한 파일 존재 확인 메소드 작성
    public void checkFile(File dir, String lang){
        Log.d("---","---");
        Log.d("//===========//","================================================");
        Log.d("","\n"+"[A_CameraOcr > checkFile() 메소드 : OCR 인식 위한 파일 존재 확인 실시]");
        Log.d("","\n"+"[언어 파일 : "+String.valueOf(lang)+"]");
        Log.d("//===========//","================================================");
        Log.d("---","---");
        try {
            if(!dir.exists() && dir.mkdirs()){
                copyFiles(lang);
            }

            if(dir.exists()){
                String dataFilePath = dataPath + "/tessdata/" + lang + ".traineddata";
                File dataFile = new File(dataFilePath);
                if(!dataFile.exists()){
                    copyFiles(lang);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //OCR 인식 위한 언어 파일 복사 메소드 작성
    public void copyFiles(String lang){
        Log.d("---","---");
        Log.d("//===========//","================================================");
        Log.d("","\n"+"[A_CameraOcr > copyFiles() 메소드 : OCR 인식 위한 언어 파일 복사 실시]");
        Log.d("","\n"+"[언어 파일 : "+String.valueOf(lang)+"]");
        Log.d("//===========//","================================================");
        Log.d("---","---");
        try {
            String filePath = dataPath + "/tessdata/" + lang + ".traineddata";

            AssetManager assetManager = getAssets();

            InputStream inputStream = assetManager.open("tessdata/" + lang + ".traineddata");
            OutputStream outputStream = new FileOutputStream(filePath);

            byte[] buffer = new byte[1024];
            int read;
            while((read = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //이미지 뷰에 표시된 사진을 OCR 컨버팅 수행 실시]
    public void getOcrConvert(ImageView image){
        Log.d("---","---");
        Log.d("//===========//","================================================");
        Log.d("","\n"+"[A_CameraOcr > getOcrConvert() 메소드 : OCR 인식 수행 실시]");
        Log.d("//===========//","================================================");
        Log.d("---","---");
        try {
            //TODO [drawable 리소스를 비트맵으로 가져오기]
            //Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.icon_resource);

            //TODO [이미지 뷰의 리소스를 비트맵으로 가져오기]
            Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();

            //TODO [OCR 인식 수행]
            tessBaseAPI.setImage(bitmap);
            String result = String.valueOf(tessBaseAPI.getUTF8Text());

            //TODO [Alert 팝업창 호출 실시]
            getAlertText(
                    String.valueOf(result));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getAlertText(String content){

        result_image_string = content;

    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //TODO [갤러리에서 응답을 받은 경우]
        if (requestCode == PICK_FROM_ALBUM) {
            Toast.makeText(getApplication(), "잠시만 기다려주세요 ... ", Toast.LENGTH_SHORT).show();
            if (data == null) {
                return;
            }
            photoUri = data.getData();

            Log.d("---","---");
            Log.w("//===========//","================================================");
            Log.d("","\n"+"[A_ScopePicture > onActivityResult() 메소드 : 갤러리 응답 확인 실시]");
            Log.d("","\n"+"[파일 경로 : "+String.valueOf(photoUri)+"]");
            Log.w("//===========//","================================================");
            Log.d("---","---");
            try {
                // [선택한 이미지에서 비트맵 생성]
                InputStream in = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(in);
                img = Bitmap.createScaledBitmap(img,400,400,true);
                in.close();

                // [이미지 뷰에 이미지 표시]
                imageView.setImageBitmap(img);


            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }






}