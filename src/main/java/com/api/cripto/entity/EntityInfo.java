package com.api.cripto.entity;


import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "informacion")
public class EntityInfo {
    @Id
    @Basic
    private String hora;
    @Basic
    private String endpoint;
    @Basic
    private String status;

    public EntityInfo() {

    }

    public EntityInfo(String endpoint, String hora, String status) {
        this.endpoint = endpoint;
        this.hora = hora;
        this.status = status;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
