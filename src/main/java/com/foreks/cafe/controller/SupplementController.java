package com.foreks.cafe.controller;

import com.foreks.cafe.domain.Cart;
import com.foreks.cafe.domain.Item;
import com.foreks.cafe.domain.Supplement;
import com.foreks.cafe.repository.CartRepository;
import com.foreks.cafe.repository.ItemRepository;
import com.foreks.cafe.repository.SupplementRepository;
import com.foreks.cafe.service.SupplementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/supplement")
public class SupplementController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final SupplementService supplementService;

    private final CartRepository cartRepository;

    private final ItemRepository itemRepository;

    private final SupplementRepository supplementRepository;

    public SupplementController(SupplementService supplementService, CartRepository cartRepository, ItemRepository itemRepository, SupplementRepository supplementRepository) {
        this.supplementService = supplementService;
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.supplementRepository = supplementRepository;
    }

    // Save operation
    @PostMapping("/{id}/add/{item_id}/{amount}")
    ResponseEntity <Supplement> savesupplement(@PathVariable("id") Long supplementId,
                                     @PathVariable("item_id") Long itemId,
                                     @PathVariable("amount") Integer amount)
    {
        log.debug("Entering create supplement endpoint");
        try {
            log.info("Supplement with ID " + supplementId + " was created.");

            if(supplementRepository.findByItemIdAndSupplementId(itemId, supplementId).isPresent()) {
                Supplement supplement = supplementRepository.findByItemIdAndSupplementId(itemId, supplementId).get();
                Item item = itemRepository.findById(itemId).get();
                Cart cart = cartRepository.findById(item.getCart().getId()).get();

                supplement.setAmount(supplement.getAmount() + amount);
                supplement.setTotalCost(supplement.getTotalCost() + supplement.getCost() * amount);
                item.setTotalCost(item.getTotalCost() + supplement.getCost() * amount);
                cart.setTotalCost(cart.getTotalCost() + supplement.getCost() * amount);
                cart.discountedPrice();
                supplementRepository.save(supplement);
                return new ResponseEntity(supplement, HttpStatus.CREATED);
            }else{
                Supplement supplement = supplementService.saveSupplement(supplementId, amount);
                Item item = itemRepository.findById(itemId).get();
                Cart cart = cartRepository.findById(item.getCart().getId()).get();

                supplement.setItem(item);
                item.getSupplements().add(supplement);
                item.setTotalCost(item.getTotalCost() + supplement.getCost() * amount);
                cart.setTotalCost(cart.getTotalCost() + supplement.getCost() * amount);
                cart.discountedPrice();
                supplementRepository.save(supplement);
                return new ResponseEntity(supplement, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            log.error("Unable to create supplement with ID: " + supplementId + ", message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read operation
    @GetMapping( "/list")
    ResponseEntity <List<Supplement>> fetchSupplementList()
    {

        log.debug("Entering list supplement endpoint");
        try {
            log.info("Supplements listed.");
            List<Supplement> supplements = supplementService.fetchSupplementList();
            return new ResponseEntity(supplements, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unable to list supplements, message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Look at here
    @GetMapping( "/list/{id}")
    ResponseEntity <Optional<Supplement>> fetchSupplement(@PathVariable("id") Long id)
    {

        log.debug("Entering list supplement with ID " + id + " endpoint");
        try {
            log.info("Supplement with ID " + id + " listed.");
            Supplement supplement = supplementService.fetchSupplement(id).get();
            return new ResponseEntity(supplement, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unable to list supplement with ID " + id + ", message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete operation
    @DeleteMapping("/delete/{id}")
    ResponseEntity <String> deleteSupplementById(@PathVariable("id") Long supplementId)
    {

        log.debug("Entering delete supplement with ID " + supplementId + " endpoint");
        try {
            log.info("Supplement with ID " + supplementId + " was deleted");

            Supplement supplement = supplementRepository.findById(supplementId).get();
            Item item = itemRepository.findById(supplement.getItem().getId()).get();
            Cart cart = cartRepository.findById(item.getCart().getId()).get();
            item.setTotalCost(item.getTotalCost() - supplement.getCost());
            cart.setTotalCost(cart.getTotalCost() - supplement.getCost());
            cart.discountedPrice();

            supplementService.deleteSupplementById(supplementId);
            return new ResponseEntity("Supplement with ID " + supplementId + " was deleted", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unable to delete supplement with ID " + supplementId + ", message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}