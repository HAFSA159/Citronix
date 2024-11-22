package com.citronix.repository;

import com.citronix.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    @Query("SELECT COALESCE(SUM(f.area), 0) FROM Field f WHERE f.farm.id = :farmId")
    double sumFieldAreaByFarmId(@Param("farmId") Long farmId);
    long countByFarmId(Long farmId);

}
