package ru.polischuk.testjarsoft.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.polischuk.testjarsoft.model.Banner;
import ru.polischuk.testjarsoft.service.BannerService;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/banners")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BannerRestControllerV1 {

    BannerService bannerService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Banner>> getAllBanner(){

        return new ResponseEntity<>(this.bannerService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Banner>> getBannerById(@PathVariable("id") Long bannerId){

        if(bannerId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Banner> banner = bannerService.getById(bannerId);

        if(!banner.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(banner, HttpStatus.OK);
    }

    @PostMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> createBanner(@RequestBody @Valid Banner banner){

        String checkResponse = bannerService.save(banner);
        if(!checkResponse.isEmpty()){
            JSONObject res = new JSONObject();
            res.put("response",checkResponse);
            return  new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> updateBanner(@RequestBody @Valid Banner banner){

        String checkResponse = bannerService.save(banner);
        if(!checkResponse.isEmpty()){
            JSONObject res = new JSONObject();
            res.put("response",checkResponse);
            return  new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Banner> deleteBanner(@PathVariable("id") Long id){

        Optional<Banner> banner = bannerService.getById(id);

        if(!banner.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bannerService.delete(banner.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
