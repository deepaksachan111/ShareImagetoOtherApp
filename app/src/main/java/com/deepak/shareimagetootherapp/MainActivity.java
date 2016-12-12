package com.deepak.shareimagetootherapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {
    private TextView tweetText;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tweetText = (TextView) findViewById(R.id.tweetText);
    }
    public void sendTweet(View v) {
        String msg = tweetText.getText().toString();
       /* Uri uri = Uri
                .parse("android.resource://com.deepak.shareimagetootherapp/mipmap/ic_launcher.png");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.setType("image/jpeg");
       // intent.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(intent, "Share Image Tutorial"));
        //startActivity(intent);*/
      //  Uri uri = Uri.parse("android.resource://com.deepak.shareimagetootherapp/mipmap/ic_launcher.png");

        OutputStream output;

        // Retrieve the image from the res folder
       bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        //BitmapDrawable drawable = (BitmapDrawable) mImageView1.getDrawable();
        // bitmap = drawable.getBitmap();

        // Find the SD Card path
        File filepath = Environment.getExternalStorageDirectory();

        // Create a new folder AndroidBegin in SD Card
        File dir = new File(filepath + "test.png");
      //  dir.mkdirs();

        // Create a name for the saved image


        try {

            output = new FileOutputStream(dir);

            // Compress into png format image from 0% - 100%
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
            output.flush();
            output.close();

            // Locate the image to Share
            Uri uri = Uri.fromFile(dir);
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");
            // Pass the image into an Intnet
            share.putExtra(Intent.EXTRA_STREAM, uri);
            // Show the social share chooser list
           startActivity(Intent.createChooser(share, "Share Image Tutorial"));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void next(View v ){
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent in1 = new Intent(this, ImageActivity.class);
        in1.putExtra("image",byteArray);
        startActivity(in1);
    }
}
