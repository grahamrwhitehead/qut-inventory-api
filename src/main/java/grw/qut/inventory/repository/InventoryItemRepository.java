package grw.qut.inventory.repository;

import grw.qut.inventory.domain.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    InventoryItem findByInvItemNo(String invItemNo);
}
