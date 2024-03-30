package com.example.inventoryservice.service;

import com.example.inventoryservice.dao.entities.Creator;

import java.util.List;

public interface CreatorManager {
    public Creator addCreator(Creator creator);
    public Creator updateCreator(Creator creator);
    public boolean deleteCreator(Creator creator);
    public Creator findCreator(Creator creator);
    public Creator findCreatorById(int id);
    public List<Creator> getAllCreator();
}
