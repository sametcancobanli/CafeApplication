package com.foreks.cafe.controller;

import com.foreks.cafe.domain.Cart;
import com.foreks.cafe.domain.Item;
import com.foreks.cafe.repository.CartRepository;
import com.foreks.cafe.repository.ItemRepository;
import com.foreks.cafe.service.CartService;
import com.foreks.cafe.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/item")
public class ItemController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private ItemService itemService;
    private CartService cartService;
    private CartRepository cartRepository;
    private ItemRepository itemRepository;

    public ItemController(ItemService itemService, CartService cartService, CartRepository cartRepository, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    // Save operation
    @PostMapping("/{id}/add/{cart_id}")
    ResponseEntity <Item> saveitem(@PathVariable("id") Long itemId,
                         @PathVariable("cart_id") Long cartId)
    {
        log.debug("Entering create item endpoint");
        try {
            log.info("Item with ID " + itemId + " was created.");
            Item item = itemService.saveItem(itemId);
            Cart cart = cartRepository.findById(cartId).get();

            item.setCart(cart);
            cart.getItems().add(item);
            item.setTotalCost(item.getCost());
            cart.setTotalCost(cart.getTotalCost() + item.getCost());
            cart.setTotalItem(cart.getTotalItem() + 1);
            cart.discountedPrice();
            itemRepository.save(item);

            return new ResponseEntity(item, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Unable to create item with ID: " + itemId + ", message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read operation
    @GetMapping( "/list")
    ResponseEntity <List<Item>> fetchItemList()
    {
        log.debug("Entering list items endpoint");
        try {
            log.info("Items listed.");
            List<Item> items = itemService.fetchItemList();
            return new ResponseEntity(items, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unable to list items, message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Look at here
    @GetMapping( "/list/{id}")
    ResponseEntity <Optional<Item>> fetchItem(@PathVariable("id") Long id)
    {
        log.debug("Entering list item with ID " + id + " endpoint");
        try {
            log.info("Item with ID " + id + " listed.");
            Item item =  itemService.fetchItem(id).get();
            return new ResponseEntity(item, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unable to list item with ID " + id + ", message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete operation
    @DeleteMapping("/delete/{id}")
    ResponseEntity <String> deleteItemById(@PathVariable("id") Long itemId)
    {
        log.debug("Entering delete item with ID " + itemId + " endpoint");
        try {
            log.info("Item with ID " + itemId + " was deleted");
            Item item = itemRepository.findById(itemId).get();
            Cart cart = cartRepository.findById(item.getCart().getId()).get();
            cart.setTotalCost(cart.getTotalCost() - item.getTotalCost());
            cart.setTotalItem(cart.getTotalItem() - 1);
            cart.discountedPrice();
            itemService.deleteItemById(itemId);
            return new ResponseEntity("Item with ID " + itemId + " was deleted", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unable to delete item with ID " + itemId + ", message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}