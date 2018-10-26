package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.service.api.LendingService;

import java.util.List;
import java.util.stream.Collectors;

public class ShowLendingListAction implements Action {

    public ShowLendingListAction(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    private LendingService lendingService;
    private List<Lending> openLendings;

    @Override
    public String execute() throws Exception {
        openLendings = lendingService.listLendings().stream().filter(l ->!(l.isCompleted())).collect(Collectors.toList());
        return SUCCESS;
    }

    public List<Lending> getOpenLendings() {return openLendings;}
}
