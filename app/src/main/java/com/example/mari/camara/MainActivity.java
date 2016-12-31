package com.example.mari.camara;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    //declaramos las variables contenedoras
    private static final int VIDEO_CAPTURE = 101;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checamos que el celular tenga camara
        Button elButton = (Button) findViewById(R.id.elbutton);

        if (hasCamera()) {
            elButton.setEnabled(true);
        }
    }//onCreate

    private boolean hasCamera() {
        return (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY));
    }

    public void videoGrabar(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, VIDEO_CAPTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri videoUri = data.getData();
        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Tu imagen esta en \n" + videoUri, Toast.LENGTH_LONG).show();

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Tamaño del imagen cancelado \n", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Falló la cancelación de la imagen \n", Toast.LENGTH_LONG).show();

            }
        }

    }
}
