package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.service.api.LendingService;

import java.util.List;
import java.util.stream.Collectors;

public class ShowMainAction extends ActionSupport implements Action {

    public ShowMainAction(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    private LendingService lendingService;
    private List<Lending> overdueLendings;

    @Override
    public String execute()throws Exception{
        overdueLendings = lendingService.listLendings().stream().filter(Lending::isOverDue).collect(Collectors.toList());

        return SUCCESS;
    }

    public List<Lending> getOverdueLendings() {
        return overdueLendings;
    }
}
