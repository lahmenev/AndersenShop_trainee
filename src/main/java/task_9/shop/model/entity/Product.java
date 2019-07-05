package task_9.shop.model.entity;

import javax.persistence.*;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String currency;

    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;
    private int price;
    private int amount;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(String name, String currency, int price, int amount, Country country) {
        this.name = name;
        this.currency = currency;
        this.price = price;
        this.amount = amount;
        this.country = country;
    }

    public Product(int id, String name, String currency, int price, int amount, Country country) {
        this(name, currency, price, amount, country);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
