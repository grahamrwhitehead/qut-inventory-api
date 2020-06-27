package grw.qut.inventory.api;

import grw.qut.inventory.model.Manufacturer;
import grw.qut.inventory.repository.InventoryItemRepository;
import grw.qut.inventory.repository.ManufacturerRepository;
import io.swagger.annotations.ApiParam;
import grw.qut.inventory.model.InventoryItem;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Generated;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2020-06-26T20:48:51.890+10:00")
@RestController
@Slf4j
@Validated
@RequestMapping("/api")
public class InventoryApiController implements InventoryApi {

    private final InventoryItemRepository inventoryItemRepository;
    private final ManufacturerRepository manufacturerRepository;

    public InventoryApiController(final InventoryItemRepository inventoryItemRepository, final ManufacturerRepository manufacturerRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public ResponseEntity<List<InventoryItem>> inventoryGet(
        @ApiParam(value = "number of records to skip for pagination") @RequestParam(value = "skip", required = false) Integer skip,
        @ApiParam(value = "maximum number of records to return") @RequestParam(value = "limit", required = false) Integer limit) {

        val size = Optional.ofNullable(limit).orElse(3);
        val page = Optional.ofNullable(skip).map(recordsToSkip -> Math.floorDiv(recordsToSkip, size)).orElse(0);

        log.debug("inventoryGet page: {}, size: {}", page, size);

        val result = inventoryItemRepository.findAll(PageRequest.of(page, size)).stream().map(inventoryItem -> convert(inventoryItem)).collect(toList());

        return result.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<InventoryItem> inventoryIdGet(@ApiParam(value = "", required = true) @PathVariable("id") String id) {
        log.debug("inventoryIdGet id: {}", id);
        return Optional.ofNullable(convert(inventoryItemRepository.findByInvItemNo(id)))
            .map(inventoryItem -> new ResponseEntity<InventoryItem>(inventoryItem, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<InventoryItem>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Void> inventoryPost(@ApiParam(value = "Inventory item to add") @Valid @RequestBody InventoryItem inventoryItem) {
        log.debug("inventoryPost inventoryItem: {}", inventoryItem);

        if (inventoryItemRepository.findByInvItemNo(inventoryItem.getId()) == null) {
            inventoryItemRepository.save(convert(inventoryItem));
        } else {
            throw new InventoryItemIdExistsException();
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private grw.qut.inventory.domain.InventoryItem convert(final InventoryItem inventoryItem) {
        val result = new grw.qut.inventory.domain.InventoryItem();
        result.setInvItemNo(inventoryItem.getId());
        result.setName(inventoryItem.getName());
        result.setReleaseDate(inventoryItem.getReleaseDate());
        result.setManufacturer(Optional.ofNullable(manufacturerRepository.findByName(inventoryItem.getManufacturer().getName()))
            .orElseGet(() -> {
                val manufacturer = new grw.qut.inventory.domain.Manufacturer();
                manufacturer.setName(inventoryItem.getManufacturer().getName());
                manufacturer.setHomePage(inventoryItem.getManufacturer().getHomePage());
                manufacturer.setPhone(inventoryItem.getManufacturer().getPhone());

                return manufacturer;
            }));

        return result;
    }

    private InventoryItem convert(final grw.qut.inventory.domain.InventoryItem inventoryItem) {
        return Optional.ofNullable(inventoryItem)
            .map(invItem -> new InventoryItem()
                            .id(invItem.getInvItemNo())
                            .name(invItem.getName())
                            .releaseDate(invItem.getReleaseDate())
                            .manufacturer(convert(invItem.getManufacturer())))
            .orElse(null);
    }

    private Manufacturer convert(final grw.qut.inventory.domain.Manufacturer manufacturer) {
        return Optional.ofNullable(manufacturer)
            .map(man -> new Manufacturer()
                            .name(man.getName())
                            .homePage(man.getHomePage())
                            .phone(man.getPhone()))
            .orElse(null);
    }

    @ExceptionHandler(InventoryItemIdExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ResponseEntity<String> handleInventoryItemIdExistsException(final InventoryItemIdExistsException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.CONFLICT);
    }
}
