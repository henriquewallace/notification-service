package com.wallace.notificationservice.listener;

import com.wallace.notificationservice.domain.Proposal;
import com.wallace.notificationservice.service.NotificationSnsService;
import com.wallace.notificationservice.utils.ConstantMessages;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PendingProposalListener {

    private final NotificationSnsService notificationSnsService;

    @RabbitListener(queues = "${rabbitmq.queue.pending.proposal}")
    public void PendingProposal(Proposal proposal) {
        String message = String.format(ConstantMessages.PROPOSAL_UNDER_ANALYSIS, proposal.getUser().getName());
        notificationSnsService.notify(message);
    }
}
