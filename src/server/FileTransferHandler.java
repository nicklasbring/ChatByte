package server;

import globalsettings.GlobalSettings;
import request.Request;

import java.io.*;
import java.net.Socket;

public class FileTransferHandler implements Runnable {

    private Socket socket;
    private String filename;
    private byte[] fileBytes;
    private ServerListener listener;


    FileTransferHandler(Socket socket, Request request, ServerListener listener) {
        this.socket = socket;
        File file = request.getFile();
        this.listener = listener;



        filename = file.getName();

        listener.updateUI("Filesize: " + file.length() + " bytes / " + file.length() * 0.00000095367432 + " Mb");

        fileBytes = new byte[(int) file.length()];

    }

    @Override
    public void run() {

        recieveFile();

    }


    private void recieveFile(){

        listener.updateUI("FileHandler initialized");

        int num;


        try {

            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(GlobalSettings.SERVER_FILE_PATH + filename));

            listener.updateUI("Writing file to " + GlobalSettings.SERVER_FILE_PATH + filename);

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
