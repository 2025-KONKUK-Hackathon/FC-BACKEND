package com.example.FC_BACKEND.domain.meeting.controller;

import com.example.FC_BACKEND.domain.meeting.dto.request.MeetingCreateRequest;
import com.example.FC_BACKEND.domain.meeting.dto.response.MeetingDetailResponse;
import com.example.FC_BACKEND.domain.meeting.dto.response.MeetingSummaryResponse;
import com.example.FC_BACKEND.domain.meeting.service.MeetingService;
import com.example.FC_BACKEND.global.annotation.CustomExceptionDescription;
import com.example.FC_BACKEND.global.annotation.LoginUserId;
import com.example.FC_BACKEND.global.config.swagger.SwaggerResponseDescription;
import com.example.FC_BACKEND.global.dto.SliceResponse;
import com.example.FC_BACKEND.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @Tag(name = "모임 관련 API")
    @Operation(summary = "모임 목록 조회")
    @CustomExceptionDescription(COMMON)
    @GetMapping()
    public BaseResponse<SliceResponse<MeetingSummaryResponse, Long>> getAllMeetings(
            @RequestParam(required = false) Long cursorId, @RequestParam(required = false, defaultValue = "10") int size){
        return BaseResponse.ok(meetingService.getAllMeetings(cursorId, size),"모임 목록 조회에 성공하였습니다.");
    }

    @Tag(name = "모임 관련 API")
    @Operation(summary = "모임 상세 조회")
    @CustomExceptionDescription(MEETING_DETAIL)
    @GetMapping("{meetingId}")
    public BaseResponse<MeetingDetailResponse> getMeetingById(@PathVariable Long meetingId){
        return BaseResponse.ok(meetingService.getMeetingById(meetingId),"모임 상세 조회에 성공하였습니다.");
    }

    @Tag(name = "모임 관련 API")
    @Operation(summary = "모임 신청하기")
    @CustomExceptionDescription(ADD_MEETING_MEMBER)
    @PostMapping("{meetingId}")
    public BaseResponse<Void> addMeetingMember(@LoginUserId @Parameter(hidden = true) Long userId,
                                               @PathVariable Long meetingId){
        meetingService.addMeetingMember(userId, meetingId);
        return BaseResponse.ok("모임 신청에 성공하였습니다.");
    }

}
