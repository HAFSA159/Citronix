package com.citronix.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fields")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Area is required")
    @Column(nullable = false)
    private Double area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tree> trees;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Harvest> harvests;
}