package com.hatsnake.capstone.domain.tourList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourListRepository extends JpaRepository<TourList, Long> {
    @Query("SELECT t FROM TourList t")
    List<TourList> findAllAsc();
}
