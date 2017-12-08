package com.spring.demo.controller.user;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.demo.model.AjaxResponse;
import com.spring.demo.model.SearchCriteria;
import com.spring.demo.model.User;
import com.spring.demo.service.UserService;
 
@Controller
public class UserController {
	@Autowired
	private UserService userService;

    

    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(
            @Valid @RequestBody SearchCriteria search, Errors errors) {
        AjaxResponse result = new AjaxResponse();
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        List<User> users = userService.findByUserNameOrEmail(search.getUsername());
        if (users.isEmpty()) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(users);
        return ResponseEntity.ok(result);
    }

}
