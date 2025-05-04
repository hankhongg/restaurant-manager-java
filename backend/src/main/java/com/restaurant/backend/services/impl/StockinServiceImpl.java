package com.restaurant.backend.services.impl;

import com.restaurant.backend.domains.dto.StockIn.StockInDTO;
import com.restaurant.backend.domains.dto.StockIn.StockInDetailsIngreDTO;
import com.restaurant.backend.domains.dto.StockIn.StockInDetailsDrinkOtherDTO;
import com.restaurant.backend.domains.entities.Ingredient;
import com.restaurant.backend.domains.entities.MenuItem;
import com.restaurant.backend.domains.entities.StockIn;
import com.restaurant.backend.domains.entities.StockInDetailsDrinkOther;
import com.restaurant.backend.domains.entities.StockInDetailsDrinkOtherId;
import com.restaurant.backend.domains.entities.StockInDetailsIngre;
import com.restaurant.backend.domains.entities.StockInDetailsIngreId;
import com.restaurant.backend.repositories.IngredientRepository;
import com.restaurant.backend.repositories.MenuItemRepository;
import com.restaurant.backend.repositories.StockInRepository;
import com.restaurant.backend.services.FinancialHistoryService;
import com.restaurant.backend.services.StockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockInServiceImpl implements StockInService {
    private final StockInRepository stockInRepository;
    private final FinancialHistoryService financialHistoryService;
    private final IngredientRepository ingredientRepository;
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public StockInServiceImpl(StockInRepository stockInRepository,
            FinancialHistoryService financialHistoryService,
            IngredientRepository ingredientRepository,
            MenuItemRepository menuItemRepository) {
        this.stockInRepository = stockInRepository;
        this.financialHistoryService = financialHistoryService;
        this.ingredientRepository = ingredientRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    @Transactional
    public StockInDTO createStockIn(StockInDTO stockInDTO) {
        StockIn stockIn = new StockIn();
        stockIn.setDate(Instant.now());
        stockIn.setPrice(stockInDTO.getPrice());
        // Lưu StockIn trước để có ID
        StockIn savedStockIn = stockInRepository.save(stockIn);

        // Handle ingredients
        if (stockInDTO.getStockInDetailsIngres() != null) {
            List<StockInDetailsIngre> ingredients = stockInDTO.getStockInDetailsIngres().stream()
                    .map(dto -> {
                        StockInDetailsIngre detail = new StockInDetailsIngre();
                        detail.setStockIn(savedStockIn);
                        detail.setQuantityKg(dto.getQuantityKg());
                        detail.setCPrice(dto.getCPrice());
                        if (dto.getCPrice() != null && dto.getQuantityKg() != null) {
                            detail.setTotalCPrice(dto.getQuantityKg() * dto.getCPrice());
                        }

                        // Lấy tham chiếu đến Ingredient hiện có thay vì tạo mới
                        Ingredient ingredient = ingredientRepository.getReferenceById(dto.getIngredientId());
                        detail.setIngredient(ingredient);

                        // Set the ID
                        StockInDetailsIngreId id = new StockInDetailsIngreId();
                        id.setStoId(savedStockIn.getId());
                        id.setIngreId(dto.getIngredientId());
                        detail.setId(id);

                        return detail;
                    })
                    .collect(Collectors.toList());
            savedStockIn.setStockInDetailsIngres(ingredients);
        }

        // Handle drink/other items
        if (stockInDTO.getStockInDetailsDrinkOthers() != null) {
            List<StockInDetailsDrinkOther> drinkOthers = stockInDTO.getStockInDetailsDrinkOthers().stream()
                    .map(dto -> {
                        StockInDetailsDrinkOther detail = new StockInDetailsDrinkOther();
                        detail.setStockIn(savedStockIn);
                        detail.setQuantityUnits(dto.getQuantityUnits());
                        detail.setCPrice(dto.getCPrice());
                        if (dto.getCPrice() != null && dto.getQuantityUnits() != null) {
                            detail.setTotalCPrice(dto.getQuantityUnits() * dto.getCPrice());
                        }

                        // Lấy tham chiếu đến MenuItem hiện có thay vì tạo mới
                        MenuItem menuItem = menuItemRepository.getReferenceById(dto.getItemId());
                        detail.setMenuItem(menuItem);

                        // Set the ID
                        StockInDetailsDrinkOtherId id = new StockInDetailsDrinkOtherId();
                        id.setStoId(savedStockIn.getId());
                        id.setItemId(dto.getItemId());
                        detail.setId(id);

                        return detail;
                    })
                    .collect(Collectors.toList());
            savedStockIn.setStockInDetailsDrinkOthers(drinkOthers);
        }

        // Final save with details
        StockIn finalStockIn = stockInRepository.save(savedStockIn);

        // Create financial history record for the stock in
        financialHistoryService.createStockInHistory(finalStockIn.getId().longValue(), finalStockIn.getPrice());

        return convertToDTO(finalStockIn);
    }

    @Override
    @Transactional
    public StockInDTO updateStockIn(Integer id, StockInDTO stockInDTO) {
        StockIn existingStockIn = stockInRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockIn not found"));

        existingStockIn.setPrice(stockInDTO.getPrice());

        // Update ingredients
        if (stockInDTO.getStockInDetailsIngres() != null) {
            List<StockInDetailsIngre> newIngredients = new ArrayList<>();
            for (StockInDetailsIngreDTO dto : stockInDTO.getStockInDetailsIngres()) {
                StockInDetailsIngre detail = new StockInDetailsIngre();
                detail.setStockIn(existingStockIn);
                detail.setQuantityKg(dto.getQuantityKg());
                detail.setCPrice(dto.getCPrice());
                if (dto.getCPrice() != null && dto.getQuantityKg() != null) {
                    detail.setTotalCPrice(dto.getQuantityKg() * dto.getCPrice());
                }

                Ingredient ingredient = ingredientRepository.getReferenceById(dto.getIngredientId());
                detail.setIngredient(ingredient);

                StockInDetailsIngreId detailId = new StockInDetailsIngreId();
                detailId.setStoId(existingStockIn.getId());
                detailId.setIngreId(dto.getIngredientId());
                detail.setId(detailId);

                newIngredients.add(detail);
            }
            existingStockIn.getStockInDetailsIngres().clear();
            existingStockIn.getStockInDetailsIngres().addAll(newIngredients);
        }

        // Update drink/other items
        if (stockInDTO.getStockInDetailsDrinkOthers() != null) {
            List<StockInDetailsDrinkOther> newDrinkOthers = new ArrayList<>();
            for (StockInDetailsDrinkOtherDTO dto : stockInDTO.getStockInDetailsDrinkOthers()) {
                StockInDetailsDrinkOther detail = new StockInDetailsDrinkOther();
                detail.setStockIn(existingStockIn);
                detail.setQuantityUnits(dto.getQuantityUnits());
                detail.setCPrice(dto.getCPrice());
                if (dto.getCPrice() != null && dto.getQuantityUnits() != null) {
                    detail.setTotalCPrice(dto.getQuantityUnits() * dto.getCPrice());
                }

                MenuItem menuItem = menuItemRepository.getReferenceById(dto.getItemId());
                detail.setMenuItem(menuItem);

                StockInDetailsDrinkOtherId detailId = new StockInDetailsDrinkOtherId();
                detailId.setStoId(existingStockIn.getId());
                detailId.setItemId(dto.getItemId());

                newDrinkOthers.add(detail);
            }
            existingStockIn.getStockInDetailsDrinkOthers().clear();
            existingStockIn.getStockInDetailsDrinkOthers().addAll(newDrinkOthers);
        }

        StockIn updatedStockIn = stockInRepository.save(existingStockIn);
        return convertToDTO(updatedStockIn);
    }

    @Override
    @Transactional
    public void deleteStockIn(Integer id) {
        StockIn stockIn = stockInRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockIn not found"));
        stockInRepository.delete(stockIn);
    }

    @Override
    public List<StockInDTO> getAllStockIns() {
        return stockInRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StockInDTO getStockInById(Integer id) {
        StockIn stockIn = stockInRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StockIn not found"));
        return convertToDTO(stockIn);
    }

    private StockInDTO convertToDTO(StockIn stockIn) {
        StockInDTO dto = new StockInDTO();
        dto.setId(stockIn.getId());
        dto.setDate(stockIn.getDate());
        dto.setPrice(stockIn.getPrice());

        if (stockIn.getStockInDetailsIngres() != null) {
            dto.setStockInDetailsIngres(stockIn.getStockInDetailsIngres().stream()
                    .map(detail -> {
                        StockInDetailsIngreDTO detailDTO = new StockInDetailsIngreDTO();
                        detailDTO.setStockInId(detail.getStockIn().getId());
                        detailDTO.setIngredientId(detail.getId().getIngreId());
                        detailDTO.setQuantityKg(detail.getQuantityKg());
                        detailDTO.setCPrice(detail.getCPrice());
                        detailDTO.setTotalCPrice(detail.getTotalCPrice());
                        return detailDTO;
                    })
                    .collect(Collectors.toList()));
        }

        if (stockIn.getStockInDetailsDrinkOthers() != null) {
            dto.setStockInDetailsDrinkOthers(stockIn.getStockInDetailsDrinkOthers().stream()
                    .map(detail -> {
                        StockInDetailsDrinkOtherDTO detailDTO = new StockInDetailsDrinkOtherDTO();
                        detailDTO.setStockInId(detail.getStockIn().getId());
                        detailDTO.setItemId(detail.getId().getItemId());
                        detailDTO.setQuantityUnits(detail.getQuantityUnits());
                        detailDTO.setCPrice(detail.getCPrice());
                        detailDTO.setTotalCPrice(detail.getTotalCPrice());
                        return detailDTO;
                    })
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}