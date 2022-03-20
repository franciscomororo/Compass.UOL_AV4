package com.compass.uol.av_4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compass.uol.av_4.entity.Partido;
import com.compass.uol.av_4.entity.enums.Ideologia;

public interface PartidoRepository extends JpaRepository<Partido, Integer>{

	List<Partido> findByIdeologia(Ideologia ideologia);

}
