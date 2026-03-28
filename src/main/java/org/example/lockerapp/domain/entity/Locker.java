package org.example.lockerapp.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "lockers")
public class Locker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "locker_number", nullable = false)
    private Integer lockerNumber;

    @Column(name = "key_number", nullable = false)
    private Integer keyNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LockerStatus status;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "grid_x")
    private Integer gridX;

    @Column(name = "grid_y")
    private Integer gridY;

    public Locker() {
    }

    public Locker(Long id, Integer lockerNumber, Integer keyNumber, LockerStatus status, Room room, Integer gridX, Integer gridY) {
        this.id = id;
        this.lockerNumber = lockerNumber;
        this.keyNumber = keyNumber;
        this.status = status;
        this.room = room;
        this.gridX = gridX;
        this.gridY = gridY;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLockerNumber() {
        return lockerNumber;
    }

    public void setLockerNumber(Integer lockerNumber) {
        this.lockerNumber = lockerNumber;
    }

    public Integer getKeyNumber() {
        return keyNumber;
    }

    public void setKeyNumber(Integer keyNumber) {
        this.keyNumber = keyNumber;
    }

    public LockerStatus getStatus() {
        return status;
    }

    public void setStatus(LockerStatus status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getGridX() {
        return gridX;
    }

    public void setGridX(Integer gridX) {
        this.gridX = gridX;
    }

    public Integer getGridY() {
        return gridY;
    }

    public void setGridY(Integer gridY) {
        this.gridY = gridY;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Locker locker = (Locker) o;
        return Objects.equals(id, locker.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Locker{" +
                "id=" + id +
                ", lockerNumber=" + lockerNumber +
                ", keyNumber=" + keyNumber +
                ", status=" + status +
                ", room=" + room +
                ", gridX=" + gridX +
                ", gridY=" + gridY +
                '}';
    }
}
