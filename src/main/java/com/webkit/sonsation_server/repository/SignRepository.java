package com.webkit.sonsation_server.repository;

import com.webkit.sonsation_server.model.Sign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SignRepository extends JpaRepository<Sign, Long> {
    List<Sign> findByNameContaining(String keyword);

    @Query("SELECT s.name FROM Sign s")
    List<String> findAllNames();
}
