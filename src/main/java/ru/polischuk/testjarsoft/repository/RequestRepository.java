package ru.polischuk.testjarsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.polischuk.testjarsoft.model.Request;

import java.time.LocalDateTime;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Long> {

   List<Request> findAllByDateAfterAndRemoteAddressAndUserAgent(LocalDateTime dateTime,String remoteAddress,String userAgent);
}
