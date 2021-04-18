package ru.polischuk.testjarsoft.model;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="banners")
@EqualsAndHashCode
@Slf4j
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="banner_id")
    Long id;

    @NotNull(message = "The field \"Banner Name\" cannot be empty ")
    @NotBlank(message = "The field \"Banner Name\" cannot be empty ")
    @Column(unique = true, nullable = false)
    String name;

    @Column(name="price", nullable = false)
    @NotNull (message = "The field \"Price\" cannot be empty ")
    @DecimalMax(value = "9999.99", message = "The price cannot be more than 9999.99 ")
    @DecimalMin(value = "0.00", message = "The price cannot be less than 0.00 ")
    @Setter(AccessLevel.PRIVATE)
    BigDecimal priceEntity;


    @Transient
    @Pattern(regexp = "^-?\\d+(.|,)?\\d{0,2}0*",
            message = "The price field must contain a number " +
                      "and the number of digits after the separator is no more than 2 ")
    String price;

    @Column(nullable = false)
    @NotNull(message = "The field \"Content\" cannot be empty ")
    @NotBlank(message = "The field \"Content\" cannot be empty ")
    @Size(max=65535, message = "Еhe content field cannot contain more than 65535 characters ")
    String content;

    @Column(nullable = false)
    Boolean deleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull
    Category category;

    public void setPrice(String priceToSave) {
        this.price = priceToSave;
        try {
            String priseE = priceToSave.replace(",",".");
            this.priceEntity = new BigDecimal(priseE);
            log.info(String.valueOf(priceEntity));
        } catch (Exception e){
            this.priceEntity = new BigDecimal(0);
        }
    }
}
