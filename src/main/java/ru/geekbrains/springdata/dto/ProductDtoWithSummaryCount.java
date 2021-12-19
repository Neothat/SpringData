package ru.geekbrains.springdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoWithSummaryCount extends ProductDto{
    private int summaryCount;

    public ProductDtoWithSummaryCount(Long id, String title, int cost, int summaryCount) {
        super(id, title, cost * summaryCount);
        this.summaryCount = summaryCount;
    }
}
