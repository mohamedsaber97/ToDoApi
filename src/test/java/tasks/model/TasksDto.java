package tasks.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TasksDto {
    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    @JsonProperty("item")
    private String item;

    public TasksDto(Boolean isCompleted, String item) {
        this.isCompleted = isCompleted;
        this.item = item;
    }

    @JsonProperty("isCompleted")
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    @JsonProperty("item")
    public String getItem() {
        return item;
    }
}
