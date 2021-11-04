package com.util.message.services.rabbit;

import com.google.gson.Gson;

import com.util.message.model.dto.DataJson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.apache.commons.lang.StringEscapeUtils;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

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

            for (Object d : data.data ){
                sender.send(gson.toJson(d));
            }
            Date d2 = new Date();

            long diff = d2.getTime() - d1.getTime();
            long diffSeconds = diff / 1000;


            System.out.println("cantidad de registros ::: " + data.getData().size() + "Tiempo ::: " + diffSeconds +"  Segundos");
        }
    }


