package com.example.WebLab3.interfaces;

import java.util.List;
import java.util.Optional;

public interface OneToManyDAO<T, U> {

    void saveUnit(T unitObj);

    void initOwner(U ownerObj);

    void removeOwner(U ownerObj);

    Optional<List<T>> getOwnerUnitsList(U ownerObj);

    void deleteOwnerUnits(U ownerObj);
}