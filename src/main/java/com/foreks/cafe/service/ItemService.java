package com.foreks.cafe.service;

import com.foreks.cafe.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    // Save operation
    Item saveItem(Long itemId);

    // Read operation
    List<Item> fetchItemList();

    Optional<Item> fetchItem(Long id);

    // Delete operation
    void deleteItemById(Long itemId);
}
