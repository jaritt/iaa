package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.model.Reminder;
import de.nordakademie.iaa.library.service.api.LendingService;
import de.nordakademie.iaa.library.service.api.ReminderService;

public class ReminderAction extends ActionSupport implements Action {

    public ReminderAction(LendingService lendingService, ReminderService reminderService) {
        this.lendingService = lendingService;
        this.reminderService = reminderService;
    }

    private Reminder reminder;

    private Lending lending;
    private Long lendingId;

    private LendingService lendingService;
    private ReminderService reminderService;

    public String remind() {
        lending = lendingService.loadLending(lendingId);
        reminderService.sendReminder(lending);
        return SUCCESS;
    }

    public void validateRemind() {
        if (lending == null && lendingId == null){
            addActionError(getText("error.selectLending"));
        }
    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    public Lending getLending() {
        return lending;
    }

    public void setLending(Lending lending) {
        this.lending = lending;
    }

    public Long getLendingId() {
        return lendingId;
    }

    public void setLendingId(Long lendingId) {
        this.lendingId = lendingId;
    }

    public LendingService getLendingService() {
        return lendingService;
    }

    public void setLendingService(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    public ReminderService getReminderService() {
        return reminderService;
    }

    public void setReminderService(ReminderService reminderService) {
        this.reminderService = reminderService;
    }
}
