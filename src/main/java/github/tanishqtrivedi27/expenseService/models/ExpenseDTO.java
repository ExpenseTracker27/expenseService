package github.tanishqtrivedi27.expenseService.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseDTO {
    private String externalId;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("merchant")
    private String merchant;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("created_at")
    private Timestamp createdAt;
}
