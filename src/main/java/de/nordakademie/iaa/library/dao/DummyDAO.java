package de.nordakademie.iaa.library.dao;

import de.nordakademie.iaa.library.model.DummyMessage;

import javax.inject.Named;

@Named
public class DummyDAO {
    public DummyMessage getMessage(){
        return new DummyMessage();
    }

    public DummyMessage showMessage(){
        System.out.println();
    }
}
