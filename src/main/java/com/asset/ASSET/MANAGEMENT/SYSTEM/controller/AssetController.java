package com.asset.ASSET.MANAGEMENT.SYSTEM.controller;

import com.asset.ASSET.MANAGEMENT.SYSTEM.entities.Asset;
import com.asset.ASSET.MANAGEMENT.SYSTEM.entities.AssetLocation;
import com.asset.ASSET.MANAGEMENT.SYSTEM.services.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }


    // CREATE ASSET
    @PostMapping
    public ResponseEntity<Asset> createAsset(
            @RequestBody Asset asset
    ) {

        Asset createdAsset =
                assetService.createAsset(asset);

        return new ResponseEntity<>(
                createdAsset,
                HttpStatus.CREATED
        );
    }


    // GET ALL ASSETS
    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {

        return ResponseEntity.ok(
                assetService.getAllAssets()
        );
    }


    // GET ASSET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                assetService.getAssetById(id)
        );
    }


    // UPDATE ASSET
    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(
            @PathVariable Long id,
            @RequestBody Asset asset
    ) {

        return ResponseEntity.ok(
                assetService.updateAsset(id, asset)
        );
    }


    // DELETE ASSET
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(
            @PathVariable Long id
    ) {

        assetService.deleteAsset(id);

        return ResponseEntity
                .noContent()
                .build();
    }


    // SEARCH BY POP LOCATION
    @GetMapping("/search/location/{location}")
    public ResponseEntity<List<Asset>> searchByLocation(
            @PathVariable AssetLocation location
    ) {

        return ResponseEntity.ok(
                assetService.searchByLocation(location)
        );
    }


    // SEARCH BY ASSET NUMBER
    @GetMapping("/search/number/{number}")
    public ResponseEntity<Asset> searchByNumber(
            @PathVariable String number
    ) {

        return ResponseEntity.ok(
                assetService.searchByNumber(number)
        );
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<Asset>> searchByName(
            @PathVariable String name
    ) {

        return ResponseEntity.ok(
                assetService.searchByName(name)
        );
    }
}