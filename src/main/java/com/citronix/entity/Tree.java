package com.citronix.entity;

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
@Table(name = "trees")
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Planting date is required")
    @Column(name = "planting_date", nullable = false)
    private LocalDate plantingDate;

    @Column(name = "is_productive")
    private boolean isProductive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HarvestDetail> harvestDetails;
}