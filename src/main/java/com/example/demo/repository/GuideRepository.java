package com.example.demo.repository;

import com.example.demo.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideRepository extends JpaRepository<Guide,Integer> {
}
