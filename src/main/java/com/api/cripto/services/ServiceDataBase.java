package com.api.cripto.services;

import com.api.cripto.entity.EntityInfo;
import com.api.cripto.entity.EntityUser;
import com.api.cripto.repository.RepositoryInfo;
import com.api.cripto.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class ServiceDataBase {
    @Autowired
    RepositoryInfo repositoryInfo;
    @Autowired
    private RepositoryUser repositorioUser;

    public void guardarMiEntidad(String Endpoint, String Status) {
        EntityInfo entityInfo = new EntityInfo();
        entityInfo.setEndpoint(Endpoint);
        entityInfo.setHora(String.valueOf(LocalTime.now()));
        entityInfo.setStatus(Status);

        repositoryInfo.save(entityInfo);
    }

    public void guardarMitoken(String userName, String token) {
        EntityUser userGuardar = repositorioUser.findByuserName(userName);
        userGuardar.setToken(token);
        repositorioUser.save(userGuardar);
    }
}
