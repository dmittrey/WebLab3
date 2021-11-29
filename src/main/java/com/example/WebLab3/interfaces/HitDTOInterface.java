package com.example.WebLab3.interfaces;

import com.example.WebLab3.entity.Hit;
import com.example.WebLab3.entity.User;

import java.util.List;
import java.util.Optional;

public interface HitDTOInterface {

    void saveHit(Hit aHit);

    Optional<List<Hit>> getSessionHitList(User anUser);

    void deleteSessionEntityList(User anUser);
}