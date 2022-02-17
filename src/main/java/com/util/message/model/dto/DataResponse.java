package com.util.message.model.dto;

public class DataResponse {

    public Object data;

    public Object destinatario;

    public boolean ultimo;

    public boolean isUltimo() {
        return ultimo;
    }

    public void setUltimo(boolean ultimo) {
        this.ultimo = ultimo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Object destinatario) {
        this.destinatario = destinatario;
    }
}
