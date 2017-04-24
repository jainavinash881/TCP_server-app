package com.jain.avinash.server;

import android.os.Handler;
import android.os.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Hp on 22-Apr-17.
 */
public class MyServer {

    Thread m_ObjThread;
    ServerSocket m_Server;
    String strMessage;
    DataDisplay m_dataDisplay;
    Object m_connected;


    public MyServer() {

    }

  public void setEventListner(DataDisplay dataDisplay) {

      m_dataDisplay = dataDisplay;
  }

    public void startListening() {


        m_ObjThread = new Thread(new Runnable() {
            public void run()
            {

                try {

                    m_Server = new ServerSocket(2001);
                    Socket connectedSocket = m_Server.accept();
                    Message clientmessage=Message.obtain();
                    ObjectInputStream ois =new ObjectInputStream(connectedSocket.getInputStream());
                    String strMessage=(String)ois.readObject();
                    clientmessage.obj=strMessage;
                    mHandler.sendMessage(clientmessage);
                    ObjectOutputStream oos =new ObjectOutputStream(connectedSocket.getOutputStream());
                    oos.writeObject("Hii...");
                    ois.close();
                    oos.close();
                    m_Server.close();
                }
                catch (Exception e)
                {
                    Message msg3= Message.obtain();
                    msg3.obj=e.getMessage();
                    mHandler.sendMessage(msg3);
                }
            }
        });

        m_ObjThread.start();

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message status) {
            m_dataDisplay.Display(status.obj.toString());
        }
    };

}
