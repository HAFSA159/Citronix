package com.citronix.repository;

import com.citronix.entity.Field;
import com.citronix.entity.Harvest;
import com.citronix.entity.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {

}
