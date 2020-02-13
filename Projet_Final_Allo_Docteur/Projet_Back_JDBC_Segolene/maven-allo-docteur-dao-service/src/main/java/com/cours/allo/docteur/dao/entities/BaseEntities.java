package com.cours.allo.docteur.dao.entities;

public class BaseEntities {

    protected String numeroTelephone;
    protected Integer version = 0;
    
    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
