package com.kubel.controllers;

import com.kubel.exception.Forbidden;
import com.kubel.security.UserPrincipal;
import com.kubel.service.AdService;
import com.kubel.service.dto.AdDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping("user/{id}/ad")
    public ResponseEntity<AdDto> crateAdByUserId(@PathVariable final Long id, @RequestBody @Valid AdDto adDto, final Authentication auth){
        var principal = (UserPrincipal)auth.getPrincipal();
        if (!principal.getId().equals(id)){
            throw new Forbidden();
        }
        return ResponseEntity.ok().body(adService.crateAdByUserId(id, adDto));
    }
}
