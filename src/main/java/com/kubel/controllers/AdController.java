package com.kubel.controllers;

import com.kubel.exception.ForbiddenException;
import com.kubel.repo.specification.AdSpecification;
import com.kubel.security.UserPrincipal;
import com.kubel.service.AdService;
import com.kubel.service.dto.AdDto;
import com.kubel.service.dto.AdPhotoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping("/users/{id}/ads")
    public ResponseEntity<AdDto> crateAdByUserId(@PathVariable final Long id, @ModelAttribute @Valid AdDto adDto, final Authentication auth) throws IOException {
        var principal = (UserPrincipal) auth.getPrincipal();
        if (!principal.getId().equals(id)) {
            throw new ForbiddenException();
        }
        return ResponseEntity.ok().body(adService.crateAdByUserId(id, adDto));
    }

    @GetMapping("/ads")
    public ResponseEntity<Page<AdDto>> getAllAdd(AdSpecification adSpecification, @PageableDefault(sort = {"createdDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok().body(adService.getAllAd(adSpecification, pageable));
    }

    @GetMapping(value = "/users/{userId}/ads", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AdDto>> getAllAddByUserId(@PathVariable final Long userId, AdSpecification adSpecification, @PageableDefault Pageable pageable, final Authentication auth) {
        var principal = (UserPrincipal) auth.getPrincipal();
        if (!principal.getId().equals(userId)) {
            throw new ForbiddenException();
        }
        return ResponseEntity.ok().body(adService.getAllAdByUserId(userId, adSpecification, pageable));
    }

    @DeleteMapping("/ads/{id}")
    public ResponseEntity<Void> deleteAdById(@PathVariable final Long id, final Authentication auth) throws IOException {
        var principal = (UserPrincipal) auth.getPrincipal();
        adService.deleteAdById(id, principal.getId());
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/ads/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<AdPhotoDto> getAdById(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok().body(adService.getAdById(id));
    }
}
