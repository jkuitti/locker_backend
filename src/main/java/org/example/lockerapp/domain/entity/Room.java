package org.example.lockerapp.domain.entity;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer gridRows;

    private Integer gridCols;

    public Room() {
    }

    public Room(Long id, String name, Gender gender, Integer gridRows, Integer gridCols) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.gridRows = gridRows;
        this.gridCols = gridCols;
    }

    public Integer getGridRows() {
        return gridRows;
    }

    public void setGridRows(Integer gridRows) {
        this.gridRows = gridRows;
    }

    public Integer getGridCols() {
        return gridCols;
    }

    public void setGridCols(Integer gridCols) {
        this.gridCols = gridCols;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", gridRows=" + gridRows +
                ", gridCols=" + gridCols +
                '}';
    }
}
