package com.webkit.sonsation_server.repository;

import com.webkit.sonsation_server.model.Sign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SignRepository extends JpaRepository<Sign, Long> {

}
