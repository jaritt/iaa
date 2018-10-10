package de.nordakademie.iaa.library.service.api;

import com.opensymphony.xwork2.inject.Inject;
import de.nordakademie.iaa.library.dao.DummyDAO;
import de.nordakademie.iaa.library.model.DummyMessage;

public interface DummyService {
    DummyMessage getMessage();
}
