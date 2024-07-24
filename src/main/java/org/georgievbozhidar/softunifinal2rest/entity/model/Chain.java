package org.georgievbozhidar.softunifinal2rest.entity.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "chains")
public class Chain extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "ownedBy", fetch = FetchType.EAGER)
    private Set<Location> locations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location) {
        this.getLocations().add(location);
    }

    public void removeLocation(Location location) {
        this.getLocations().remove(location);
    }
}
