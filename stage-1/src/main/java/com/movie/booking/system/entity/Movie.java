package com.movie.booking.system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Builder
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private Date releaseDate;


    @JsonBackReference
    @OneToMany(mappedBy = "movie" ,cascade = CascadeType.MERGE, fetch = FetchType.LAZY )
    private Set<Show> showSet;

    @Column(nullable = true)
    private boolean activeFlag=true;

    @CreationTimestamp
    private Date crtDt;
    @UpdateTimestamp
    private Date updatedDt;

}
