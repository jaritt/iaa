package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.service.api.LendingService;

import java.util.List;

public class ShowLendingListAction implements Action {

    public ShowLendingListAction(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    private LendingService lendingService;
    private List<Lending> lendings;

    @Override
    public String execute() throws Exception {
        lendings = lendingService.listLendings();
        return SUCCESS;
    }

    public List<Lending> getLendings() {return lendings;}
}
