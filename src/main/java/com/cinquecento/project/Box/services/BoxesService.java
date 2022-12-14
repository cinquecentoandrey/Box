package com.cinquecento.project.Box.services;


import com.cinquecento.project.Box.models.OrderDetails;
import com.cinquecento.project.Box.repositories.BoxesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cinquecento.project.Box.models.Box;

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

    @Transactional
    public void update(int id, Box updatedBox){
        updatedBox.setBoxId(id);
        boxesRepository.save(updatedBox);
    }

    @Transactional
    public void updateOnOrderStatusById(int id) {
        Optional<Box> box = boxesRepository.findById(id);
        if(box.isPresent()) {
            box.get().setBoxId(id);
            box.get().setBoxOnOrder(box
                                    .get()
                                    .getOrderDetails()
                                    .stream()
                                    .mapToInt(OrderDetails::getQuantity)
                                    .sum());
            boxesRepository.save(box.get());
        }
    }

    @Transactional
    public void updateOnOrderStatus() {
        boxesRepository.findAll().forEach(box -> {
            updateOnOrderStatusById(box.getBoxId());
        });
    }


    @Transactional
    public void delete(Box box) {
        boxesRepository.delete(box);
    }
}
