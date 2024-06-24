package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResponseUser {
    public String name;
    public String job;
    public String id;
    public Date createdAt;
}

