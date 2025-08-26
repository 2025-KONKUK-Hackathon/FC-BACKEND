package com.example.FC_BACKEND.domain.meeting.controller;

import com.example.FC_BACKEND.domain.meeting.dto.request.MeetingCreateRequest;
import com.example.FC_BACKEND.domain.meeting.service.MeetingService;
import com.example.FC_BACKEND.global.annotation.CustomExceptionDescription;
import com.example.FC_BACKEND.global.annotation.LoginUserId;
import com.example.FC_BACKEND.global.config.swagger.SwaggerResponseDescription;
import com.example.FC_BACKEND.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.FC_BACKEND.global.config.swagger.SwaggerResponseDescription.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("meetings")
public class MeetingController {

    private final MeetingService meetingService;

    @Tag(name = "모임 관련 API")
    @Operation(summary = "모임 생성")
    @CustomExceptionDescription(MEETING_CREATE)
    @PostMapping()
    public BaseResponse<Long> createMeeting(@LoginUserId @Parameter(hidden = true) Long userId, @RequestBody MeetingCreateRequest req){
        return BaseResponse.create(meetingService.createMeeting(userId, req.meetingName(), req.content(), req.category(),
                req.recruitNumber(), req.recruitStartDate(), req.recruitEndDate(), req.actualStartDate(), req.actualEndDate(), req.imageUrls()),
                "모임 생성에 성공하였습니다.");
    }
}
