package com.example.test_uijfx;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.scene.image.Image;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;

public class ImageCaching {

    private static final String CACHE_DIRECTORY = "./com/example/test_uijfx/Cache/image_cache";
    private static final Cache<String, Image> imageCache = Caffeine.newBuilder().maximumSize(100).build();
    private static final File cacheDirectory = new File(CACHE_DIRECTORY);


    public static Image getImage(String url) throws IOException {
        String fileName = url.substring(url.lastIndexOf('/') +1);
        String localFilePath = CACHE_DIRECTORY + File.separator + fileName;

        if(!cacheDirectory.exists()) {
            boolean isDirCreated = cacheDirectory.mkdirs();
            if (!isDirCreated) {
                System.err.println("Failed to create cache directory: " + CACHE_DIRECTORY);
            }
        }
        Image image = imageCache.getIfPresent(localFilePath);
        if (image == null) {
            File cachedImageFile = new File(localFilePath);
            if (cachedImageFile.exists()) {
                byte[] decompressedData = imageDecompressor(Files.readAllBytes(cachedImageFile.toPath()),1.0F);
                image = new Image(new ByteArrayInputStream(decompressedData));
                imageCache.put(localFilePath, image);
            } else {
                try (InputStream inputStream = new URL(url).openStream();
                     FileOutputStream outputStream = new FileOutputStream(cachedImageFile)) {


                    byte[] buffer = new byte[4800];
                    int bytesRead;
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer,0, bytesRead);
                        System.out.println("Buffer: " + Arrays.toString(buffer));
                        //System.out.println("bytesRead: " + bytesRead);
                    }

                    //byte[] compressedData = imageCompresser(byteArrayOutputStream.toByteArray(), 0.5F);
                    outputStream.write(byteArrayOutputStream.toByteArray());

                    image = new Image(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                    imageCache.put(localFilePath, image);
                }

            }
        }
        System.out.println("Size of Cache: " + folderSize() + " kB");
        return image;
    }

    public static long folderSize () {
        long length = 0;
        for (File file : cacheDirectory.listFiles()){
            if (file.isFile()){
                length += file.length();
            } else {
                length += folderSize();
            }
        }
        return length / 1000;
    }

    public static void dumpCache(){
        for (File file : cacheDirectory.listFiles()){
            if (!file.isDirectory()){
                boolean isDeleted = file.delete();
                if (!isDeleted){
                    System.out.println("Could Not Delete File: " + file);
                }
            }
        }
    }

    private static byte[] imageCompresser(byte[] imageData, float quality) throws IOException {

        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));
        ByteArrayOutputStream compressedStream = new ByteArrayOutputStream();

        ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpeg").next();
        ImageWriteParam writeParam = imageWriter.getDefaultWriteParam();
        writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        writeParam.setCompressionQuality(quality);

        imageWriter.setOutput(ImageIO.createImageOutputStream(compressedStream));
        imageWriter.write(null, new IIOImage(bufferedImage, null, null), writeParam);

        imageWriter.dispose();

        //int sizeAfterComp = compressedStream.toByteArray().length;
        //System.out.println(sizeAfterComp/1000 + " kB");

        return compressedStream.toByteArray();
    }

    private static byte[] imageDecompressor(byte[] compressedImageData, float quality) throws IOException{

        BufferedImage compressedImage = ImageIO.read(new ByteArrayInputStream(compressedImageData));

        ByteArrayOutputStream decompressedStream = new ByteArrayOutputStream();

        ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpeg").next();
        ImageWriteParam writeParam = imageWriter.getDefaultWriteParam();
        writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        writeParam.setCompressionQuality(quality);

        imageWriter.setOutput(ImageIO.createImageOutputStream(decompressedStream));
        imageWriter.write(null, new IIOImage(compressedImage, null, null), writeParam);

        imageWriter.dispose();

        //int imageSizeAfterDecom = decompressedStream.toByteArray().length;
        //System.out.println(imageSizeAfterDecom/1000 + " kB");

        return decompressedStream.toByteArray();
    }

}
