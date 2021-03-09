package com.kubel.controllers;

import com.kubel.exception.ForbiddenException;
import com.kubel.security.UserPrincipal;
import com.kubel.service.AdService;
import com.kubel.service.dto.AdDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping("users/{id}/ads")
    public ResponseEntity<AdDto> crateAdByUserId(@PathVariable final Long id, @RequestBody @Valid AdDto adDto, final Authentication auth){
        var principal = (UserPrincipal)auth.getPrincipal();
        if (!principal.getId().equals(id)){
            throw new ForbiddenException();
        }
        return ResponseEntity.ok().body(adService.crateAdByUserId(id, adDto));
    }
    @GetMapping("/ads")
    public ResponseEntity<Page<AdDto>> getAllAdd(@PageableDefault Pageable pageable){
        return ResponseEntity.ok().body(adService.getAllAd(pageable));
    }
}
