package com.movie.booking.system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name ="movie_id",referencedColumnName = "movieId")
    private Movie movie;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name ="theatre_id",referencedColumnName = "theatreId")
    private Theatre theatre;

    @Column(nullable = false)
    private int screenNumber;

    @Column(nullable = false)
    private Date showTime;

    @JsonBackReference
    @OneToMany(mappedBy = "show",cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Seat> seatSet;

    @Column(nullable = true)
    private boolean activeFlag=true;

    @CreationTimestamp
    private Date crtDt;
    @UpdateTimestamp
    private Date updatedDt;

}
