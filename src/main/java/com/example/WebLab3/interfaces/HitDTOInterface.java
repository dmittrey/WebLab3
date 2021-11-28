package com.example.WebLab3.interfaces;

import com.example.WebLab3.entity.Hit;

import java.util.List;
import java.util.Optional;

public interface HitDTOInterface {

    boolean save(Hit aHit);

    Optional<List<Hit>> getSessionEntityList(String sessionId);

    boolean deleteSessionEntityList(String sessionId);
}