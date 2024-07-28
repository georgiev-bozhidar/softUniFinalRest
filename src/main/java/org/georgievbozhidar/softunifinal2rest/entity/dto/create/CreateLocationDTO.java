package org.georgievbozhidar.softunifinal2rest.entity.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.georgievbozhidar.softunifinal2rest.entity.dto.ChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.enums.LocationType;

public class CreateLocationDTO {
    @NotBlank(message = "Address must not be blank.")
    private String address;

    @NotNull(message = "Parent chain must not be null.")
    @JsonProperty("ownedBy")
    private ChainDTO ownedBy;

    @NotNull(message = "Location type must not be blank.")
    private LocationType locationType;

    public @NotBlank String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank String address) {
        this.address = address;
    }

    public @NotNull ChainDTO getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(@NotNull ChainDTO ownedBy) {
        this.ownedBy = ownedBy;
    }

    public @NotNull LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(@NotNull LocationType locationType) {
        this.locationType = locationType;
    }
}
