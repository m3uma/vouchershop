package pl.mczuma.vouchershop.catalog;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JdbcProductStorage implements ProductStorage {
    private final JdbcTemplate jdbcTemplate;

    public static final String SELECT_PUBLISHED = "Select * from `products_catalog__products` where price IS NOT NULL and description is NOT NULL";

    public JdbcProductStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO `products_catalog__products` " +
                        "(`id`, `description`, `picture`, `price`) values " +
                        "(?,?,?,?)",
                product.getId(),
                product.getDescription(),
                product.getPicture(),
                product.getPrice()
        );
    }

    @Override
    public boolean isExists(String productId) {
        int productsCount = jdbcTemplate.queryForObject(
                "Select count(*) from `products_catalog__products` where id = ?",
                new Object[]{productId},
                Integer.class
        );

        return productsCount > 0;
    }

    @Override
    public Optional<Product> load(String productId) {
        return null;
    }

    private static RowMapper<Product> getProductRowMapper() {
        return (rs, i) -> {
            Product p = new Product(UUID.fromString(rs.getString("id")));
            p.setDescription(rs.getString("description"));
            p.setPrice(rs.getBigDecimal("price"));
            p.setPicture(rs.getString("picture"));

            return p;
        };
    }

    @Override
    public List<Product> allProducts() {
        return jdbcTemplate.query(SELECT_PUBLISHED, getProductRowMapper());
    }

    @Override
    public void clean() {
        jdbcTemplate.execute("delete form `products_catalog__products`");
    }
}
