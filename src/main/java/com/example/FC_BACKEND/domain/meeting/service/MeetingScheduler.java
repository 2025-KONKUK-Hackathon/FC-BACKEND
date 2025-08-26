package com.example.FC_BACKEND.domain.meeting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingScheduler {

    private final MeetingService meetingService;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateMeetingStatuses() {
        meetingService.updateMeetingStatuses();
    }
}
