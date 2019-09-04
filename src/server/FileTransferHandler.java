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
    private ServerListener listener;


    public FileTransferHandler(Socket socket, Request request, ServerListener listener) {
        this.socket = socket;
        this.file = request.getFile();
        this.listener = listener;



        filename = file.getName();
        listener.updateUI("Filesize: " + file.length() + " bytes / " + file.length() * 0.00000095367432 + " Mb");
        fileBytes = new byte[(int) file.length()];

    }

    @Override
    public void run() {

        listener.updateUI("FileHandler initialized");

        int num;


        try {
            bis = new BufferedInputStream(socket.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(path + filename));

            listener.updateUI("Writing file to " + path + filename);
            while ( (num = bis.read(fileBytes)) > 0) {
                bos.write(fileBytes,0,num);

            }
            bos.close();
            bis.close();
            listener.updateUI("File transfer completed");
        } catch (IOException e){
            listener.updateUI("File writing error!");
            e.printStackTrace();
        }

    }






}
