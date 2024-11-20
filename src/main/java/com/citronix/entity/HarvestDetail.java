package com.citronix.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "harvest_details")
public class HarvestDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Quantity is required")
    @Column(nullable = false)
    private Double quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tree_id", nullable = false)
    private Tree tree;
}

