package com.asset.ASSET.MANAGEMENT.SYSTEM.services;

import com.asset.ASSET.MANAGEMENT.SYSTEM.Repository.AssetRepository;
import com.asset.ASSET.MANAGEMENT.SYSTEM.entities.Asset;
import com.asset.ASSET.MANAGEMENT.SYSTEM.entities.AssetLocation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }


    // CREATE ASSET
    public Asset createAsset(Asset asset) {

        // Find the latest asset for:
        // location + year + asset type
        Asset lastAsset =
                assetRepository
                        .findTopByPopLocationAndYearAndAssetTypeOrderByIdDesc(
                                asset.getPopLocation(),
                                asset.getYear(),
                                asset.getAssetType()
                        )
                        .orElse(null);


        // Start numbering at 001
        int nextNumber = 1;

        // If an asset already exists in this
        // location + year + asset type,
        // increase its number by 1
        if (lastAsset != null) {

            String lastNumber = lastAsset.getNumber();

            String[] parts = lastNumber.split("/");

            String numberPart = parts[parts.length - 1];

            nextNumber = Integer.parseInt(numberPart) + 1;
        }


        // Generate:
        // VDT/LOCATION/YEAR/ASSET_TYPE/NUMBER
        String generatedNumber =
                String.format(
                        "VDT/%s/%d/%s/%03d",
                        asset.getPopLocation(),
                        asset.getYear(),
                        asset.getAssetType().toUpperCase(),
                        nextNumber
                );


        // Put generated number into asset
        asset.setNumber(generatedNumber);


        // Save asset
        return assetRepository.save(asset);
    }


    // GET ALL ASSETS
    public List<Asset> getAllAssets() {

        return assetRepository.findAll();
    }


    // GET ASSET BY ID
    public Asset getAssetById(Long id) {

        return assetRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Asset with id "
                                        + id
                                        + " not found"
                        )
                );
    }


    // UPDATE ASSET
    public Asset updateAsset(Long id, Asset updatedAsset) {

        Asset existingAsset = getAssetById(id);

        existingAsset.setName(
                updatedAsset.getName()
        );

        existingAsset.setManufacturer(
                updatedAsset.getManufacturer()
        );

        existingAsset.setModel(
                updatedAsset.getModel()
        );

        existingAsset.setSerialMac(
                updatedAsset.getSerialMac()
        );

        existingAsset.setAssetType(
                updatedAsset.getAssetType()
        );

        existingAsset.setStatus(
                updatedAsset.getStatus()
        );

        existingAsset.setPopLocation(
                updatedAsset.getPopLocation()
        );

        existingAsset.setPopHead(
                updatedAsset.getPopHead()
        );

        existingAsset.setYear(
                updatedAsset.getYear()
        );

        existingAsset.setNumber(
                existingAsset.getNumber()
        );


        return assetRepository.save(existingAsset);
    }


    // DELETE ASSET
    public void deleteAsset(Long id) {

        Asset asset = getAssetById(id);

        assetRepository.delete(asset);
    }


    // SEARCH BY POP LOCATION
    public List<Asset> searchByLocation(
            AssetLocation location
    ) {

        return assetRepository.findByPopLocation(location);
    }


    // SEARCH BY NUMBER
    public Asset searchByNumber(String number) {

        return assetRepository.findByNumber(number)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Asset with number "
                                        + number
                                        + " not found"
                        )
                );
    }


    // SEARCH BY NAME
    public List<Asset> searchByName(String name) {

        return assetRepository
                .findByNameContainingIgnoreCase(name);
    }
}