package com.movie.booking.system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Builder
@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theatreId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer totalScreens;

    @JsonBackReference
    @OneToMany(mappedBy ="theatre",cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Show> showSet;

    @Column(nullable = true)
    private boolean activeFlag=true;

    @CreationTimestamp
    private Date crtDt;
    @UpdateTimestamp
    private Date updatedDt;
}
