package com.util.message.services.rabbit;

import com.google.gson.Gson;

import com.util.message.model.dto.DataJson;
import com.util.message.model.dto.DataResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.apache.commons.lang.StringEscapeUtils;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
    public class MessageReceiver {

        @Autowired
        private Gson gson;


        @Autowired
        private MessageSender sender;


        @RabbitListener(queues = "${queue.list}")
        public void receive(String in) {
            Date d1 = new Date();

            DataJson data = gson.fromJson(in, DataJson.class);

            for (Object d : data.lista ){
                sender.send(gson.toJson(d), data.getQueue());
            }
            Date d2 = new Date();

            long diff = d2.getTime() - d1.getTime();
            long diffSeconds = diff / 1000;


            System.out.println("cantidad de registros ::: " + data.getLista().size() + "Tiempo ::: " + diffSeconds +"  Segundos");
        }


    @RabbitListener(queues = "${queue.data}")
    public void receiveData(String in) {
        Date d1 = new Date();

        DataJson data = gson.fromJson(in, DataJson.class);

        int agrupador = data.agrupador>0 ? data.getAgrupador() : 1;

        List<Object> listaAgrupada = new ArrayList<>();
        int cont =0;

        for (int i = 0 ; i < data.getLista().size(); i++){


            Object d = data.getLista().get(i);
            DataResponse response = new DataResponse();
            response.data = data.data;
            response.destinatario = d;
            response.setUltimo(data.getLista().size() -1 == i ? true : false);

            listaAgrupada.add(response);
            cont++;

            if (agrupador == cont || data.getLista().size() -1 == i){
                sender.send(gson.toJson(listaAgrupada), data.queue);
                listaAgrupada.clear();
                cont=0;
            }


        }
        Date d2 = new Date();

        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000;


        System.out.println("cantidad de registros ::: " + data.getLista().size() + "Tiempo ::: " + diffSeconds +"  Segundos");
    }
    }


