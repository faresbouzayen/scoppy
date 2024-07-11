package com.insecurity.service;

import com.insecurity.exception.ResourceNotFoundException;
import com.insecurity.model.Asset;
import com.insecurity.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Optional<Asset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }

    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Transactional
    public Asset updateAsset(Long id, Asset assetDetails) {
        Asset asset = assetRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + id));
        asset.setName(assetDetails.getName());
        asset.setType(assetDetails.getType());
        asset.setOwner(assetDetails.getOwner());
        asset.setLocation(assetDetails.getLocation());
        asset.setStatus(assetDetails.getStatus());
        asset.setDescription(assetDetails.getDescription());
        asset.setProject(assetDetails.getProject());
        asset.setVulnerabilities(assetDetails.getVulnerabilities());
        return assetRepository.save(asset);
    }

    public void deleteAsset(Long id) {
        if (!assetRepository.existsById(id)) {
            throw new ResourceNotFoundException("Asset not found with id " + id);
        }
        assetRepository.deleteById(id);
    }

    public List<Asset> getAssetsByProjectId(Long projectId) {
        return assetRepository.findByProjectId(projectId);
    }

    public List<Asset> getAssetsByStatus(String status) {
        return assetRepository.findByStatus(status);
    }

    public List<Asset> getAssetsByOwner(String owner) {
        return assetRepository.findByOwner(owner);
    }

    public List<Asset> filterAssets(String status, String type, String owner) {
        return assetRepository.findByStatusAndTypeAndOwner(status, type, owner);
    }

    public List<Asset> getAssetsByVulnerabilityCount(int minVulnerabilities) {
        return assetRepository.findByVulnerabilityCountGreaterThan(minVulnerabilities);
    }

    public void updateAssetStatusByProjectId(Long projectId, String status) {
        List<Asset> assets = assetRepository.findByProjectId(projectId);
        for (Asset asset : assets) {
            asset.setStatus(status);
            assetRepository.save(asset);
        }
    }

    public long countAssetsByStatus(String status) {
        return assetRepository.countByStatus(status);
    }
}
