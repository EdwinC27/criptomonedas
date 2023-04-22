package com.api.cripto.repository;

import com.api.cripto.entity.EntityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryInfo extends JpaRepository<EntityInfo, String> {
}
