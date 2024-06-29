package com.movie.booking.system.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.movie.booking.system.util.SeatStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;


    @Column(nullable = false)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status=SeatStatus.AVAILABLE;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id",referencedColumnName = "showId")
    private Show show;

    @Column(nullable = true)
    private boolean activeFlag=true;

    @CreationTimestamp
    private Date crtDt;
    @UpdateTimestamp
    private Date updatedDt;
}
