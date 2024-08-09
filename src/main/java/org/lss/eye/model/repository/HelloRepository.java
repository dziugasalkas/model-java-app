package org.lss.eye.model.repository;

import org.lss.eye.model.models.Hello;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository extends JpaRepository<Hello, Long> {}
