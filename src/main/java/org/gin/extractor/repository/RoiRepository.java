package org.gin.extractor.repository;

import java.util.*;

import org.gin.extractor.model.Roi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoiRepository extends JpaRepository<Roi, Integer> {
	
	@Query("SELECT roi FROM Roi WHERE laterality != 3 and atlas = ?1 ")
	public List<String> findRoiByAtlas(String atlas);
	
	@Query("SELECT roi FROM Roi WHERE laterality = 3 and atlas = ?1 ")
	public List<String> findRoiAsymByAtlas(String atlas);
	
	@Query("SELECT laterality FROM Roi WHERE atlas = ?1 ")
	public List<Integer> findLateralityByAtlas(String atlas);	
}
