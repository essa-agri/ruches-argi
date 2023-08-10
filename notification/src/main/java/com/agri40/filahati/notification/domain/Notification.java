package com.agri40.filahati.notification.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Notification.
 */
@Document(collection = "notification")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("description")
    private String description;

    @NotNull
    @Field("ruche_name")
    private String rucheName;

    @NotNull
    @Field("rucher_name")
    private String rucherName;

    @NotNull
    @Field("created_at")
    private String createdAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Notification id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public Notification description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRucheName() {
        return this.rucheName;
    }

    public Notification rucheName(String rucheName) {
        this.setRucheName(rucheName);
        return this;
    }

    public void setRucheName(String rucheName) {
        this.rucheName = rucheName;
    }

    public String getRucherName() {
        return this.rucherName;
    }

    public Notification rucherName(String rucherName) {
        this.setRucherName(rucherName);
        return this;
    }

    public void setRucherName(String rucherName) {
        this.rucherName = rucherName;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public Notification createdAt(String createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Notification)) {
            return false;
        }
        return id != null && id.equals(((Notification) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Notification{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", rucheName='" + getRucheName() + "'" +
            ", rucherName='" + getRucherName() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }
}
