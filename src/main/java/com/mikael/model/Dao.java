package com.mikael.model;

import lombok.SneakyThrows;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import java.util.List;

public class Dao {
    /*
     * Qualquer porta entre 0 e 65535.
     * Seu IPV4.
     */
    private String port = "5000";
    private String server = "192.168.15.7";

    JSONObject json = new JSONObject();

    /*
     * Método que tenta conexão com o Server Socket atrávez do server e porta
     * e chama o método que irá realizar ações no servidor.
     */
    @SneakyThrows
    public void connection(JavaBeans javaBeans){
            // Tentado conexão ao servidor
            Socket socket = new Socket(server, Integer.parseInt(port));
            System.out.println("Conectado ao Server Socket!");
            actions(socket, javaBeans);
            System.out.println("Fechando o socket.");
            socket.close();
    }
    /*
     * Método que transforma as váriasveis da class JavaBeans em json,
     * chama os método enviar mensagem, receber mensagem e receber usuarios.
     * Essas mensagens são enviadas e recebidas do server socket
     */
    public void actions(Socket socket, JavaBeans javaBeans){
        json.put("user",javaBeans.getUser());
        json.put("email",javaBeans.getEmail());
        json.put("password",javaBeans.getPassword());
        sendMessage(socket);
        getMessage(socket);
    }
    /*
     *  Método que envia mensagem para o server socket
     *
     * obtem o fluxo de saída do soquete.
     * cria um fluxo de saída de objeto a partir do fluxo de saída para que possamos enviar um objeto através dele.
     * criando um array.
     * transformando o json em string e adicionando no array.
     * Envia ao Socket a mensagem
     */
   @SneakyThrows
    public void sendMessage(Socket socket) {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            List<String> messages = new ArrayList<>();
            messages.add(new String(json.toJSONString()));

            System.out.println("Enviando mensagem ao ServerSocket: ");
            System.out.println(json);
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.print("Resultado: ");
            objectOutputStream.writeObject(messages);

        }
        /*
         * Método que recebe mensagem do servidor e printa no terminal
         */
        @SneakyThrows
        public  void getMessage(Socket socket){
            DataInputStream dus = new DataInputStream(socket.getInputStream());
            String mgs = dus.readUTF();
            System.out.println(mgs);

        }
        /*
         * Método que recebe uma lista com todos os usuários cadastrados no sistema
         * (Em construção)
         */
    /*
    @SneakyThrows
        public void receberUsuario(Socket socket){
                System.out.println("passou aqui");
                for (var i = 0; i <= 20; i++){
                    DataInputStream dos = new DataInputStream(socket.getInputStream());
                     String usr = dos.readUTF();
                    System.out.println(usr);
                }
        }
        */
    }

