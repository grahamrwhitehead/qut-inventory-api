package grw.qut.inventory.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import grw.qut.inventory.model.InventoryItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.List;

@Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2020-06-26T20:48:51.890+10:00")
@Api(value = "inventory", description = "the inventory API")
public interface InventoryApi {

    @ApiOperation(value = "", notes = "List available inventory in the system.", response = InventoryItem.class, responseContainer = "List", tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "list of inventory", response = InventoryItem.class),
        @ApiResponse(code = 400, message = "bad input parameter", response = InventoryItem.class)})
    @GetMapping(value = "/inventory", produces = {"application/json"})
    ResponseEntity<List<InventoryItem>> inventoryGet(
        @ApiParam(value = "number of records to skip for pagination") @RequestParam(value = "skip", required = false) Integer skip,
        @ApiParam(value = "maximum number of records to return") @RequestParam(value = "limit", required = false) Integer limit
    );

    @ApiOperation(value = "", notes = "Returns an inventory item by id.", response = InventoryItem.class, tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "an inventory item", response = InventoryItem.class),
        @ApiResponse(code = 404, message = "not found", response = InventoryItem.class)})
    @GetMapping(value = "/inventory/{id}", produces = {"application/json"})
    ResponseEntity<InventoryItem> inventoryIdGet(@ApiParam(value = "", required = true) @PathVariable("id") String id);

    @ApiOperation(value = "", notes = "Adds an item to the system", response = Void.class, tags = {})
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "item created", response = Void.class),
        @ApiResponse(code = 400, message = "invalid input, object invalid", response = Void.class),
        @ApiResponse(code = 409, message = "an existing item already exists", response = Void.class)})
    @PostMapping(value = "/inventory", produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<Void> inventoryPost(@ApiParam(value = "Inventory item to add") @Valid @RequestBody InventoryItem inventoryItem);
}
