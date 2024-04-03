package ua.edu.nung.pz.dao.repository;

import ua.edu.nung.pz.dao.entity.Good;
import ua.edu.nung.pz.dao.entity.Price;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PriceRepository {
    public ArrayList<Price> getAll() {
        DataSource dataSource = new DataSource();
        ArrayList<Price> prices = new ArrayList<>();
        String sql = "Select * FROM prices";

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next()) {
                Price price = new Price(
                        resultSet.getLong("id"),
                        resultSet.getLong("good_id"),
                        resultSet.getDouble("from_supplier"),
                        resultSet.getDouble("for_client"),
                        resultSet.getString("created_at"),
                        resultSet.getString("deleted_at")
                );

                prices.add(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prices;
    }
}
