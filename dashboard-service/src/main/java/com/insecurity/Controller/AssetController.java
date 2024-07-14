package com.insecurity.Controller;

import com.insecurity.model.Asset;
import com.insecurity.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    // Method to handle requests for displaying assets as HTML
    @GetMapping("/html")
    public String getAllAssets(Model model) {
        List<Asset> assets = assetService.getAllAssets();
        model.addAttribute("assets", assets);
        return "assets"; // This assumes "assets.html" exists under src/main/resources/templates
    }

    // RESTful endpoint to get all assets as JSON
    @GetMapping
    @ResponseBody
    public List<Asset> getAllAssetsRest() {
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        Optional<Asset> asset = assetService.getAssetById(id);
        return asset.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{new}")
    public Asset createAsset(@RequestBody Asset asset) {
        return assetService.saveAsset(asset);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody Asset assetDetails) {
        Asset updatedAsset = assetService.updateAsset(id, assetDetails);
        return ResponseEntity.ok(updatedAsset);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/project/{projectId}")
    public List<Asset> getAssetsByProjectId(@PathVariable Long projectId) {
        return assetService.getAssetsByProjectId(projectId);
    }

    @GetMapping("/status/{status}")
    public List<Asset> getAssetsByStatus(@PathVariable String status) {
        return assetService.getAssetsByStatus(status);
    }

    @GetMapping("/owner/{owner}")
    public List<Asset> getAssetsByOwner(@PathVariable String owner) {
        return assetService.getAssetsByOwner(owner);
    }

    @GetMapping("/filter")
    public List<Asset> filterAssets(@RequestParam String status, @RequestParam String type, @RequestParam String owner) {
        return assetService.filterAssets(status, type, owner);
    }

    @GetMapping("/vulnerability-count/{minVulnerabilities}")
    public List<Asset> getAssetsByVulnerabilityCount(@PathVariable int minVulnerabilities) {
        return assetService.getAssetsByVulnerabilityCount(minVulnerabilities);
    }

    @PutMapping("/project/{projectId}/status/{status}")
    public ResponseEntity<Void> updateAssetStatusByProjectId(@PathVariable Long projectId, @PathVariable String status) {
        assetService.updateAssetStatusByProjectId(projectId, status);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count/status/{status}")
    public long countAssetsByStatus(@PathVariable String status) {
        return assetService.countAssetsByStatus(status);
    }
}
