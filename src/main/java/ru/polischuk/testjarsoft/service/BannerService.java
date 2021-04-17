package ru.polischuk.testjarsoft.service;

import ru.polischuk.testjarsoft.model.Banner;
import java.util.List;
import java.util.Optional;

public interface BannerService {

    Optional<Banner> getById(Long id);
    String save(Banner banner);
    void delete(Banner banner);
    List<Banner> getAll();

}
