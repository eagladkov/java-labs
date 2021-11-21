package ru.bfu.ipmit.eugene;

import ru.bfu.ipmit.eugene.core.WebShop;
import ru.bfu.ipmit.eugene.core.models.ProductAvailability;
import ru.bfu.ipmit.eugene.core.models.ProductCategory;
import ru.bfu.ipmit.eugene.core.models.ProductSupplier;
import ru.bfu.ipmit.eugene.search.WebShopSearchService;
import ru.bfu.ipmit.eugene.search.models.WebShopSearchRequest;
import ru.bfu.ipmit.eugene.search.models.WebShopSearchResult;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class WebShopSearchServiceMainClass implements WebShopSearchService{

    private final WebShop webShop;

    public WebShopSearchServiceMainClass(WebShop webShop) {
        this.webShop = webShop;
    }

    @Override
    public List<WebShopSearchResult> findProducts(WebShopSearchRequest searchRequest) {
        return getProducts().stream()
                .filter(product -> product.getProductName().contains(searchRequest.getProductNameSearchText()))
                .filter(product -> product.getProductPrice() <= searchRequest.getMaximalProductPrice())
                .filter(product -> product.getProductRating() >= searchRequest.getMinimalProductRating())
                .map(this::mapResult)
                .collect(toList());
    }

    private List<ProductAvailability> getProducts() {
        List<Long> categoryIds = this.webShop.getProductCategories().stream()
                .map(ProductCategory::getId)
                .collect(toList());
        return this.webShop.getProductSuppliers().stream()
                .map(ProductSupplier::getId).map(
                        supplierId -> categoryIds.stream()
                                .map(
                                        categoryId -> this.webShop.getProducts(supplierId, categoryId)
                                )
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList())
                )
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private WebShopSearchResult mapResult(ProductAvailability product) {
        ProductSupplier supplier = this.webShop.getProductSuppliers().stream()
                .filter(it -> it.getId().equals(product.getProductSupplierId()))
                .findFirst().orElseThrow(NoSuchElementException::new);

        ProductCategory category = this.webShop.getProductCategories().stream()
                .filter(it -> it.getId().equals(product.getProductCategoryId()))
                .findFirst().orElseThrow(NoSuchElementException::new);

        return new WebShopSearchResult() {
            @Override
            public String getProductName() {
                return product.getProductName();
            }

            @Override
            public String getProductDescription() {
                return product.getProductDescription();
            }

            @Override
            public String getProductPrice() {
                return String.valueOf(product.getProductPrice());
            }

            @Override
            public String getProductRating() {
                return String.valueOf(product.getProductRating());
            }

            @Override
            public String getProductCategoryName() {
                return category.getName();
            }

            @Override
            public String getProductSupplierName() {
                return supplier.getName();
            }

            @Override
            public String getProductSupplierRating() {
                return String.valueOf(supplier.getRating());
            }
        };
    }
}
