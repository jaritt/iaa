package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import de.nordakademie.iaa.library.model.DummyMessage;
import de.nordakademie.iaa.library.service.api.DummyService;

public class DummyAction implements Action {

    public DummyAction(DummyService dummyService) {
        this.dummyService = dummyService;
    }

    private DummyService dummyService;

    private DummyMessage message;

    @Override
    public String execute() throws Exception {
        message = dummyService.getMessage();
        return SUCCESS;
    }

    public DummyMessage getMessage() {
        return message;
    }
}
