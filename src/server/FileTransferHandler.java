package server;

import request.Request;

import java.io.*;
import java.net.Socket;

public class FileTransferHandler implements Runnable {

    private final String path = "C:\\Users\\Cosby\\Desktop\\serverFiles\\";

    private Socket socket;
    private BufferedOutputStream bos;
    private BufferedInputStream bis;
    private File file;
    private String filename;
    private byte[] fileBytes;


    public FileTransferHandler(Socket socket, Request request) {
        this.socket = socket;
        this.file = request.getFile();


        filename = file.getName();
        System.out.println("Filesize: " + file.length());
        fileBytes = new byte[(int) file.length()];

    }

    @Override
    public void run() {

        System.out.println("FileHandler initialized run");

        int num;


        try {
            System.out.println("creating input and output");
            bis = new BufferedInputStream(socket.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(path + filename));

            System.out.println("Writing the contents in the file from server");
            while ( (num = bis.read(fileBytes)) > 0) {
                bos.write(fileBytes,0,num);

            }
            bos.close();
            bis.close();
            System.out.println("done");
        } catch (IOException e){
            System.out.println("File writing error!");
            e.printStackTrace();
        }

    }






}
