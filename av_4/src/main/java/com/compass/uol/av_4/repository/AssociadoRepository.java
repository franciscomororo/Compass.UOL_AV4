package com.compass.uol.av_4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compass.uol.av_4.entity.Associado;
import com.compass.uol.av_4.entity.enums.CargoPolitico;

public interface AssociadoRepository extends JpaRepository<Associado, Integer>{

	List<Associado> findByCargoPolitico(CargoPolitico cargoPolitico);

}
