package grw.qut.inventory.model;

import io.swagger.annotations.ApiModelProperty;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Manufacturer
 */
@Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2020-06-26T20:48:51.890+10:00")
public class Manufacturer {
    @NotNull
    private String name;

    @NotNull
    private String homePage;

    @NotNull
    private String phone;

    public Manufacturer name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @ApiModelProperty(example = "ACME Corporation", required = true, value = "")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer homePage(String homePage) {
        this.homePage = homePage;
        return this;
    }

    /**
     * Get homePage
     *
     * @return homePage
     **/
    @ApiModelProperty(example = "https://www.acme-corp.com", value = "")
    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public Manufacturer phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Get phone
     *
     * @return phone
     **/
    @ApiModelProperty(example = "(07) 5556 4321", value = "")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Manufacturer manufacturer = (Manufacturer) o;
        return Objects.equals(this.name, manufacturer.name) &&
            Objects.equals(this.homePage, manufacturer.homePage) &&
            Objects.equals(this.phone, manufacturer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, homePage, phone);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Manufacturer {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    homePage: ").append(toIndentedString(homePage)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
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

