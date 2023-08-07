import jakarta.persistence.*;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int countryCode;
    private String isoCodes;
    private long population;
    private long areaKm2;
    private String gdpUSD;

    // Getters, setters, constructeurs, etc.

    // Dans cette relation unidirectionnelle, nous n'avons pas besoin de référencer les téléphones depuis le pays.
}
