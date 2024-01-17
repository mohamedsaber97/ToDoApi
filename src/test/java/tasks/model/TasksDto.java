package tasks.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TasksDto {
    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("item")
    private String item;

    @JsonProperty("userID")
    private String userID;

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("__v")
    private String v;

    public TasksDto() {
    }

    public TasksDto(Boolean isCompleted, String item) {
        this.isCompleted = isCompleted;
        this.item = item;
    }

    @JsonProperty("isCompleted")
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("userID")
    public String getUserID() {
        return userID;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("__v")
    public String getV() {
        return v;
    }
}
