package com.wallace.notificationservice.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationSnsService {

    @Autowired
    AmazonSNS amazonSNS;

    public void notify(String phoneNumber, String message) {
        PublishRequest publishRequest = new PublishRequest().withMessage(message).withPhoneNumber(phoneNumber);
        amazonSNS.publish(publishRequest);
    }
}
