package ro.usv.ppaw.lab1.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "RENT_HISTORY")
public class RentHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Column(name = "DAYS", nullable = false)
    @NotNull(message = "Days is required")
    private Integer days;

    @ManyToOne
    @JoinColumn(name = "VEHICLE_ID", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    @NotNull(message = "Vehicle is required")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    @NotNull(message = "Client is required")
    private Client client;

    public Long getId() {
        return id;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "RentHistory{" +
                "id=" + id +
                ", days=" + days +
                ", vehicle=" + vehicle +
                ", client=" + client +
                '}';
    }
}
