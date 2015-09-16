package org.gin.extractor.repository;

import java.util.*;
import org.gin.extractor.model.Contrast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContrastRepository extends JpaRepository<Contrast, Integer> {
	
	@Query("SELECT DISTINCT atlas FROM Contrast")
    public List<String> findAllAtlas();
	
	@Query("SELECT contrast FROM Contrast WHERE contype = ?1 AND atlas = ?2")
	public List<String> findConByContypeAndAtlas(String contype, String atlas);
	
	public List<Contrast> findByAtlas(String atlas);
	
	public List<Contrast> findByContype(String contype);
}
