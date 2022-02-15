package com.util.message.model.dto;

import java.util.List;

public class DataJson {

    public Object data;

    public List<Object> lista;

    public String queue;

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public Object getData() {
        return data;
    }

    public List<Object> getLista() {
        return lista;
    }

    public void setLista(List<Object> lista) {
        this.lista = lista;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
