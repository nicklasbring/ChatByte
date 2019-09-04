package client;

import request.Request;

import java.io.*;
import java.net.Socket;

public class FileTransferClient implements Runnable {

    //Constants
    private static final int PORT = 16500;

    //Class variables
    private String hostIP = "localhost";

    private Socket socket;
    private BufferedInputStream bis;
    private BufferedOutputStream bos;
    private Request request;

    private byte[] fileBytes;

    private File file;

    public FileTransferClient(Request request) {
        this.file = request.getFile();
        this.request = request;

        System.out.println("File path: " + file.getPath() + "\n" +
                "Transfer size: " + file.length() );
        fileBytes = new byte[(int)file.length()];

    }

    @Override
    public void run() {

        int num;

        try {
            socket = new Socket(hostIP, PORT);
            System.out.println("File socket connected");


            bis = new BufferedInputStream(new FileInputStream(file.getPath()));
            System.out.println("BufferedInputStream initialized");

            bos = new BufferedOutputStream(socket.getOutputStream());
            System.out.println("BufferedOutputStream initialized");


            while ( (num = bis.read(fileBytes)) > 0 ){
                bos.write(fileBytes,0,num);
            }

            bos.close();
            bis.close();

            System.out.println("File transfer complete");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
