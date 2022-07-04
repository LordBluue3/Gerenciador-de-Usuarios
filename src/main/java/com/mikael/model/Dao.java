package com.mikael.model;

import org.json.simple.JSONObject;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dao {

    private String porta = "5000"; //Qualquer porta entre 0 e 65535
    private String servidor = "192.168.15.7"; //O seu IPV4


    public void conexao(JavaBeans jao){
        JSONObject json = new JSONObject();

        json.put("usuario",jao.getUsuario());
        json.put("email",jao.getEmail());
        json.put("senha",jao.getSenha());

        try{
            // Tentado conexão ao servidor
            Socket socket = new Socket(servidor, Integer.parseInt(porta));
            System.out.println("Conectado ao Server Socket!");

            // obter o fluxo de saída do soquete.
            OutputStream outputStream = socket.getOutputStream();
            // cria um fluxo de saída de objeto a partir do fluxo de saída para que possamos enviar um objeto através dele
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            //fazer um monte de mensagens para enviar.
            List<String> messages = new ArrayList<>();
            //enviando mensagem em Json ao servidor
            messages.add(new String(json.toJSONString()));
            //Enviando mensagem ao socket
            System.out.println("Enviando mensagem ao ServerSocket: ");
            System.out.println(json);
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.print("Resultado: ");
            objectOutputStream.writeObject(messages);

            //fechando o socket
            System.out.println("Fechando o socket.");
            socket.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
