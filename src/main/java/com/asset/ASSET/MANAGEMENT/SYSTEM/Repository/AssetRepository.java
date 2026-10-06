package com.asset.ASSET.MANAGEMENT.SYSTEM.Repository;

import com.asset.ASSET.MANAGEMENT.SYSTEM.entities.Asset;
import com.asset.ASSET.MANAGEMENT.SYSTEM.entities.AssetLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    // Find the latest asset for:
    // POP location + year + asset type
    Optional<Asset> findTopByPopLocationAndYearAndAssetTypeOrderByIdDesc(
            AssetLocation popLocation,
            Integer year,
            String assetType
    );


    // Search assets by POP location
    List<Asset> findByPopLocation(
            AssetLocation popLocation
    );


    // Search asset by generated asset number
    Optional<Asset> findByNumber(
            String number
    );


    // Search assets by name
    List<Asset> findByNameContainingIgnoreCase(
            String name
    );
}