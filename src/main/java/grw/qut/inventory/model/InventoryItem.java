package grw.qut.inventory.model;

import io.swagger.annotations.ApiModelProperty;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * InventoryItem
 */
@Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2020-06-26T20:48:51.890+10:00")
public class InventoryItem {
    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private LocalDate releaseDate;

    @NotNull
    private Manufacturer manufacturer;

    public InventoryItem id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", required = true, value = "")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InventoryItem name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @ApiModelProperty(example = "Widget Adapter", required = true, value = "")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InventoryItem releaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    /**
     * Get releaseDate
     *
     * @return releaseDate
     **/
    @ApiModelProperty(example = "2016-08-29T09:12:33.001Z", required = true, value = "")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public InventoryItem manufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    /**
     * Get manufacturer
     *
     * @return manufacturer
     **/
    @ApiModelProperty(required = true, value = "")
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InventoryItem inventoryItem = (InventoryItem) o;
        return Objects.equals(this.id, inventoryItem.id) &&
            Objects.equals(this.name, inventoryItem.name) &&
            Objects.equals(this.releaseDate, inventoryItem.releaseDate) &&
            Objects.equals(this.manufacturer, inventoryItem.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, manufacturer);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InventoryItem {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    releaseDate: ").append(toIndentedString(releaseDate)).append("\n");
        sb.append("    manufacturer: ").append(toIndentedString(manufacturer)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

