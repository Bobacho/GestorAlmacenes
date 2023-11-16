package com.example.gestoralmacenes.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.FileProvider;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoUsuario;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Camara extends AppCompatActivity {
    Button camara;
    ImageView view;
    String rutaImagen;
    Bitmap bitmapImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        camara=(Button) findViewById(R.id.button);
        view=(ImageView) findViewById(R.id.imageView2);
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara();
            }
        });
    }
    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagenArchivo=null;
        try {
            imagenArchivo=crearImagen();
        }catch (Exception e)
        {
            Log.e("Error",e.toString());
        }
        if(imagenArchivo!=null)
        {
            Uri fotoUri= FileProvider.getUriForFile(this,"com.example.gestoralmacenes.fileprovider",imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
        }
        startActivityForResult(intent, 1);
    }

    private File crearImagen() throws IOException {
        String nombreImagen="imagen";
        File directorio=getExternalFilesDir(null);
        File imagen=File.createTempFile(nombreImagen,".jpg",directorio);
        rutaImagen=imagen.getAbsolutePath();
        return imagen;
    }
    public void borrar(View view) throws SQLException {
        DaoUsuario daoUsuario=new DaoUsuario(this);
        daoUsuario.limpiarTabla();
    }
    public void procesarImagen(View view)
    {
        TextRecognizer textRecognizer=new TextRecognizer.Builder(this).build();
        if(!textRecognizer.isOperational())
        {
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Frame frame=new Frame.Builder().setBitmap(bitmapImagen).build();
            SparseArray<TextBlock> textBlockSparseArray=textRecognizer.detect(frame);
            StringBuilder stringBuilder=new StringBuilder();
            for(int i=0;i<textBlockSparseArray.size();i++)
            {
                TextBlock textBlock=textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append("\n");
            }
            Toast.makeText(this,"Texto: "+stringBuilder.toString(),Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,Registro.class);
            i.putExtra("Sentencia",stringBuilder.toString());
            startActivity(i);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK) {
            bitmapImagen= BitmapFactory.decodeFile(rutaImagen);
            view.setImageBitmap(bitmapImagen);
        }
    }

}