package com.example.textloader;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final static String FILE_NAME = "content.txt";
    private static final String IMAGE_NAME = "/data/data/com.example.textloader/files/scale_1200.jpg";
    private int k = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // сохранение Текста
    public void saveText(View view){

        FileOutputStream fos = null;
        try {
            EditText textBox = findViewById(R.id.editor);
            String text = textBox.getText().toString();

            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    // открытие Картинки
    public void openImg(View view){
        ImageView imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button1);
        if(k==1) {
            Toast.makeText(this, "Картинка открыта", Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(BitmapFactory.decodeFile(IMAGE_NAME));
            button.setText("Скрыть картинку");
        }else{
            Toast.makeText(this, "Картинка закрыта", Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(null);
            button.setText("Показать картинку");
        }
        k*=-1;
    }
    // открытие Текста
    public void openText(View view){

        FileInputStream fin = null;
        TextView textView = findViewById(R.id.text);

        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            textView.setText(text);
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}