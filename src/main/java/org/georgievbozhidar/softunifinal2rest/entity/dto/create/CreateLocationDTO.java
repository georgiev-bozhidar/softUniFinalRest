package org.georgievbozhidar.softunifinal2rest.entity.dto.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.georgievbozhidar.softunifinal2rest.entity.dto.ChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.enums.LocationType;
import org.georgievbozhidar.softunifinal2rest.validation.annotation.UniqueAddress;

public class CreateLocationDTO {
    @NotBlank(message = "Address must not be blank.")
    @UniqueAddress
    private String address;

    @Null(message = "Parent chain must be null when creating location at chain endpoint.")
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

    public ChainDTO getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(ChainDTO ownedBy) {
        this.ownedBy = ownedBy;
    }

    public @NotNull LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(@NotNull LocationType locationType) {
        this.locationType = locationType;
    }
}
