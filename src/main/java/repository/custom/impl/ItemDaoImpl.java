package repository.custom.impl;

import dto.Item;
import javafx.collections.ObservableList;
import repository.CrudDao;
import repository.custom.CustomerDao;
import repository.custom.ItemDao;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public ObservableList<Item> getAll() {
        return null;
    }

    @Override
    public boolean update(Item item) {
        return false;
    }

    @Override
    public Item search(String id) {
        return null;
    }
}
