package com.wallace.notificationservice.listener;

import com.wallace.notificationservice.domain.Proposal;
import com.wallace.notificationservice.service.NotificationSnsService;
import com.wallace.notificationservice.utils.ConstantMessages;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.util.Currency;

@AllArgsConstructor
@Component
public class FinishedProposalListener {

    private final NotificationSnsService notificationSnsService;

    @RabbitListener(queues = "${rabbitmq.queue.finished.proposal}")
    public void finishedProposal(@Payload Proposal proposal) {
        var phoneNumber = proposal.getUser().getPhoneNumber();
        var formatRequestedAmount = NumberFormat.getCurrencyInstance().format(proposal.getRequestedAmount());
        if (proposal.getApproved()) {
            notificationSnsService.notify(phoneNumber,
                    String.format(ConstantMessages.APPROVED_PROPOSAL, proposal.getUser().getName(), formatRequestedAmount));
        } else {
            notificationSnsService.notify(phoneNumber,
                    String.format(ConstantMessages.REPROVED_PROPOSAL, proposal.getUser().getName(), proposal.getObservation()));
        }
    }
}
