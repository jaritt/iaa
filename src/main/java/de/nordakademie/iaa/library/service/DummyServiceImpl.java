package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.dao.DummyDAO;
import de.nordakademie.iaa.library.model.DummyMessage;
import de.nordakademie.iaa.library.service.api.DummyService;

import javax.inject.Named;

@Named
public class DummyServiceImpl implements DummyService {

    private DummyDAO dummyDAO;

    DummyServiceImpl(DummyDAO dummyDAO) {
        this.dummyDAO = dummyDAO;
    }

    @Override
    public DummyMessage getMessage() {
        return dummyDAO.getMessage();
    }
}
