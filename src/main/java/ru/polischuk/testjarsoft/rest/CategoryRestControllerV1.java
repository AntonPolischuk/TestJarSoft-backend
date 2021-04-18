package ru.polischuk.testjarsoft.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.polischuk.testjarsoft.model.Category;
import ru.polischuk.testjarsoft.service.CategoryService;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/categories")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoryRestControllerV1 {

    CategoryService categoryService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categories = categoryService.getAll();

        if(categories.isEmpty()){
             return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable("id") Long categoryId){

        if(categoryId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Category> category = categoryService.getById(categoryId);

        if(!category.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> createCategory(@RequestBody @Valid Category category){

        String checkResponse = categoryService.save(category);
        if(!checkResponse.isEmpty()){
            JSONObject res = new JSONObject();
            res.put("response",checkResponse);
            return  new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> updateCategory(@RequestBody @Valid Category category){

        if(category==null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String checkResponse = categoryService.save(category);
        if(!checkResponse.isEmpty()){
            JSONObject res = new JSONObject();
            res.put("response",checkResponse);
            return  new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> deleteCategory(@PathVariable("id") Long id){

        Optional<Category> category = categoryService.getById(id);
        if(!category.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String response = categoryService.delete(category.get());
        JSONObject res = new JSONObject();
        res.put("response",response);
        return new ResponseEntity<>(res,HttpStatus.OK);
        }
}
