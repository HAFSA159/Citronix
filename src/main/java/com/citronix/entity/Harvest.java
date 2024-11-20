package com.citronix.entity;

import com.citronix.entity.enums.Season;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

    @Entity
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "harvests")
    public class Harvest {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull(message = "Harvest date is required")
        @Column(name = "harvest_date", nullable = false)
        private LocalDate harvestDate;

        @NotNull(message = "Season is required")
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Season season;

        @NotNull(message = "Total quantity is required")
        @Column(name = "total_quantity", nullable = false)
        private Double totalQuantity;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "field_id", nullable = false)
        private Field field;

        @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<HarvestDetail> harvestDetails;

        @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Sale> sales;
    }

