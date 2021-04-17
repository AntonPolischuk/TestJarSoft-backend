package ru.polischuk.testjarsoft.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="request_id")
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "banner_id")
    private Banner banner;

    @Column(name="user_agent", nullable = false)
    private String userAgent;

    @Column(name="ip_address", nullable = false)
    private String remoteAddress;

    @Column(nullable = false)
    private LocalDateTime date;
}
