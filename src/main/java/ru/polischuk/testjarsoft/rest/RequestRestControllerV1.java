package ru.polischuk.testjarsoft.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.polischuk.testjarsoft.service.RequestService;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RequestRestControllerV1 {

    RequestService requestService;

    @GetMapping(value = "/bid", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getBannerContentByReqName(@RequestParam("category") String reqName,
                                                @RequestHeader(value="User-Agent", required=false) String userAgent,
                                                HttpServletRequest request){

            String remoteAddress = request.getRemoteAddr();
            String content = requestService.getBannerContent(remoteAddress,userAgent,reqName);

            if(content.equals("")){
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(content,HttpStatus.OK);
    }
}
