package client;

import globalsettings.GlobalSettings;
import request.Request;

import java.io.*;
import java.net.Socket;

public class FileTransferClient implements Runnable {

    //Class variables
    private ClientListener listener;


    private byte[] fileBytes;

    private File file;

    FileTransferClient(Request request, ClientListener listener) {
        this.file = request.getFile();
        this.listener = listener;

        listener.updateUi("File path: " + file.getPath() + "\n" +
                "Transfer size: " + file.length() + " bytes / " + file.length() * 0.00000095367432 + " Mb" );
        fileBytes = new byte[(int)file.length()];

    }

    @Override
    public void run() {

        int num;

        try {
            Socket socket = new Socket(GlobalSettings.HOST_ADDRESS, GlobalSettings.HOST_PORT);

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file.getPath()));
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

            while ( (num = bis.read(fileBytes)) > 0 ){
                bos.write(fileBytes,0,num);
            }

            bos.close();
            bis.close();

            listener.updateUi("File transfer complete");

        } catch (IOException e) {
            listener.updateUi("Filetransfer had an IO error");
            e.printStackTrace();
        }

    }

}
