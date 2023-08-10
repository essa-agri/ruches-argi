package com.agri40.filahati.notification.domain;

import com.agri40.filahati.notification.domain.enumeration.AlertConditionType;
import com.agri40.filahati.notification.domain.enumeration.AlertSi;
import com.agri40.filahati.notification.domain.enumeration.AlertType;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Alert.
 */
@Document(collection = "alert")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Alert implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("type")
    private AlertType type;

    @NotNull
    @Field("alert_si")
    private AlertSi alertSi;

    @NotNull
    @Field("condition_type")
    private AlertConditionType conditionType;

    @NotNull
    @Field("valeur")
    private Float valeur;

    @Field("variation")
    private Float variation;

    @NotNull
    @Field("statut")
    private Boolean statut;

    @NotNull
    @Field("ruche_id")
    private String rucheId;

    @Field("last_stream")
    private String lastStream;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Alert id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AlertType getType() {
        return this.type;
    }

    public Alert type(AlertType type) {
        this.setType(type);
        return this;
    }

    public void setType(AlertType type) {
        this.type = type;
    }

    public AlertSi getAlertSi() {
        return this.alertSi;
    }

    public Alert alertSi(AlertSi alertSi) {
        this.setAlertSi(alertSi);
        return this;
    }

    public void setAlertSi(AlertSi alertSi) {
        this.alertSi = alertSi;
    }

    public AlertConditionType getConditionType() {
        return this.conditionType;
    }

    public Alert conditionType(AlertConditionType conditionType) {
        this.setConditionType(conditionType);
        return this;
    }

    public void setConditionType(AlertConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public Float getValeur() {
        return this.valeur;
    }

    public Alert valeur(Float valeur) {
        this.setValeur(valeur);
        return this;
    }

    public void setValeur(Float valeur) {
        this.valeur = valeur;
    }

    public Float getVariation() {
        return this.variation;
    }

    public Alert variation(Float variation) {
        this.setVariation(variation);
        return this;
    }

    public void setVariation(Float variation) {
        this.variation = variation;
    }

    public Boolean getStatut() {
        return this.statut;
    }

    public Alert statut(Boolean statut) {
        this.setStatut(statut);
        return this;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public String getRucheId() {
        return this.rucheId;
    }

    public Alert rucheId(String rucheId) {
        this.setRucheId(rucheId);
        return this;
    }

    public void setRucheId(String rucheId) {
        this.rucheId = rucheId;
    }

    public String getLastStream() {
        return this.lastStream;
    }

    public Alert lastStream(String lastStream) {
        this.setLastStream(lastStream);
        return this;
    }

    public void setLastStream(String lastStream) {
        this.lastStream = lastStream;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Alert)) {
            return false;
        }
        return id != null && id.equals(((Alert) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Alert{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", alertSi='" + getAlertSi() + "'" +
            ", conditionType='" + getConditionType() + "'" +
            ", valeur=" + getValeur() +
            ", variation=" + getVariation() +
            ", statut='" + getStatut() + "'" +
            ", rucheId='" + getRucheId() + "'" +
            ", lastStream='" + getLastStream() + "'" +
            "}";
    }
}
