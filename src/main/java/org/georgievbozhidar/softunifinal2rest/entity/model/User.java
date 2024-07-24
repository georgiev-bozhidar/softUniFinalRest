package org.georgievbozhidar.softunifinal2rest.entity.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate birthday;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private Set<Chain> ownedChains;

    @ManyToMany
    private Set<Chain> favouriteChains;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Set<Chain> getOwnedChains() {
        return ownedChains;
    }

    public void setOwnedChains(Set<Chain> ownedChains) {
        this.ownedChains = ownedChains;
    }

    public Set<Chain> getFavouriteChains() {
        return favouriteChains;
    }

    public void setFavouriteChains(Set<Chain> favouriteChains) {
        this.favouriteChains = favouriteChains;
    }
}
