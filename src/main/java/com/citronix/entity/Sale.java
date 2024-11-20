package com.citronix.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Sale date is required")
    @Column(name = "sale_date", nullable = false)
    private LocalDate saleDate;

    @NotNull(message = "Unit price is required")
    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @NotNull(message = "Quantity is required")
    @Column(nullable = false)
    private Double quantity;

    @NotBlank(message = "Client name is required")
    @Column(name = "client_name", nullable = false)
    private String clientName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;

    @Column(name = "total_revenue")
    private Double totalRevenue;

    @PrePersist
    @PreUpdate
    protected void calculateRevenue() {
        this.totalRevenue = this.quantity * this.unitPrice;
    }
}