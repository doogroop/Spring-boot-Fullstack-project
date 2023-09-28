package com.cpan252.assignmentOne.cpan252assignmentOne.repository;


import com.cpan252.assignmentOne.cpan252assignmentOne.model.Brand;
import com.cpan252.assignmentOne.cpan252assignmentOne.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemJpaRepository extends JpaRepository<Item, Long> {
    Page<Item> findByBrandAndYearOfCreation(int i, String dior, PageRequest of);



    @Query("SELECT i FROM Item i WHERE i.brand = :brand AND i.yearOfCreation = :year")
    List<Item> findItemsByBrandAndYear(@Param("brand") Brand brand, @Param("year") int year);




}
