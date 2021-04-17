package ru.polischuk.testjarsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.polischuk.testjarsoft.model.Banner;

import java.util.List;
import java.util.Optional;

public interface BannerRepository extends JpaRepository<Banner,Long> {

    List<Banner> findByDeleted(Boolean deleted);
    Optional<Banner> findByIdAndDeleted(Long id, Boolean deleted);
    List <Banner> findAllByCategory_ReqNameAndDeletedFalse(String reqName);
}
