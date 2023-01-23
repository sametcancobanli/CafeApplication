package com.foreks.cafe.service;

import com.foreks.cafe.domain.Item;
import com.foreks.cafe.repository.CartRepository;
import com.foreks.cafe.repository.ItemRepository;
import com.foreks.cafe.domain.BaseItem;
import com.foreks.cafe.repository.BaseItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private BaseItemRepository baseItemRepository;

    private CartRepository cartRepository;

    public ItemServiceImpl(ItemRepository itemRepository, BaseItemRepository baseItemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.baseItemRepository = baseItemRepository;
        this.cartRepository = cartRepository;
    }

    // Save operation
    public Item saveItem(Long itemId){
        BaseItem baseItem = baseItemRepository.findById(itemId).get();
        Item item = new Item(baseItem.getName(), baseItem.getCost());
        return itemRepository.save(item);
    }

    // Read operation
    @Override
    public List<Item> fetchItemList()
    {
        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public Optional<Item> fetchItem(Long id)
    {
        return (Optional<Item>) itemRepository.findById(id);
    }

    // Delete operation
    @Override
    public void deleteItemById(Long itemId)
    {
        itemRepository.deleteById(itemId);
    }
}
