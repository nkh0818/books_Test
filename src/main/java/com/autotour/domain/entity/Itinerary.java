package com.autotour.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String memo;
    private String region;
    private String theme;
    private LocalDate travelDate;
    private String weatherCondition;
    private Double totalDistance;

    // [User 관련 코드 삭제됨]

    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default
    private List<ItineraryItem> items = new ArrayList<>();

    public void addItem(ItineraryItem item) {
        items.add(item);
        item.setItinerary(this);
    }
}