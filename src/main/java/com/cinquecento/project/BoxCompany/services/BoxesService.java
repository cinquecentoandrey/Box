package com.cinquecento.project.BoxCompany.services;


import com.cinquecento.project.BoxCompany.models.OrderDetails;
import com.cinquecento.project.BoxCompany.repositories.BoxesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cinquecento.project.BoxCompany.models.Box;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BoxesService {

    private final BoxesRepository boxesRepository;

    @Autowired
    public BoxesService(BoxesRepository boxesRepository) {
        this.boxesRepository = boxesRepository;
    }

    public List<Box> findAll() {
        return boxesRepository.findAll();
    }

    public Optional<Box> findById(int id) {
        return boxesRepository.findById(id);
    }

    public Optional<Box> findByName(String name) {
        return boxesRepository.findByBoxName(name);
    }

    @Transactional
    public void add(Box box) {
        boxesRepository.save(box);
    }

    // replacing @Deprecated update methods
    @Transactional
    public void update(int id, Box updatedBox){
        updatedBox.setBoxId(id);
        boxesRepository.save(updatedBox);
    }

    // in the future it will be possible to immediately call get() one at a time
    @Transactional
    public void updateOnOrderStatus(int id) {
        Optional<Box> box = boxesRepository.findById(id);
        if(box.isPresent()) {
            box.get().setBoxId(id);
            box.get().setBoxOnOrder(box.get().getOrderDetails().stream().mapToInt(OrderDetails::getQuantity).sum());
            boxesRepository.save(box.get());
        }
    }
    @Deprecated
    @Transactional
    public void updatePrice(int id, Box box, double newPrice) {
        Optional<Box> updatedBox = boxesRepository.findByBoxName(box.getBoxName());
        if (updatedBox.isPresent()) {
            updatedBox.get().setBoxPrice(newPrice);
            boxesRepository.save(updatedBox.get());
        }
    }

    @Deprecated
    @Transactional
    public void updateOnOrderCount(Box box, int count) {
        Optional<Box> updatedBox = boxesRepository.findByBoxName(box.getBoxName());
        if (updatedBox.isPresent()) {
            updatedBox.get().setBoxOnOrder(count);
            boxesRepository.save(updatedBox.get());
        }
    }

    @Deprecated
    @Transactional
    public void updateInStockCountAfterShipping(Box box, int count) {
        Optional<Box> updatedBox = boxesRepository.findByBoxName(box.getBoxName());
        if(updatedBox.isPresent()) {
            if(updatedBox.get().getBoxInStock() > count) {
                updatedBox.get().setBoxInStock(updatedBox.get().getBoxInStock()-count);
                updatedBox.get().setBoxOnOrder(0);
                boxesRepository.save(updatedBox.get());
            }
        }
    }

    // danger!
    @Transactional
    public void delete(Box box) {
        boxesRepository.delete(box);
    }
}
