package com.example.gorenganindonesia.Util;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BitmapHelper {
    int MAX_SIZE_IMAGE = 1024; // default

    public BitmapHelper() {
    }

    public Bitmap compressImage(Uri imageUri, ContentResolver resolver) {
        try {
            // Decode the image file to a Bitmap
            InputStream input = resolver.openInputStream(imageUri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1; // Set the initial inSampleSize value
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, options);

            // Calculate the initial size in kilobytes
            int initialSizeKB = bitmap.getByteCount() / 1024;
            input.close();

            // Loop until the size is below the defined threshold
            while (initialSizeKB > MAX_SIZE_IMAGE) {
                // Increase the inSampleSize to reduce the image dimensions
                options.inSampleSize *= 2;

                InputStream inputTemp = resolver.openInputStream(imageUri);
                Bitmap bitmapTemp = BitmapFactory.decodeStream(inputTemp, null, options);

                // Recalculate the size
                initialSizeKB = bitmapTemp.getByteCount() / 1024;
                inputTemp.close();
            }


            // Now 'bitmap' contains the compressed image
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MultipartBody.Part bitmapToMultipartBody(Bitmap bitmap, Uri imageUri) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        String imageType = getImageTypeFromUri(imageUri);
        bitmap.compress(getBitmapCompressFormat(imageType), 100, stream);
        byte[] byteArray = stream.toByteArray();

        // Create a RequestBody from the byte array
        RequestBody requestBody = RequestBody.create(getMediaType(imageType), byteArray);

        // Create a MultipartBody.Part from the RequestBody
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "image." + imageType, requestBody);

        return filePart;
    }


    private Bitmap.CompressFormat getBitmapCompressFormat(String imageType) {
        switch (imageType.toLowerCase()) {
            case "png":
                return Bitmap.CompressFormat.PNG;
            case "webp":
                return Bitmap.CompressFormat.WEBP;
            default:
                return Bitmap.CompressFormat.JPEG;
        }
    }

    private MediaType getMediaType(String imageType) {
        switch (imageType.toLowerCase()) {
            case "png":
                return MediaType.parse("image/png");
            case "webp":
                return MediaType.parse("image/webp");
            default:
                return MediaType.parse("image/jpeg");
        }
    }

    public String getImageTypeFromUri(Uri imageUri) {
         MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Get the file extension from the Uri
        String fileExtension = MimeTypeMap.getFileExtensionFromUrl(imageUri.toString());

        // Map the file extension to a standard image type
        String mimeType = mimeTypeMap.getMimeTypeFromExtension(fileExtension.toLowerCase());

        // Extract the image type from the mime type
        String imageType = mimeType != null ? mimeType.split("/")[1] : "jpeg";

        return imageType;
    }

}