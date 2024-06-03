package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @ApiOperation(value = "일기 텍스트와 날씨를 이용해서 DB에 일기를 저장합니다.", notes = "이것은 노트")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "일기를 작성할 일자를 지정합니다.",
                             example = "2024-01-01")
                     LocalDate date, @RequestBody String text)
    {
        diaryService.createDiary(date, text);
    }

    @ApiOperation("지정한 일자의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                          @ApiParam(value = "일기를 조회할 일자를 지정합니다.",
                                  example = "2024-01-01")
                          LocalDate date)
    {
        return diaryService.readDiary(date);
    }

    @ApiOperation("지정한 기간의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "조회할 기간의 시작일 입니다.",
                                    example = "2024-01-01")
                            LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "조회할 기간의 종료일 입니다.",
                                    example = "2024-01-01")
                            LocalDate endDate)
    {
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("지정한 일자의 첫 일기 데이터를 수정합니다.")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "일기를 수정할 일자를 지정합니다.",
                             example = "2024-01-01")
                     LocalDate date, @RequestBody String text)
    {
        diaryService.updateDiary(date, text);
    }

    @ApiOperation("지정한 일자의 모든 일기 데이터를 삭제합니다.")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "일기를 삭제할 일자를 지정합니다.",
                             example = "2024-01-01")
                     LocalDate date)
    {
        diaryService.deleteDiary(date);
    }
}
