package grw.qut.inventory.repository;

import grw.qut.inventory.domain.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Manufacturer findByName(String name);
}
