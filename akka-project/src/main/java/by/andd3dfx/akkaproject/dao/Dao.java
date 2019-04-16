package by.andd3dfx.akkaproject.dao;

import by.andd3dfx.akkaproject.model.DataItem;

import java.util.List;

public interface Dao {
    List<DataItem> retrieveItems(int maxSize);
}
