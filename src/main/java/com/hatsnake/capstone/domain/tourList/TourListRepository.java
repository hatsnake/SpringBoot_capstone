package com.hatsnake.capstone.domain.tourList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourListRepository extends JpaRepository<TourList, Long> {
    @Query("SELECT t FROM TourList t")
    List<TourList> findAllAsc();

    Page<TourList> findAllByTitleContaining(Pageable pageable, String keyword);

    List<TourList> findAllByTitleContaining(String keyword);
}
