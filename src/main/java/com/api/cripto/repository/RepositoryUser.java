package com.api.cripto.repository;

import com.api.cripto.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends JpaRepository<EntityUser,String> {
     EntityUser findByuserName(String nombreUsuario);

}
