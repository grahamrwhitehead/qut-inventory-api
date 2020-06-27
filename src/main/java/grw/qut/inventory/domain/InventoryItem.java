package grw.qut.inventory.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "inventory_item")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "inv_item_no", unique=true, nullable = false)
    private String invItemNo;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", nullable = false)
    private Manufacturer manufacturer;
}

