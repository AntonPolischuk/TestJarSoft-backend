package ru.polischuk.testjarsoft.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="categories")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Category{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    Long id;

    @Column(name = "name")
    @NotNull(message = "The field \"Category Name\" cannot be empty ")
    @NotBlank(message = "The field \"Category Name\" cannot be empty ")
    @Size(max=255, message = "The length of the \"Category Name\" field cannot exceed 255 characters ")
    String categoryName;

    @Column(name = "req_name", unique = true)
    @NotNull(message = "The field \"Request ID\" cannot be empty ")
    @NotBlank(message = "The field \"Request ID\" cannot be empty ")
    @Size(max=255, message = "The length of the \"Request ID\" field cannot exceed 255 characters ")
    String reqName;

    @Column(nullable = false)
    Boolean deleted;

}
