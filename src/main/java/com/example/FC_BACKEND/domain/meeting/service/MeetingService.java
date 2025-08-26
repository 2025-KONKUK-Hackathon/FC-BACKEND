package com.example.FC_BACKEND.domain.meeting.service;

import com.example.FC_BACKEND.domain.meeting.constant.MeetingStatus;
import com.example.FC_BACKEND.domain.meeting.dto.response.MeetingSummaryResponse;
import com.example.FC_BACKEND.domain.meeting.entity.Meeting;
import com.example.FC_BACKEND.domain.meeting.entity.MeetingImage;
import com.example.FC_BACKEND.domain.meeting.entity.MeetingMember;
import com.example.FC_BACKEND.domain.meeting.repository.MeetingCustomRepositoryImpl;
import com.example.FC_BACKEND.domain.meeting.repository.MeetingImageRepository;
import com.example.FC_BACKEND.domain.meeting.repository.MeetingMemberRepository;
import com.example.FC_BACKEND.domain.meeting.repository.MeetingRepository;
import com.example.FC_BACKEND.domain.user.entity.User;
import com.example.FC_BACKEND.domain.user.service.UserService;
import com.example.FC_BACKEND.global.dto.SliceResponse;
import com.example.FC_BACKEND.global.exception.customexception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.FC_BACKEND.global.exception.constant.MeetingErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeetingService {

    private final MeetingRepository meetingRepository;

    private final UserService userService;

    private final MeetingMemberRepository meetingMemberRepository;

    private final MeetingImageRepository meetingImageRepository;

    private final MeetingCustomRepositoryImpl meetingCustomRepository;

    @Transactional
    public Long createMeeting(Long userId, String meetingName, String content, String category, int recruitNumber,
                              LocalDate recruitStartDate, LocalDate recruitEndDate, LocalDate actualStartDate, LocalDate actualEndDate,
                              List<String> imageUrls) {

        User user = userService.findUser(userId);

        validateDate(recruitStartDate, recruitEndDate, actualStartDate, actualEndDate);

        Meeting meeting = Meeting.createMeeting(meetingName, content, category, recruitNumber, recruitStartDate, recruitEndDate, actualStartDate, actualEndDate);
        setMeetingStatus(recruitStartDate, recruitEndDate, meeting);
        meetingRepository.save(meeting);

        List<String> urls = imageUrls != null ? imageUrls : List.of();
        if (!urls.isEmpty()) {

            List<MeetingImage> images = new ArrayList<>(urls.size());
            for (String url : urls) {
                images.add(MeetingImage.create(meeting, url));
            }
            meetingImageRepository.saveAll(images);
        }

        MeetingMember meetingMember = MeetingMember.create(user, meeting, LocalDateTime.now());
        meetingMember.setHost(true);
        meetingMemberRepository.save(meetingMember);

        return meeting.getId();

    }

    private void validateDate(LocalDate recruitStartDate, LocalDate recruitEndDate, LocalDate actualStartDate, LocalDate actualEndDate) {
        if(LocalDate.now().isAfter(actualEndDate)){
            throw new CustomException(INVALID_ACTUAL_DATE);
        }

        if(actualEndDate.isBefore(actualStartDate) || recruitEndDate.isBefore(recruitStartDate)){
            throw new CustomException(INVALID_DATE);
        }
    }

    private void setMeetingStatus(LocalDate recruitStartDate, LocalDate recruitEndDate, Meeting meeting) {
        LocalDate now = LocalDate.now();

        if(now.isAfter(recruitStartDate) && now.isBefore(recruitEndDate) ){
            meeting.setMeetingStatus(MeetingStatus.IN_PROGRESS);
        }

        if(now.isBefore(recruitStartDate)){
            meeting.setMeetingStatus(MeetingStatus.NOT_STARTED);
        }
    }

    @Transactional
    public void updateMeetingStatuses() {
        LocalDate today = LocalDate.now();
        List<Meeting> meetings = meetingRepository.findAll();

        for (Meeting meeting : meetings) {
            if (today.isBefore(meeting.getRecruitStartDate())) {
                meeting.setMeetingStatus(MeetingStatus.NOT_STARTED);
            } else if (!today.isAfter(meeting.getActualEndDate())) {
                meeting.setMeetingStatus(MeetingStatus.IN_PROGRESS);
            } else {
                meeting.setMeetingStatus(MeetingStatus.FINISHED);
            }
        }
    }

    public SliceResponse<MeetingSummaryResponse, Long> getAllMeetings(Long cursorId, int size){
        Slice<MeetingSummaryResponse> meetings = meetingCustomRepository.findAllByCursorId(cursorId, size);

        return SliceResponse.from(meetings);
    }

}
